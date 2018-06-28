package com.sd.his.controller.setting;

import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.Tax;
import com.sd.his.request.SaveTaxRequest;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.service.TaxService;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.TaxWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/*
 * @author    : irfan
 * @Date      : 14-May-18
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
 * @Package   : com.sd.his.controller.setting
 * @FileName  : TaxAPI
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@RestController
@RequestMapping("/setting/tax")
public class TaxAPI {

    Logger logger = LoggerFactory.getLogger(TaxAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @Autowired
    TaxService taxService;

    @ApiOperation(httpMethod = "GET", value = "All Services Tax",
            notes = "This method will return All Service Tax",
            produces = "application/json", nickname = "All Service Tax",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Service Tax fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllServiceTax(HttpServletRequest request) {

        logger.error("getAllServiceTax API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("service.tax.fetch.error"));
        response.setResponseCode(ResponseEnum.SERVICE_TAX_FETCH_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAllServiceTax - service tax fetching from DB");
            List<TaxWrapper> taxes = taxService.findAllActiveTax();
            logger.error("getAllServiceTax - tax fetched successfully");

            if (HISCoreUtil.isListEmpty(taxes)) {
                response.setResponseMessage(messageBundle.getString("service.tax.not.found.error"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("getAllServiceTax API - Taxes not found");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.setResponseMessage(messageBundle.getString("service.tax.fetch.success"));
            response.setResponseCode(ResponseEnum.SERVICE_TAX_FETCH_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(taxes);

            logger.error("getAllServiceTax API successfully executed.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("getAllServiceTax exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "Paginated Services Tax",
            notes = "This method will return Paginated Service Tax",
            produces = "application/json", nickname = "Paginated Service Tax",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated Service Tax fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaginatedServiceTax(HttpServletRequest request,
                                                       @PathVariable("page") int page,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        logger.error("getAllPaginatedServiceTax API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("service.tax.fetch.error"));
        response.setResponseCode(ResponseEnum.SERVICE_TAX_FETCH_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAllPaginatedServiceTax - service tax fetching from DB");
            List<TaxWrapper> taxes = taxService.findAllPaginatedTax(page, pageSize);
            int taxCount = taxService.countAllTax();

            logger.error("getAllPaginatedServiceTax - tax fetched successfully");

            if (!HISCoreUtil.isListEmpty(taxes)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (taxCount > pageSize) {
                    int remainder = taxCount % pageSize;
                    int totalPages = taxCount / pageSize;
                    if (remainder > 0) {
                        totalPages = totalPages + 1;
                    }
                    pages = new int[totalPages];
                    pages = IntStream.range(0, totalPages).toArray();
                    currPage = page;
                    nextPage = (currPage + 1) != totalPages ? currPage + 1 : null;
                    prePage = currPage > 0 ? currPage : null;
                } else {
                    pages = new int[1];
                    pages[0] = 0;
                    currPage = 0;
                    nextPage = null;
                    prePage = null;
                }

                Map<String, Object> returnValues = new LinkedHashMap<>();
                returnValues.put("nextPage", nextPage);
                returnValues.put("prePage", prePage);
                returnValues.put("currPage", currPage);
                returnValues.put("pages", pages);
                returnValues.put("data", taxes);

                response.setResponseMessage(messageBundle.getString("service.tax.fetch.success"));
                response.setResponseCode(ResponseEnum.SERVICE_TAX_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);

                logger.error("getAllPaginatedServiceTax API successfully executed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("getAllPaginatedServiceTax exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete Service Tax",
            notes = "This method will Delete the Service Tax",
            produces = "application/json", nickname = "Delete Service Tax ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted Service Tax successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceTax(HttpServletRequest request,
                                              @RequestParam("taxId") long taxId) {
        logger.info("deleteServiceTax API - Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("service.tax.delete.error"));
        response.setResponseCode(ResponseEnum.SERVICE_TAX_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (taxId <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("deleteServiceTax API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Tax dbTax = taxService.findTaxById(taxId);
            if (!HISCoreUtil.isValidObject(dbTax)) {
                response.setResponseMessage(messageBundle.getString("service.tax.not.found.error"));
                response.setResponseCode(ResponseEnum.SERVICE_TAX_NOT_FOUND_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("deleteServiceTax API - tax not found...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            taxService.deleteTax(dbTax);
            response.setResponseMessage(messageBundle.getString("service.tax.delete.success"));
            response.setResponseCode(ResponseEnum.SERVICE_TAX_DELETE_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(null);
            logger.info("deleteServiceTax API - Deleted Successfully...");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("deleteServiceTax API - deleted failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "POST", value = "Save Service Tax",
            notes = "This method will save the service tax.",
            produces = "application/json", nickname = "Save Service Tax",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Save Service Tax successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveServiceTax(HttpServletRequest request,
                                            @RequestBody SaveTaxRequest taxWrapper) {
        logger.info("saveServiceTax API - initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseData(null);
        response.setResponseMessage(messageBundle.getString("service.tax.save.error"));
        response.setResponseCode(ResponseEnum.SERVICE_TAX_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());

        try {

            if (HISCoreUtil.isNull(taxWrapper.getName()) ||
                    HISCoreUtil.isNull(taxWrapper.getFromDate()) ||
                    HISCoreUtil.isNull(taxWrapper.getToDate())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                logger.error("saveServiceTax API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (taxService.isAlreadyExist(taxWrapper)) {
                response.setResponseMessage(messageBundle.getString("service.tax.already.exist"));
                response.setResponseCode(ResponseEnum.SERVICE_TAX_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                logger.error("saveServiceTax API - Tax already exist with same name.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (HISCoreUtil.isValidObject(taxService.saveTax(taxWrapper))) {
                response.setResponseMessage(messageBundle.getString("service.tax.save.success"));
                response.setResponseCode(ResponseEnum.SERVICE_TAX_SAVE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(null);

                logger.error("saveServiceTax API - Tax successfully saved.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveServiceTax exception.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "PUT", value = "Update Tax Service",
            notes = "This method will update Tax Service",
            produces = "application/json", nickname = "Update Tax Service",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tax service updated successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTaxService(HttpServletRequest request,
                                              @RequestBody SaveTaxRequest updateRequest) {
        logger.info("updateTaxService API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseData(null);
        response.setResponseMessage(messageBundle.getString("service.tax.update.error"));
        response.setResponseCode(ResponseEnum.ICD_CODE_UPDATE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());

        try {
            if (updateRequest.getId() <= 0 ||
                    HISCoreUtil.isNull(updateRequest.getFromDate()) ||
                    HISCoreUtil.isNull(updateRequest.getToDate())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updateTaxService API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (taxService.isAlreadyExist(updateRequest)) {
                response.setResponseMessage(messageBundle.getString("service.tax.already.exist"));
                response.setResponseCode(ResponseEnum.SERVICE_TAX_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                logger.error("updateICDCode API - Tax already exist with same name.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            taxService.updateTaxService(updateRequest);
            response.setResponseData(null);
            response.setResponseMessage(messageBundle.getString("service.tax.update.success"));
            response.setResponseCode(ResponseEnum.SERVICE_TAX_UPDATE_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateICDCode API - update failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "GET Search Taxes",
            notes = "This method will return Searched Tax",
            produces = "application/json", nickname = "Searched Taxes ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Searched Taxes fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> searchTaxByName(HttpServletRequest request,
                                             @PathVariable("page") int pageNo,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                             @RequestParam(value = "searchTax") String searchTaxName) {

        logger.info("searchTaxByName initiated");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.not-found"));
        response.setResponseCode(ResponseEnum.ICD_CODE_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<SaveTaxRequest> taxes = taxService.searchByTaxByName(searchTaxName, pageNo, pageSize);
            int taxesCount = taxService.countSearchByTaxByName(searchTaxName);

            if (!HISCoreUtil.isListEmpty(taxes)) {
                logger.info("searchTaxByName fetched from DB successfully...");
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (taxesCount > pageSize) {
                    int remainder = taxesCount % pageSize;
                    int totalPages = taxesCount / pageSize;
                    if (remainder > 0) {
                        totalPages = totalPages + 1;
                    }
                    pages = new int[totalPages];
                    pages = IntStream.range(0, totalPages).toArray();
                    currPage = pageNo;
                    nextPage = (currPage + 1) != totalPages ? currPage + 1 : null;
                    prePage = currPage > 0 ? currPage : null;
                } else {
                    pages = new int[1];
                    pages[0] = 0;
                    currPage = 0;
                    nextPage = null;
                    prePage = null;
                }

                Map<String, Object> returnValues = new LinkedHashMap<>();
                returnValues.put("nextPage", nextPage);
                returnValues.put("prePage", prePage);
                returnValues.put("currPage", currPage);
                returnValues.put("pages", pages);
                returnValues.put("data", taxes);

                response.setResponseMessage(messageBundle.getString("service.tax.fetched.success"));
                response.setResponseCode(ResponseEnum.SERVICE_TAX_SEARCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("All searched taxes fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("get all filtered Search Taxes failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
