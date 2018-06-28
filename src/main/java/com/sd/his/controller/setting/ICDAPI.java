package com.sd.his.controller.setting;

import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.ICDCode;
import com.sd.his.model.ICDCodeVersion;
import com.sd.his.model.ICDVersion;
import com.sd.his.request.ICDCodeCreateRequest;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.service.ICDService;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.ICDCodeVersionWrapper;
import com.sd.his.wrapper.ICDCodeWrapper;
import com.sd.his.wrapper.ICDVersionWrapper;
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
 * @author    : Irfan Nasim
 * @Date      : 26-Apr-18
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
 * @FileName  : ICDController
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@RequestMapping("/setting/icd")
@RestController
public class ICDAPI {

    @Autowired
    private ICDService iCDService;
    private final Logger logger = LoggerFactory.getLogger(ICDAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @ApiOperation(httpMethod = "GET", value = "versions",
            notes = "This method will return   Versions ",
            produces = "application/json", nickname = " versions",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "versions fetched", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/versions", method = RequestMethod.GET)
    public ResponseEntity<?> getAllICDVersions(HttpServletRequest request) {

        logger.info("getAllICDVersions API initiated..");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.versions.not.found"));
        response.setResponseCode(ResponseEnum.ICD_VERSION_FETCH_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ICD_VERSION_ERROR.getValue());
        response.setResponseData(null);

        try {

            logger.info("Versions Found Successfully");
            response.setResponseData(iCDService.versios());
            response.setResponseMessage(messageBundle.getString("icd.versions.found.success"));
            response.setResponseCode(ResponseEnum.ICD_VERSIONS_FETCH_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("getAllICDVersions failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "codes Not Deleted ",
            notes = "This method will return codes Not Deleted",
            produces = "application/json", nickname = "codes Not Deleted",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "codes Not Deleted fetched", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/codes", method = RequestMethod.GET)
    public ResponseEntity<?> getAllICDCodes(HttpServletRequest request) {

        logger.info("codesNotDeleted API initiated..");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.codes.not.found"));
        response.setResponseCode(ResponseEnum.ICD_VERSION_FETCH_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {

            logger.info("codes Not Deleted Found Successfully");
            response.setResponseData(iCDService.codesNotDeleted());
            response.setResponseMessage(messageBundle.getString("icd.codes.found.success"));
            response.setResponseCode(ResponseEnum.ICD_CODE_VERSION_FETCH_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("codesNotDeleted failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "get All Code Versions",
            notes = "This method will return code Versions",
            produces = "application/json", nickname = "codeVersions ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "code Versions fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/codeVersions/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCodeVersions(HttpServletRequest request,
                                                @PathVariable("page") int page,
                                                @RequestParam(value = "pageSize",
                                                        required = false, defaultValue = "10") int pageSize) {
        logger.info("getAllCodeVersions API Initiated..");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.code.version.not-found"));
        response.setResponseCode(ResponseEnum.ICD_CODE_VERSION_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<ICDCodeVersionWrapper> cvs = iCDService.codeVersions(page, pageSize);
            int countICDs = iCDService.countCodeVersions();

            if (!HISCoreUtil.isListEmpty(cvs)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (countICDs > pageSize) {
                    int remainder = countICDs % pageSize;
                    int totalPages = countICDs / pageSize;
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
                returnValues.put("data", cvs);

                response.setResponseMessage(messageBundle.getString("icd.code.version.found"));
                response.setResponseCode(ResponseEnum.ICD_CODE_VERSION_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("code Versions Fetched successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("fetching code Versions failed.", ex.fillInStackTrace());
            response.setResponseData(null);
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "POST", value = "saveCode ",
            notes = "This method will return Status of Code",
            produces = "application/json", nickname = "saveCode",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "save Code successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/code/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveICDCode(HttpServletRequest request,
                                         @RequestBody ICDCodeCreateRequest createRequest) {
        logger.info("saveCode API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseData(null);
        response.setResponseMessage(messageBundle.getString("icd.code.save.error"));
        response.setResponseCode(ResponseEnum.ICD_CODE_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());

        try {

            if (HISCoreUtil.isNull(createRequest.getCode())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("saveCode API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (iCDService.isICDCodeAlreadyExist(createRequest.getCode())) {
                response.setResponseMessage(messageBundle.getString("icd.code.already.exist"));
                response.setResponseCode(ResponseEnum.ICD_CODE_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ICDCode icd = iCDService.saveICD(createRequest);
            if (HISCoreUtil.isValidObject(icd)) {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("icd.code.save.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_SAVE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("saveCode API - saving failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "PUT", value = "Update ICD Code  ",
            notes = "This method will return Status of ICDCode",
            produces = "application/json", nickname = "Update  ICDCode",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update ICDCode  successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/code/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateICDCode(HttpServletRequest request,
                                           @RequestBody ICDCodeCreateRequest createRequest) {
        logger.info("updateICDCode API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseData(null);
        response.setResponseMessage(messageBundle.getString("icd.code.update.error"));
        response.setResponseCode(ResponseEnum.ICD_CODE_UPDATE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());

        try {

            if (createRequest.getId() <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updateICDCode API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (HISCoreUtil.isNull(createRequest.getCode())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updateICDCode API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (iCDService.isICDCodeAlreadyExistAgainstICDCodeId(createRequest.getCode(), createRequest.getId())) {
                response.setResponseMessage(messageBundle.getString("icd.code.already.exist"));
                response.setResponseCode(ResponseEnum.ICD_CODE_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            iCDService.updateICD(createRequest);
            response.setResponseData(null);
            response.setResponseMessage(messageBundle.getString("icd.update.success"));
            response.setResponseCode(ResponseEnum.ICD_CODE_UPDATE_SUCC.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateICDCode failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Response with all paginated ICDCode Versions.
     * @author Irfan Nasim
     * @description API will return detail of all ICDCode Versions paginated.
     * @since 30-04-2017
     */
    @ApiOperation(httpMethod = "GET", value = "Paginated ICDs",
            notes = "This method will return Paginated ICDs",
            produces = "application/json", nickname = "Get Paginated ICDs ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated ICDs fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/codes/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getPaginatedICDCodes(HttpServletRequest request,
                                                  @PathVariable("page") int page,
                                                  @RequestParam(value = "pageSize",
                                                          required = false, defaultValue = "10") int pageSize) {
        logger.info("get all ICD Code paginated..");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.not-found"));
        response.setResponseCode(ResponseEnum.ICD_CODE_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<ICDCodeWrapper> icdsWrappers = iCDService.findCodes(page, pageSize);
            int countICDs = iCDService.countCodes();

            if (!HISCoreUtil.isListEmpty(icdsWrappers)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (countICDs > pageSize) {
                    int remainder = countICDs % pageSize;
                    int totalPages = countICDs / pageSize;
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
                returnValues.put("data", icdsWrappers);

                response.setResponseMessage(messageBundle.getString("icd.fetched.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("ICD Code Fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("get all paginated ICD code failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Response with all search Filtered ICDs.
     * @author Jamal Nasim
     * @description API will return detail of all filtered ICDs.
     * @since 02-05-2017
     */
    @ApiOperation(httpMethod = "GET", value = "Search ICDs",
            notes = "This method will return Searched ICDs",
            produces = "application/json", nickname = "Get Searched ICDs ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Searched ICDs fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/code/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> searchICDCodes(HttpServletRequest request,
                                            @PathVariable("page") int pageNo,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                            @RequestParam(value = "code") String code) {

        logger.info("get all Searched ICDs");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.not-found"));
        response.setResponseCode(ResponseEnum.ICD_CODE_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<ICDCodeWrapper> icds = iCDService.searchCodes(code, pageNo, pageSize);
            int icdCount = iCDService.countSearchCodes(code);

            if (!HISCoreUtil.isListEmpty(icds)) {
                logger.info("ICDs fetched from DB successfully...");
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (icdCount > pageSize) {
                    int remainder = icdCount % pageSize;
                    int totalPages = icdCount / pageSize;
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
                returnValues.put("data", icds);

                response.setResponseMessage(messageBundle.getString("icd.fetched.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("All Searched ICDs fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("get all filtered admins failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete ICDCode",
            notes = "This method will return Deleted Status of ICDCode",
            produces = "application/json", nickname = "Delete ICDCode ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted ICDCode successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/code/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteICDCode(HttpServletRequest request,
                                           @RequestParam("codeId") long codeId) {
        logger.info("Delete ICDCode Api Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.delete.error"));
        response.setResponseCode(ResponseEnum.ICD_CODE_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (codeId <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("deleteICD API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (iCDService.deletedICD(codeId).equalsIgnoreCase(ResponseEnum.SUCCESS.getValue())) {
                response.setResponseMessage(messageBundle.getString("icd.delete.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_DELETE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(null);
                logger.info("ICDs Deleted Successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseMessage(messageBundle.getString("icd.already.deleted"));
                response.setResponseCode(ResponseEnum.NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("ICDs Already Deleted...");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Delete ICDCode failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "POST", value = "Save ICD Version ",
            notes = "This method will return Status while saving ICD Version",
            produces = "application/json", nickname = "Save ICD Version",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Save ICD Version successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/version/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveICDVersion(HttpServletRequest request,
                                            @RequestBody ICDVersionWrapper createRequest) {
        logger.info("saveICDVersion API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseData(null);
        response.setResponseMessage(messageBundle.getString("icd.version.save.error"));
        response.setResponseCode(ResponseEnum.ICD_VERSION_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());

        try {

            if (HISCoreUtil.isNull(createRequest.getName())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("saveICDVersion API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (iCDService.isICDVersionNameAlreadyExist(createRequest.getName())) {
                response.setResponseMessage(messageBundle.getString("icd.code.already.exist"));
                response.setResponseCode(ResponseEnum.ICD_VERSION_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                logger.error("saveICDVersion API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ICDVersion icd = iCDService.saveICDVersion(createRequest);
            if (HISCoreUtil.isValidObject(icd)) {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("icd.version.save.success"));
                response.setResponseCode(ResponseEnum.ICD_VERSION_SAVE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("saveICDVersion failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "Paginated Versions",
            notes = "This method will return Paginated  Versions",
            produces = "application/json", nickname = "Get Paginated  Versions",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated  Versions fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/versions/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaginatedVersions(HttpServletRequest request,
                                                     @PathVariable("page") int page,
                                                     @RequestParam(value = "pageSize",
                                                             required = false, defaultValue = "10") int pageSize) {
        logger.info("getAllPaginatedVersions API called..");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.version.not-found"));
        response.setResponseCode(ResponseEnum.ICD_VERSIONS_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<ICDVersionWrapper> icdsWrappers = iCDService.findVersions(page, pageSize);
            int countICDsVersion = iCDService.countVersion();

            if (!HISCoreUtil.isListEmpty(icdsWrappers)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (countICDsVersion > pageSize) {
                    int remainder = countICDsVersion % pageSize;
                    int totalPages = countICDsVersion / pageSize;
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
                returnValues.put("data", icdsWrappers);

                response.setResponseMessage(messageBundle.getString("icd.versions.fetched.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("getAllPaginatedICDVersions Fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("get All Paginated ICD Versions failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Response with all search Filtered ICDs.
     * @author Jamal
     * @description API will return detail of all filtered ICDs.
     * @since 02-05-2017
     */
    @ApiOperation(httpMethod = "GET", value = "Search ICDs Version",
            notes = "This method will return Searched ICDs Version",
            produces = "application/json", nickname = "Get Searched ICDs Version",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Searched ICDs  Version fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/version/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> searchVersions(HttpServletRequest request,
                                            @PathVariable("page") int pageNo,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                            @RequestParam(value = "searchVersion") String searchVersion) {

        logger.info("searchVersions API Called");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.versions.search.error"));
        response.setResponseCode(ResponseEnum.ICD_VERSIONS_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<ICDVersionWrapper> icds = iCDService.searchByVersion(searchVersion, pageNo, pageSize);
            int icdVersionCount = this.iCDService.countSearchByVersion(searchVersion);

            if (!HISCoreUtil.isListEmpty(icds)) {
                logger.info("ICDs Version fetched from DB successfully...");
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (icdVersionCount > pageSize) {
                    int remainder = icdVersionCount % pageSize;
                    int totalPages = icdVersionCount / pageSize;
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
                returnValues.put("data", icds);

                response.setResponseMessage(messageBundle.getString("icd.versions.search.success"));
                response.setResponseCode(ResponseEnum.ICD_VERSIONS_FOUND_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("searchICDVersion fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("searchICDVersion failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @return Response with all search Filtered ICDs Version.
     * @author Jamal
     * @description API will return detail of all filtered ICDs Version.
     * @since 03-05-2017
     */
    @ApiOperation(httpMethod = "PUT", value = "Update ICDVersion  ",
            notes = "This method will return Status while Updating  ICDVersion",
            produces = "application/json", nickname = "Update  ICDVersion",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update ICDVersion  successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/version/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateICDVersion(HttpServletRequest request,
                                              @RequestBody ICDVersionWrapper createRequest) {
        logger.info("updateICDVersion API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseData(null);
        response.setResponseMessage(messageBundle.getString("icd.version.update.error"));
        response.setResponseCode(ResponseEnum.ICD_VERSION_UPDATE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());

        try {

            if (createRequest.getId() <= 0) {
                response.setResponseMessage(messageBundle.getString("icd.version.update.required"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updateICDVersion API - version id not available.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (HISCoreUtil.isNull(createRequest.getName())) {
                response.setResponseMessage(messageBundle.getString("icd.version.update.name.required"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updateICDVersion API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (iCDService.isICDVersionNameAlreadyExistAgainstICDVersionNameId(createRequest.getName(), createRequest.getId())) {
                response.setResponseMessage(messageBundle.getString("icd.version.already.exist"));
                response.setResponseCode(ResponseEnum.ICD_CODE_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ICDVersion icdVersion = iCDService.updateICDVersion(createRequest);
            if (HISCoreUtil.isValidObject(icdVersion)) {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("icd.version.update.success"));
                response.setResponseCode(ResponseEnum.ICD_VERSION_UPDATE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateICD Version failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(httpMethod = "DELETE", value = "Delete ICD Version",
            notes = "This method will return Deleted Status of  ICD Version",
            produces = "application/json", nickname = "Delete  ICD Version ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted  ICD Version successful", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/version/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteICDVersion(HttpServletRequest request,
                                              @RequestParam("iCDVersionId") long iCDVersionId) {
        logger.info("deleteICDVersion Api Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.version.delete.error"));
        response.setResponseCode(ResponseEnum.ICD_VERSION_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (iCDVersionId <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("deleteICDVersion API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (iCDService.deletedICDVersion(iCDVersionId).equalsIgnoreCase(ResponseEnum.SUCCESS.getValue())) {
                response.setResponseMessage(messageBundle.getString("icd.version.delete.success"));
                response.setResponseCode(ResponseEnum.ICD_VERSION_DELETE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(null);
                logger.info("deleteICDVersion Successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("Delete deleteICDVersion failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "POST", value = "saveCodeVersion",
            notes = "This method will return Status while saving CodeVersion",
            produces = "application/json", nickname = "saveCodeVersion",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "saveCodeVersion  successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/codeVersion/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveCodeVersion(HttpServletRequest request,
                                             @RequestBody ICDCodeVersionWrapper createRequest) {
        logger.info("saveCodeVersion API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseData(null);
        response.setResponseMessage(messageBundle.getString("icd.code.version.save.error"));
        response.setResponseCode(ResponseEnum.ICD_CODE_VERSION_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());

        try {

            if (HISCoreUtil.isListEmpty(createRequest.getiCDCodes()) ||
                    !HISCoreUtil.isValidObject(createRequest.getSelectedICDVersionId())) {
                response.setResponseMessage(messageBundle.getString("icd.manage.list.empty"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("saveCodeVersion API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            List<ICDCodeVersion> icdCodeVersions = iCDService.saveAssociateICDCVs(createRequest);
            if (HISCoreUtil.isValidObject(icdCodeVersions)) {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("icd.code.version.save.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_VERSION_SAVE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("saveCodeVersion failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "DELETE", value = "deleteCodeVersion",
            notes = "This method will return delete Code Version STATUS",
            produces = "application/json", nickname = "deleteCodeVersion",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "delete Code Version successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/codeVersion/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCodeVersion(HttpServletRequest request,
                                               @RequestParam("associateICDCVId") long associateICDCVId) {
        logger.info("deleteCodeVersion Api Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.code.version.delete.error"));
        response.setResponseCode(ResponseEnum.ICD_CODE_VERSION_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (associateICDCVId <= 0) {
                response.setResponseMessage(messageBundle.getString("icd.version.required"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("deleteAssociateICDCV API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (iCDService.deletedAssociateICDCV(associateICDCVId).equalsIgnoreCase(ResponseEnum.SUCCESS.getValue())) {
                response.setResponseMessage(messageBundle.getString("icd.code.version.delete.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_VERSION_DELETE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(null);
                logger.info("deleteAssociateICDCV Successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("deleteAssociateICDCV failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "DELETE", value = "get associated ICDCV",
            notes = "This method will return associated Status of   associate ICDCVs",
            produces = "application/json", nickname = "Associated ICDCVs ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get Associated ICDCVs successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/version/codes", method = RequestMethod.GET)
    public ResponseEntity<?> getCodesByVersion(HttpServletRequest request,
                                               @RequestParam("versionId") long versionId) {
        logger.info("getAssociatedICDCVAgainstICDVId Api Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.associated.not.found"));
        response.setResponseCode(ResponseEnum.ICD_CODE_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (versionId <= 0) {
                response.setResponseMessage(messageBundle.getString("icd.version.required"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("getAssociatedICDCVAgainstICDVId API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            List<ICDCodeWrapper> list = iCDService.getAssociatedICDCVByVId(versionId);
            if (!HISCoreUtil.isListEmpty(list)) {
                response.setResponseMessage(messageBundle.getString("icd.associated.found"));
                response.setResponseCode(ResponseEnum.ICD_ASSOCIATED_FOUND_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(list);
                logger.info("Associated found  Successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("Delete ICDVersion failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Response with all search Filtered Code Version by Version.
     * @author Jamal
     * @description API will get data from  icd_code_version table by Version Name.
     * @since 09-05-2017
     */
    @ApiOperation(httpMethod = "GET", value = "Search Code Version by Version",
            notes = "This method will return Searched Code Version by Version",
            produces = "application/json", nickname = "Get Searched Code Version by Version ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Searched Code Version by Version fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/codeVersion/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> searchCodeVersionByParam(HttpServletRequest request,
                                                      @PathVariable("page") int page,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                      @RequestParam(value = "versionName") String versionName,
                                                      @RequestParam(value = "searchCodeVersionCode") String searchCode) {

        logger.info("get all Searched  Code Version By Version Name");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("icd.not-found"));
        response.setResponseCode(ResponseEnum.ICD_CODE_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<ICDCodeVersionWrapper> searchedCodeVersions = iCDService.searchCodeVersionByVersionName(page, pageSize, versionName,searchCode);
            int icdCount = iCDService.countSearchCodeVersionByVersionName(versionName,searchCode);

            if (!HISCoreUtil.isListEmpty(searchedCodeVersions)) {
                logger.info("search Code Version By VersionName fetched from DB successfully...");
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (icdCount > pageSize) {
                    int remainder = icdCount % pageSize;
                    int totalPages = icdCount / pageSize;
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
                returnValues.put("data", searchedCodeVersions);

                response.setResponseMessage(messageBundle.getString("icd.fetched.success"));
                response.setResponseCode(ResponseEnum.ICD_CODE_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("All search Code Version By VersionName fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("search Code Version By VersionName failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
