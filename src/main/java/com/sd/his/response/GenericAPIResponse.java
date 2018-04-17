package com.sd.his.response;

/*
 * @author    : Irfan Nasim
 * @Date      : 16-Apr-18
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
 * @FileName  : GenericAPIResponse
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */

public class GenericAPIResponse {

    private String responseCode;
    private Object responseData;
    private String responseMessage;
    private String errorMessageData;
    private String responseStatus;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getErrorMessageData() {
        return errorMessageData;
    }

    public void setErrorMessageData(String errorMessageData) {
        this.errorMessageData = errorMessageData;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
