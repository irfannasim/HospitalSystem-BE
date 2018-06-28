package com.sd.his.request;

public class SearchMedicalServiceRequest {

    private long searchServiceId = 0;
    private String searchServiceName = "";
    private long searchBranchId = 0;
    private long searchDepartmentId = 0;
    private long searchServiceFee = 0;
    private boolean searched = false;


    public long getSearchServiceId() {
        return searchServiceId;
    }

    public void setSearchServiceId(long searchServiceId) {
        this.searchServiceId = searchServiceId;
    }

    public String getSearchServiceName() {
        return searchServiceName;
    }

    public void setSearchServiceName(String searchServiceName) {
        this.searchServiceName = searchServiceName;
    }

    public long getSearchBranchId() {
        return searchBranchId;
    }

    public void setSearchBranchId(long searchBranchId) {
        this.searchBranchId = searchBranchId;
    }

    public long getSearchDepartmentId() {
        return searchDepartmentId;
    }

    public void setSearchDepartmentId(long searchDepartmentId) {
        this.searchDepartmentId = searchDepartmentId;
    }

    public long getSearchServiceFee() {
        return searchServiceFee;
    }

    public void setSearchServiceFee(long searchServiceFee) {
        this.searchServiceFee = searchServiceFee;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }
}
