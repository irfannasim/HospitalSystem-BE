package com.sd.his.wrapper;

import com.sd.his.model.EmailTemplate;

public class EmailTemplateWrapper {
    private long id;
    private String title;
    private String subject;
    private String type;
    private String emailTemplate;
    private boolean active;
    private boolean deleted;
    private long updatedOn;
    private long createdOn;

    public EmailTemplateWrapper() {
    }

    public EmailTemplateWrapper(EmailTemplate emailTemplate) {
        this.id = emailTemplate.getId();
        this.title = emailTemplate.getTitle();
        this.subject = emailTemplate.getSubject();
        this.type = emailTemplate.getType();
        this.emailTemplate = emailTemplate.getEmailTemplate();
        this.active = emailTemplate.getActive();
        this.deleted = emailTemplate.getDeleted();
        this.createdOn = emailTemplate.getCreatedOn();
        this.updatedOn = emailTemplate.getUpdatedOn();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }
}
