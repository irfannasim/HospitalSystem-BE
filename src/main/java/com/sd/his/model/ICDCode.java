package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.request.ICDCodeCreateRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 * @author    : Irfan Nasim
 * @Date      : 26-Apr-18
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
 * @FileName  : ICDCode
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "ICD_CODE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICDCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "STATUS", columnDefinition = "boolean default true", nullable = false)
    private Boolean status;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @Column(name = "DESCRIPTION")
    private String description;


    @JsonIgnore
    @OneToMany(targetEntity = ICDCodeVersion.class, mappedBy = "icd", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<ICDCodeVersion> icdCodes;

    public ICDCode() {
    }

    public ICDCode(ICDCodeCreateRequest createRequest) {
        this.code = createRequest.getCode();
        this.createdOn = System.currentTimeMillis();
        this.status = createRequest.isStatus();
        this.setDeleted(false);
        this.title = createRequest.getTitle();
        this.updatedOn = System.currentTimeMillis();
        this.description = createRequest.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ICDCodeVersion> getIcdCodes() {
        return icdCodes;
    }

    public void setIcdCodes(List<ICDCodeVersion> icdCodes) {
        this.icdCodes = icdCodes;
    }

}
