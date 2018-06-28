package com.sd.his.repositories;

import com.sd.his.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
 * @Package   : com.sd.his.repositories
 * @FileName  : ProfileRepository
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
