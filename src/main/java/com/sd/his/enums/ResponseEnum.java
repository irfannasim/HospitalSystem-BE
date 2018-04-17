package com.sd.his.enums;

/*
 * @author    : irfan
 * @Date      : 22-May-17
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
 * @Project   : adminportal
 * @Package   : com.sd.ap.enums
 * @FileName  : ResponseEnum
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */

public enum ResponseEnum {

    DATA("DATA"),
    STATUS("STATUS"),
    REASON("REASON"),
    ERROR("ERROR"),
    SUCCESS("SUCCESS"),
    INFO("INFO"),
    NOT_FOUND("NOT_FOUND"),
    ADMIN_LOGGEDIN_FAILED("ADM_ERR_01"),
    ADMIN_LOGGEDIN_SUCCESS("ADM_SUC_01"),
    ADMIN_NOT_FOUND("ADM_ERR_02"),
    ADMIN_ACCESS_GRANTED("ADM_AUTH_SUC_01"),
    EXCEPTION("SYS_ERR_01");


    private String value;

    ResponseEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
