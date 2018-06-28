package com.sd.his.repositories;


import com.sd.his.model.MedicalService;
import com.sd.his.model.User;
import com.sd.his.model.UserMedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @author    : waqas kamran
 * @Date      : 17-Apr-18
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
 * @Package   : com.sd.his.*
 * @FileName  : UserAuthAPI
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Repository
public interface UserMedicalServiceRepository extends JpaRepository<UserMedicalService, Long> {

  UserMedicalService findByMedicalService(MedicalService MedicalService);
  void deleteByUser(User user);
}

