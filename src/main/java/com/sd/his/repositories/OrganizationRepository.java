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

import com.sd.his.model.Organization;
import com.sd.his.model.User;
import com.sd.his.model.Vacation;
import com.sd.his.response.OrganizationResponseWrapper;
import com.sd.his.wrapper.TimezoneWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByActiveTrueAndDeletedFalse();

    @Query("SELECT new com.sd.his.response.OrganizationResponseWrapper(o.id, o.companyName, o.cellPhone, o.aptSerialStart, o.website, ou.email) FROM Organization o INNER JOIN o.user ou where o.active =TRUE AND o.deleted =FALSE ")
    List<OrganizationResponseWrapper> findAllByNameAndActiveTrueAndDeletedFalse(Pageable pageable);

    int countAllByActiveTrueAndDeletedFalse();

    Organization findByCompanyName(String companyName);
}
