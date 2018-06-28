package com.sd.his.response;

/*
 * @author    : Irfan Nasim
 * @Date      : 06-Jun-18
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
 * @Package   : com.sd.his.response
 * @FileName  : AdminDashboardDataResponseWrapper
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class AdminDashboardDataResponseWrapper {

    private long patientCount;
    private long appointmentsCount;
    private long medicalServicesCount;
    private long icdsCount;

    public AdminDashboardDataResponseWrapper() {
    }

    public AdminDashboardDataResponseWrapper(long patientCount, long appointmentsCount, long medicalServicesCount, long icdsCount) {
        this.patientCount = patientCount;
        this.appointmentsCount = appointmentsCount;
        this.medicalServicesCount = medicalServicesCount;
        this.icdsCount = icdsCount;
    }

    @Override
    public String toString() {
        return "AdminDashboardDataResponseWrapper{" +
                "patientCount=" + patientCount +
                ", appointmentsCount=" + appointmentsCount +
                ", medicalServicesCount=" + medicalServicesCount +
                ", icdsCount=" + icdsCount +
                '}';
    }

    public long getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(long patientCount) {
        this.patientCount = patientCount;
    }

    public long getAppointmentsCount() {
        return appointmentsCount;
    }

    public void setAppointmentsCount(long appointmentsCount) {
        this.appointmentsCount = appointmentsCount;
    }

    public long getMedicalServicesCount() {
        return medicalServicesCount;
    }

    public void setMedicalServicesCount(long medicalServicesCount) {
        this.medicalServicesCount = medicalServicesCount;
    }

    public long getIcdsCount() {
        return icdsCount;
    }

    public void setIcdsCount(long icdsCount) {
        this.icdsCount = icdsCount;
    }
}
