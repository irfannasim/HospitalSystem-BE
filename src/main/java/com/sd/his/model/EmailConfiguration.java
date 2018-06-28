package com.sd.his.model;

import javax.persistence.*;

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
 * @Package   : com.sd.his.model
 * @FileName  : EmailConfiguration
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "EMAIL_CONFIGURATION")
public class EmailConfiguration {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SENDER_EMAIL")
    private String senderEmail;

    @Column(name = "SMTP_PASSWORD")
    private String smptPassword;

    @Column(name = "SMTP_PORT")
    private String smtpPort;

    @Column(name = "SMTP_HOST")
    private String smtpHost;

    @Column(name = "SERVER_TYPE") //SMTP or SES
    private String serverType;

    @Column(name = "SES_ACCESS_KEY")
    private String sesAccessKey;

    @Column(name = "SES_SECRET_KEY")
    private String sesSecretKey;

    @Column(name = "IS_SYSTEM_DEFAULT", columnDefinition = "boolean default false")
    private boolean systemDefault;

    public EmailConfiguration() {
    }

    @Override
    public String toString() {
        return "EmailConfiguration{" +
                "id=" + id +
                ", senderEmail='" + senderEmail + '\'' +
                ", smptPassword='" + smptPassword + '\'' +
                ", smtpPort='" + smtpPort + '\'' +
                ", smtpHost='" + smtpHost + '\'' +
                ", serverType='" + serverType + '\'' +
                ", sesAccessKey='" + sesAccessKey + '\'' +
                ", sesSecretKey='" + sesSecretKey + '\'' +
                ", systemDefault=" + systemDefault +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSmptPassword() {
        return smptPassword;
    }

    public void setSmptPassword(String smptPassword) {
        this.smptPassword = smptPassword;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getSesAccessKey() {
        return sesAccessKey;
    }

    public void setSesAccessKey(String sesAccessKey) {
        this.sesAccessKey = sesAccessKey;
    }

    public String getSesSecretKey() {
        return sesSecretKey;
    }

    public void setSesSecretKey(String sesSecretKey) {
        this.sesSecretKey = sesSecretKey;
    }

    public boolean isSystemDefault() {
        return systemDefault;
    }

    public void setSystemDefault(boolean systemDefault) {
        this.systemDefault = systemDefault;
    }
}
