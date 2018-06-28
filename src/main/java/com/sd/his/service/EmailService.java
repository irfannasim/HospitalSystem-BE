package com.sd.his.service;


import com.sd.his.enums.EmailServerTypeEnum;
import com.sd.his.model.EmailConfiguration;
import com.sd.his.utill.AmazonSESUtil;
import com.sd.his.utill.SMTPUtil;
import com.sd.his.wrapper.EmailWrapper;
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
 * @FileName  : EmailService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class EmailService {

    @Autowired
    private EmailConfigurationService emailConfigurationService;
    private EmailConfiguration emailConfiguration;

    public Boolean sendEmail(EmailWrapper email) {
        emailConfiguration = emailConfigurationService.findSystemDefaultEmailConfiguration();
        if (emailConfiguration.getServerType().equalsIgnoreCase(EmailServerTypeEnum.AWS_SES.getValue())) {
            return new AmazonSESUtil(emailConfiguration).sendEmail(emailConfiguration.getSenderEmail(),
                    email.getRecipient(), email.getSubject(), email.getContent());
        } else {
            return new SMTPUtil(emailConfiguration).sendEmail(emailConfiguration.getSenderEmail(), email.getRecipient(),
                    email.getSubject(), email.getContent());
        }
    }

}
