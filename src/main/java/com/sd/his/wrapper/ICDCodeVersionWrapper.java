package com.sd.his.wrapper;

import com.sd.his.model.ICDCodeVersion;

import java.util.List;

/*
 * @author    : Qari Jamal
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
 * @Package   : com.sd.his.wrapper
 * @FileName  : ICDCodeVersionWrapper
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class ICDCodeVersionWrapper {

    private long id;
    private ICDCodeWrapper icdCode;
    private ICDVersionWrapper icdVersion;
    private String description;

    private String selectedICDVersionId;
    private List<ICDCodeWrapper> selectedICDCodes;
    private List<ICDCodeWrapper> iCDCodes;

    public List<ICDCodeWrapper> getiCDCodes() {
        return iCDCodes;
    }

    public void setiCDCodes(List<ICDCodeWrapper> iCDCodes) {
        this.iCDCodes = iCDCodes;
    }

    public ICDCodeVersionWrapper() {
    }

    public ICDCodeVersionWrapper(ICDCodeVersion icdCodeVersion) {
        this.id = icdCodeVersion.getId();
        this.description = icdCodeVersion.getDescription();
    }

    public ICDCodeVersionWrapper(ICDCodeVersion codeVersion, ICDCodeWrapper code, ICDVersionWrapper version) {
        this.id = codeVersion.getId();
        this.description = codeVersion.getDescription();
        this.icdCode = code;
        this.icdVersion = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ICDCodeWrapper getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(ICDCodeWrapper icdCode) {
        this.icdCode = icdCode;
    }

    public ICDVersionWrapper getIcdVersion() {
        return icdVersion;
    }

    public void setIcdVersion(ICDVersionWrapper icdVersion) {
        this.icdVersion = icdVersion;
    }

    public String getSelectedICDVersionId() {
        return selectedICDVersionId;
    }

    public void setSelectedICDVersionId(String selectedICDVersionId) {
        this.selectedICDVersionId = selectedICDVersionId;
    }

    public List<ICDCodeWrapper> getSelectedICDCodes() {
        return selectedICDCodes;
    }

    public void setSelectedICDCodes(List<ICDCodeWrapper> selectedICDCodes) {
        this.selectedICDCodes = selectedICDCodes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
