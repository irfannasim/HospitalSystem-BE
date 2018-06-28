package com.sd.his.service;

import com.sd.his.model.EmailConfiguration;
import com.sd.his.repositories.EmailConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author    : Irfan Nasim
 * @Date      : 11-Jun-18
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
 * @FileName  : EmailConfigurationService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class EmailConfigurationService {

    private final Logger logger = LoggerFactory.getLogger(EmailConfigurationService.class);

    @Autowired
    private EmailConfigurationRepository emailConfigurationRepository;

    public EmailConfiguration findSystemDefaultEmailConfiguration() {
        return emailConfigurationRepository.findBySystemDefaultTrue();
    }

}