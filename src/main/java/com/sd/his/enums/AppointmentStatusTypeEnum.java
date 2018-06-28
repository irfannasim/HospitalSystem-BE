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
 * @FileName  : AppointmentStatusTypeEnum
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public enum AppointmentStatusTypeEnum {

    REGULAR("REGULAR"),
    CHECK_IN("WALK_IN"),
    IN_ROOM("TRANSACTION_OF_CARE"),
    IN_SESSION("TRANSACTION_OF_CARE"),
    COMPLETE("TRANSACTION_OF_CARE"),
    CONFIRMED("TRANSACTION_OF_CARE"),
    NOT_CONFIRMED("TRANSACTION_OF_CARE"),
    RE_SCHEDULED("TRANSACTION_OF_CARE"),
    CANCELLED("TRANSACTION_OF_CARE");

    private String value;

    AppointmentStatusTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
