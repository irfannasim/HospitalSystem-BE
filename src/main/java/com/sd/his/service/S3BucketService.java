package com.sd.his.service;

import com.amazonaws.services.s3.S3ResponseMetadata;
import com.sd.his.model.S3Bucket;
import com.sd.his.model.Tax;
import com.sd.his.repositories.S3BucketRepository;
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
 * @Date      : 26-Jun-18
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
 * @FileName  : S3BucketService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class S3BucketService {

    @Autowired
    S3BucketRepository s3BucketRepository;

    public S3Bucket findActiveBucket() {
        return s3BucketRepository.findByActiveTrue();
    }
}
