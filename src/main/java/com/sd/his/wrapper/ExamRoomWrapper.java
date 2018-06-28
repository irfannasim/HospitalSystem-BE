package com.sd.his.wrapper;

import com.sd.his.model.Room;

/*
 * @author    : Irfan Nasim
 * @Date      : 08-Jun-18
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
 * @FileName  : ExamRoomWrapper
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class ExamRoomWrapper {

    private Long id;
    private String examName;
    private boolean deleted;
    private long updatedOn;
    private long createdOn;
    private boolean active;
    private boolean allowOnlineScheduling;

    public ExamRoomWrapper() {
    }

    public ExamRoomWrapper(Room room) {
        this.id = room.getId();
        this.examName = room.getExamName();
    }

    @Override
    public String toString() {
        return "ExamRoomWrapper{" +
                "id=" + id +
                ", examName='" + examName + '\'' +
                ", deleted=" + deleted +
                ", updatedOn=" + updatedOn +
                ", createdOn=" + createdOn +
                ", active=" + active +
                ", allowOnlineScheduling=" + allowOnlineScheduling +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAllowOnlineScheduling() {
        return allowOnlineScheduling;
    }

    public void setAllowOnlineScheduling(boolean allowOnlineScheduling) {
        this.allowOnlineScheduling = allowOnlineScheduling;
    }
}
