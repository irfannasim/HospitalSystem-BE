package com.sd.his.repositories;

import com.sd.his.model.EmailTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @author    : Muhammad Jamal
 * @Date      : 21-May-18
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
 * @FileName  : EmailTemplateRepository
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

    List<EmailTemplate> findAllByDeletedFalse(Pageable pageable);

    List<EmailTemplate> findAllByDeletedFalse();


    List<EmailTemplate> findAllByTitleAndDeletedFalse(String title);

    EmailTemplate findByTitleAndIdNot(String title, long id);

    EmailTemplate findByIdAndDeletedFalse(long id);

    List<EmailTemplate> findAllByTitleContainingAndDeletedFalse(String title, Pageable pageable);

    List<EmailTemplate> findAllByTitleContainingAndDeletedFalse(String title);

    EmailTemplate findByTypeAndActiveTrueAndDeletedFalse(String type);
}
