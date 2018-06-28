package com.sd.his.repositories;

import com.sd.his.model.Tax;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface TaxRepository extends JpaRepository<Tax, Long> {

    List<Tax> findAllByDeletedFalse(Pageable pageable);

    List<Tax> findAllByDeletedFalseAndActiveTrue();

    List<Tax> findAllByDeletedFalse();

    List<Tax> findAllByNameAndIdNotAndDeletedFalse(String name,long id);

    Tax findByNameAndDeletedFalse(String name);
    Tax findById(long id);

    List<Tax> findAllByNameContainingAndDeletedFalse(String name, Pageable pageable);
    List<Tax> findAllByNameContainingAndDeletedFalse(String name);

}
