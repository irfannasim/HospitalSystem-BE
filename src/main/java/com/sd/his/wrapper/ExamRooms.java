package com.sd.his.wrapper;

/*
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
public class ExamRooms {
    boolean allowOnlineScheduling;
    String examName;

    public ExamRooms() {
    }

    public boolean isAllowOnlineScheduling() {
        return allowOnlineScheduling;
    }

    public void setAllowOnlineScheduling(boolean allowOnlineScheduling) {
        this.allowOnlineScheduling = allowOnlineScheduling;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}