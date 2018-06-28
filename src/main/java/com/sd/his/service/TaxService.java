package com.sd.his.service;

import com.sd.his.model.Tax;
import com.sd.his.repositories.TaxRepository;
import com.sd.his.request.SaveTaxRequest;
import com.sd.his.utill.APIUtil;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.TaxWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
 * @author    : Irfan Nasim
 * @Date      : 14-May-18
 * @version   : ver. 1.0.0
 *
 * ________________________________________________________________________________________________
 *
 *  Developer				Date		     Version		Operation		Description
 * ________________________________________________________________________________________________
 *
 *
 * ________________________________________________________________________________________________
 *
 * @Project   : HIS
 * @Package   : com.sd.his.service
 * @FileName  : TaxService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class TaxService {

    @Autowired
    TaxRepository taxRepository;

    public List<TaxWrapper> findAllActiveTax() {
        List<Tax> dbTax = taxRepository.findAllByDeletedFalseAndActiveTrue();
        List<TaxWrapper> taxWrappers = new ArrayList<>();

        for (Tax tax : dbTax) {
            TaxWrapper taxWrapper = new TaxWrapper(tax);
            taxWrappers.add(taxWrapper);
        }
        return taxWrappers;
    }

    public List<TaxWrapper> findAllPaginatedTax(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        List<Tax> dbTax = taxRepository.findAllByDeletedFalse(pageable);
        List<TaxWrapper> taxWrappers = new ArrayList<>();

        for (Tax tax : dbTax) {
            TaxWrapper taxWrapper = new TaxWrapper(tax);
            taxWrappers.add(taxWrapper);
        }
        return taxWrappers;
    }

    public int countAllTax() {
        return taxRepository.findAllByDeletedFalse().size();
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteTax(Tax tax) {
        tax.setDeleted(true);
        taxRepository.save(tax);
    }

    public Tax findTaxById(long taxId) {
        return taxRepository.findOne(taxId);
    }

    @Transactional(rollbackOn = Throwable.class)
    public Tax saveTax(SaveTaxRequest saveTaxRequest) {
        Tax tax = new Tax(saveTaxRequest);
        return taxRepository.save(tax);
    }

    public boolean isAlreadyExist(SaveTaxRequest taxWrapper) {
        Boolean isAlreadyExist = false;

        if (taxWrapper.getId() > 0) {
            List<Tax> taxes = this.taxRepository.findAllByNameAndIdNotAndDeletedFalse(taxWrapper.getName(), taxWrapper.getId());
            if (!HISCoreUtil.isListEmpty(taxes)) {
                isAlreadyExist = true;
            }
        } else {
            Tax tax = this.taxRepository.findByNameAndDeletedFalse(taxWrapper.getName());
            if (HISCoreUtil.isValidObject(tax)) {
                isAlreadyExist = true;
            }
        }
        return isAlreadyExist;
    }

    public Tax updateTaxService(SaveTaxRequest updateRequest) {
        Tax dbTax = this.taxRepository.findById(updateRequest.getId());
        APIUtil.buildTax(dbTax, updateRequest);
        return this.taxRepository.save(dbTax);
    }

    public List<SaveTaxRequest> searchByTaxByName(String searchTaxName, int pageNo, int pageSize) {
        List<SaveTaxRequest> taxesResponse = new ArrayList<>();
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Tax> dbTaxes = taxRepository.findAllByNameContainingAndDeletedFalse(searchTaxName, pageable);
        APIUtil.buildTaxWrapper(taxesResponse, dbTaxes);
        return taxesResponse;
    }

    public int countSearchByTaxByName(String searchTaxName) {
        return taxRepository.findAllByNameContainingAndDeletedFalse(searchTaxName).size();
    }
}
