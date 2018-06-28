package com.sd.his.model;

import javax.persistence.*;
import java.io.Serializable;

/*
 * @author    : Irfan Nasim
 * @Date      : 02-May-18
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
 * @FileName  : ICDCodeVersion
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "ICD_CODE_VERSION")
public class ICDCodeVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ICD_ID")
    private ICDCode icd;

    @ManyToOne
    @JoinColumn(name = "ICD_VERSION_ID")
    private ICDVersion version;

    @Column(name = "description",length = 400)
    private String description;

    public ICDCodeVersion() {
    }

    public ICDCodeVersion(ICDCode icd, ICDVersion version) {
        this.icd = icd;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ICDCode getIcd() {
        return icd;
    }

    public void setIcd(ICDCode icd) {
        this.icd = icd;
    }

    public ICDVersion getVersion() {
        return version;
    }

    public void setVersion(ICDVersion version) {
        this.version = version;
    }
}
