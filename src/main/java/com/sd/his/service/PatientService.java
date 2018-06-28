package com.sd.his.service;

import org.springframework.stereotype.Service;

/*
 * @author    : Muhammad Jamal
 * @Date      : 5-Jun-18
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
 * @FileName  : ManagePatientService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class PatientService {

/*    @Autowired
    UserRepository patientRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    InsuranceRepository insuranceRepository;

    public List<PatientWrapper> findAllPaginatedUserByUserType(int offset, int limit,String userType) {
        Pageable pageable = new PageRequest(offset, limit);
        return patientRepository.findAllByDeletedFalse(pageable,userType);
    }

    public int countAllPaginatedPatients(String userType) {
        return patientRepository.findAllByDeletedFalse(userType).size();
    }

    public void deletePatientById(long patientId) {
        User user = this.patientRepository.findOne(patientId);
        if (user != null) {
            user.setDeleted(true);
            user.getProfile().setDeleted(true);
            user.getProfile().setUpdatedOn(System.currentTimeMillis());

            user.getInsurance().setUpdated(System.currentTimeMillis());
            user.getInsurance().setDeleted(true);
            this.patientRepository.save(user);
        }
    }
    @Transactional(rollbackOn = Throwable.class)
    public void savePatient(PatientRequest patientRequest) throws ParseException {
        Profile profile = new Profile(patientRequest);
        Insurance insurance = new Insurance(patientRequest);
        User user = new User(patientRequest);
        this.profileRepository.save(profile);
        this.insuranceRepository.save(insurance);
        user.setProfile(profile);
        user.setInsurance(insurance);
        this.patientRepository.save(user);
    }

    public boolean isUserAlreadyExists(String userName, String email) {
//        User user = this.patientRepository.

        return false;
    }*/

   /* @Transactional(rollbackOn = Throwable.class)
    public void deleteTax(Tax tax) {
        tax.setDeleted(true);
        patientRepository.save(tax);
    }*/

//    public Tax findTaxById(long taxId) {
//        return patientRepository.findOne(taxId);
//    }

    /*@Transactional(rollbackOn = Throwable.class)
    public Tax saveTax(SaveTaxRequest saveTaxRequest) {
        Tax tax = new Tax(saveTaxRequest);
        return patientRepository.save(tax);
    }*/

    /*public boolean isAlreadyExist(SaveTaxRequest taxWrapper) {
        Boolean isAlreadyExist = false;

        if (taxWrapper.getId() > 0) {
            List<Tax> taxes = this.patientRepository.findAllByNameAndIdNotAndDeletedFalse(taxWrapper.getName(), taxWrapper.getId());
            if (!HISCoreUtil.isListEmpty(taxes)) {
                isAlreadyExist = true;
            }
        } else {
            Tax tax = this.patientRepository.findByNameAndDeletedFalse(taxWrapper.getName());
            if (HISCoreUtil.isValidObject(tax)) {
                isAlreadyExist = true;
            }
        }
        return isAlreadyExist;
    }

    public Tax updateTaxService(SaveTaxRequest updateRequest) {
        Tax dbTax = this.patientRepository.findById(updateRequest.getId());
        APIUtil.buildTax(dbTax, updateRequest);
        return this.patientRepository.save(dbTax);
    }

    public List<SaveTaxRequest> searchByTaxByName(String searchTaxName, int pageNo, int pageSize) {
        List<SaveTaxRequest> taxesResponse = new ArrayList<>();
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Tax> dbTaxes = patientRepository.findAllByNameContainingAndDeletedFalse(searchTaxName, pageable);
        APIUtil.buildTaxWrapper(taxesResponse, dbTaxes);
        return taxesResponse;
    }

    public int countSearchByTaxByName(String searchTaxName) {
        return patientRepository.findAllByNameContainingAndDeletedFalse(searchTaxName).size();
    }*/
}
