package com.sd.his.repositories;/*
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

import com.sd.his.model.Branch;
import com.sd.his.model.BranchUser;
import com.sd.his.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchUserRepository extends JpaRepository<BranchUser,Long> {
   @Query("SELECT b FROM Branch b INNER JOIN b.users bu where bu.user.id=:userId")
    List<BranchUser> findByUser(@Param("userId") long userId);

   List<BranchUser> findAllByBranch(Branch branch);
   BranchUser findByUser(User user);
   BranchUser findByBranch(Branch branch);

}
