package com.sd.his.enums;

/*
 * @author    : Irfan Nasim
 * @Date      :08-Jun-18
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
 * @Package   : com.sd.his.enums
 * @FileName  : AppointmentTypeEnum
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public enum AppointmentTypeEnum {

    REGULAR("REGULAR"),
    WALK_IN("WALK_IN"),
    NEW_PATIENT("NewPatient"),
    TRANSACTION_OF_CARE("TRANSACTION_OF_CARE");

    private String value;

    AppointmentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
