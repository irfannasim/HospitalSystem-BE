package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.wrapper.ICDVersionWrapper;

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
 * @FileName  : ICDVersion
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "ICD_VERSION")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICDVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "STATUS", columnDefinition = "boolean default true", nullable = false)
    private boolean status;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;


    @JsonIgnore
    @OneToMany(targetEntity = ICDCodeVersion.class, mappedBy = "version", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<ICDCodeVersion> versions;

    public ICDVersion() {
    }

    public ICDVersion(ICDVersionWrapper createRequest) {
        this.name = createRequest.getName();
        this.title = createRequest.getTitle();
        this.status = createRequest.isStatus();
        this.createdOn = System.currentTimeMillis();
        this.updatedOn = 0;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public List<ICDCodeVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<ICDCodeVersion> versions) {
        this.versions = versions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
