package com.sd.his.repositories;

import com.sd.his.model.Tax;
import com.sd.his.model.Timezone;
import com.sd.his.wrapper.TimezoneWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
 * @Package   : com.sd.his.repositories
 * @FileName  : TaxRepository
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Repository
public interface TimezoneRepository extends JpaRepository<Timezone, Long> {

    @Query("SELECT new com.sd.his.wrapper.TimezoneWrapper(t.id,t.countryCode,t.name) FROM Timezone t ")
     List<TimezoneWrapper> findAllByCountryCode();
}
