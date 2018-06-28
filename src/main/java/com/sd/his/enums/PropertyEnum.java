package com.sd.his.enums;/*
 * @author    : waqas kamran
 * @Date      : 17-Apr-18
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
 * @Package   : com.sd.his.*
 * @FileName  : UserAuthAPI
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */

public enum PropertyEnum {
    STATUS("ACTIVE"),
    USER_TYPE_RECEPTIONIST("RECEPTIONIST"),
    USER_TYPE_NURSE("NURSE"),
    USER_TYPE_CASHIER("CASHIER"),
    PRIMARY_BRANCH("primaryBranch"),
    PRIMARY_DOCTOR("primaryDOCTOR"),
    USER_TYPE_DOCTOR("DOCTOR");


    private String value;

    PropertyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
