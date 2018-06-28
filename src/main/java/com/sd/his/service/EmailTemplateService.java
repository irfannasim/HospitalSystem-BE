package com.sd.his.service;

import com.sd.his.model.EmailTemplate;
import com.sd.his.repositories.EmailTemplateRepository;
import com.sd.his.request.EmailTemplateRequest;
import com.sd.his.utill.APIUtil;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.EmailTemplateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
 * @Package   : com.sd.his.service
 * @FileName  : TaxService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class EmailTemplateService {

    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    public List<EmailTemplateWrapper> findAllEmailTemplate(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        List<EmailTemplate> emailTemplates = emailTemplateRepository.findAllByDeletedFalse(pageable);
        List<EmailTemplateWrapper> emailTemplateWrappers = new ArrayList<>();

        for (EmailTemplate emailTemplate : emailTemplates) {
            EmailTemplateWrapper wrapper = new EmailTemplateWrapper(emailTemplate);
            emailTemplateWrappers.add(wrapper);
        }
        return emailTemplateWrappers;
    }

    public int countAllEmailTemplate() {
        return emailTemplateRepository.findAllByDeletedFalse().size();
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(long id) {
        EmailTemplate emailTemplate = emailTemplateRepository.findOne(id);
        if (HISCoreUtil.isValidObject(emailTemplate)) {
            emailTemplate.setDeleted(true);
            emailTemplateRepository.save(emailTemplate);
        }
    }

    public boolean isDupTitle(String title) {
        List<EmailTemplate> emailTemplate = emailTemplateRepository.findAllByTitleAndDeletedFalse(title);
        if (HISCoreUtil.isListEmpty(emailTemplate)) {
            return false;
        }
        return true;
    }

    public boolean isDupTitleAgainstId(EmailTemplateRequest emailTemplateRequest) {
        EmailTemplate emailTemplate = emailTemplateRepository.findByTitleAndIdNot(
                emailTemplateRequest.getTitle(),
                emailTemplateRequest.getId());
        if (HISCoreUtil.isValidObject(emailTemplate)) {
            return true;
        }
        return false;
    }

    public void saveEmailTemplate(EmailTemplateRequest createRequest) {
        EmailTemplate emailTemplate = new EmailTemplate();
        APIUtil.buildEmailTemplateRequest(emailTemplate, createRequest);
        emailTemplateRepository.save(emailTemplate);
    }

    public EmailTemplate updateEmailTemplate(EmailTemplateRequest createRequest) {
        EmailTemplate emailTemplate = emailTemplateRepository.findByIdAndDeletedFalse(createRequest.getId());
        if (HISCoreUtil.isValidObject(emailTemplate)) {
            APIUtil.buildEmailTemplateRequest(emailTemplate, createRequest);
            emailTemplateRepository.save(emailTemplate);
            return emailTemplate;
        }
        return null;
    }

    public EmailTemplateRequest getEmailTemplateById(long id) {
        EmailTemplate emailTemplate = emailTemplateRepository.findOne(id);
        EmailTemplateRequest emailTemplateRequest = null;
        if (HISCoreUtil.isValidObject(emailTemplate)) {
            emailTemplateRequest = new EmailTemplateRequest(emailTemplate);
            return emailTemplateRequest;
        }
        return emailTemplateRequest;
    }

    public List<EmailTemplateRequest> searchEmailTemplateByTitle(int page, int pageSize, String title) {
        Pageable pageable = new PageRequest(page, pageSize);
        List<EmailTemplate> dbEmailTemplates = emailTemplateRepository.findAllByTitleContainingAndDeletedFalse(title, pageable);
        if (!HISCoreUtil.isListEmpty(dbEmailTemplates)) {
            return APIUtil.buildEmailTemplatesRequest(dbEmailTemplates);
        }
        return new ArrayList<>();
    }

    public int countSearchEmailTemplateByTitle(String title) {
        return emailTemplateRepository.findAllByTitleContainingAndDeletedFalse(title).size();
    }

    public EmailTemplate findEmailTemplateByType(String type){
        return emailTemplateRepository.findByTypeAndActiveTrueAndDeletedFalse(type);
    }
}
