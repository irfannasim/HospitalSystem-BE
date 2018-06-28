package com.sd.his.repositories;

import com.sd.his.model.S3Bucket;
import com.sd.his.model.User;
import com.sd.his.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
 * @Package   : com.sd.his.repositories
 * @FileName  : S3BucketRepository
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Repository
public interface S3BucketRepository extends JpaRepository<S3Bucket, Long> {

    S3Bucket findByActiveTrue();
}
