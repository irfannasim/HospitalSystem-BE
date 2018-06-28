package com.sd.his.controller.setting;

import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.MedicalService;
import com.sd.his.request.MedicalServiceRequest;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.service.MedicalServicesService;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.MedicalServiceWrapper;
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
 * @Date      : 15-May-18
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
 * @FileName  : ${FILE_NAME}
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@RestController
@RequestMapping("/setting/medicalService")
public class MedicalServiceAPI {

    Logger logger = LoggerFactory.getLogger(MedicalServiceAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @Autowired
    MedicalServicesService medicalServicesService;

    @ApiOperation(httpMethod = "GET", value = "Paginated Medical Services",
            notes = "This method will return Paginated Medical Services",
            produces = "application/json", nickname = "Paginated CMedical Services",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated Medical Services fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getPaginatedAllMedicalServices(HttpServletRequest request,
                                                            @PathVariable("page") int page,
                                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        logger.error("getPaginatedAllMedicalServices API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("med.service.fetch.error"));
        response.setResponseCode(ResponseEnum.MED_SERVICE_FETCH_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getPaginatedAllMedicalServices - Medical Services fetching from DB");
            List<MedicalServiceWrapper> mss = medicalServicesService.findAllPaginatedMedicalServices(page, pageSize);
            int mssCount = medicalServicesService.countAllMedicalServices();

            logger.error("getPaginatedAllMedicalServices - Medical Services fetched successfully");

            if (!HISCoreUtil.isListEmpty(mss)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (mssCount > pageSize) {
                    int remainder = mssCount % pageSize;
                    int totalPages = mssCount / pageSize;
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
                returnValues.put("data", mss);

                response.setResponseMessage(messageBundle.getString("med.service.fetch.success"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);

                logger.error("getPaginatedAllMedicalServices API successfully executed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("getPaginatedAllMedicalServices exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Paginated Medical Services",
            notes = "This method will return Paginated Medical Services",
            produces = "application/json", nickname = "Paginated CMedical Services",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated Medical Services fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMedicalServices(HttpServletRequest request) {

        logger.error("getAllMedicalServices API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("med.service.fetch.error"));
        response.setResponseCode(ResponseEnum.MED_SERVICE_FETCH_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAllMedicalServices - Medical Services fetching from DB");
            List<MedicalServiceWrapper> mss = medicalServicesService.findAllMedicalServices();

            logger.info("getAllMedicalServices - Medical Services fetched successfully"+ mss.size());
            response.setResponseMessage(messageBundle.getString("med.service.fetch.success"));
            response.setResponseCode(ResponseEnum.MED_SERVICE_FETCH_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(mss);

            logger.error("getAllMedicalServices API successfully executed.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("getAllMedicalServices exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @ApiOperation(httpMethod = "POST", value = "saveCode ",
            notes = "This method will Save the Medical Service",
            produces = "application/json", nickname = "Save Medical Service",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Medical Service Saved successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveMedicalService(HttpServletRequest request,
                                                @RequestBody MedicalServiceRequest createRequest) {
        logger.info("saveMedicalService API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("med.service.save.error"));
        response.setResponseCode(ResponseEnum.MED_SERVICE_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {

            if (HISCoreUtil.isNull(createRequest.getTitle()) ||
                    createRequest.getBranchId() <= 0 ||
                    createRequest.getDptId() <= 0 ||
                    createRequest.getFee() <= -1) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                logger.error("saveMedicalService API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            MedicalService alreadyMedicalService = medicalServicesService.findByTitleAndDeletedFalse(createRequest.getTitle());
            if (HISCoreUtil.isValidObject(alreadyMedicalService)) {
                response.setResponseMessage(messageBundle.getString("med.service.already.exist"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_ALREADY_EXIST.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());

                logger.error("saveMedicalService API - Already Exist.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            MedicalService newMedicalService = medicalServicesService.saveMedicalService(createRequest);
            if (HISCoreUtil.isValidObject(newMedicalService)) {
                response.setResponseData(newMedicalService);
                response.setResponseMessage(messageBundle.getString("med.service.save.success"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_SAVE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

                logger.error("saveMedicalService API - Successfully saved.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("saveMedicalService API - saving failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete Medical Services",
            notes = "This method will Delete Medical Services",
            produces = "application/json", nickname = "Delete Medical Services ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Medical Services deleted successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMedicalServices(HttpServletRequest request,
                                                   @RequestParam("msId") long msId,
                                                   @RequestParam("dptId") long dptId,
                                                   @RequestParam("branchId") long branchId) {
        logger.info("deleteMedicalServices API - Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("med.service.delete.error"));
        response.setResponseCode(ResponseEnum.MED_SERVICE_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (msId <= 0 || dptId <= 0 || branchId <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("deleteMedicalServices API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            String deletedStatus = medicalServicesService.deleteMedicalService(msId, dptId, branchId);
            if (deletedStatus.equalsIgnoreCase(ResponseEnum.SUCCESS.getValue())) {
                response.setResponseMessage(messageBundle.getString("med.service.delete.success"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_DELETE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(null);
                logger.info("deleteMedicalServices API - deleted Successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("deleteMedicalServices API - deletion failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Get Medical Service By Id",
            notes = "This method will return Paginated Medical Services",
            produces = "application/json", nickname = "Medical Service by Id",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Medical Service by Id fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMedicalServiceById(HttpServletRequest request,
                                                   @PathVariable("id") long id) {

        logger.error("getMedicalServiceById API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("med.service.fetch.error"));
        response.setResponseCode(ResponseEnum.MED_SERVICE_FETCH_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (id <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("getMedicalServiceById API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            logger.error("getMedicalServiceById - Medical Service fetching from DB");
            MedicalServiceWrapper mss = medicalServicesService.findOneByIdAndDeletedFalse(id);
            logger.error("getMedicalServiceById - Medical Service fetched successfully");

            if (HISCoreUtil.isValidObject(mss)) {
                response.setResponseMessage(messageBundle.getString("med.service.fetch.success"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(mss);

                logger.error("getMedicalServiceById API successfully executed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("getMedicalServiceById exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "POST", value = "update Medical Service ",
            notes = "This method will Update the Medical Service",
            produces = "application/json", nickname = "Update Medical Service",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Medical Service Updated successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMedicalService(HttpServletRequest request,
                                                  @RequestBody MedicalServiceRequest createRequest) {
        logger.info("updateMedicalService API initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("med.service.update.error"));
        response.setResponseCode(ResponseEnum.MED_SERVICE_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (HISCoreUtil.isNull(createRequest.getTitle()) ||
                    createRequest.getId() <= 0 ||
                    createRequest.getBranchId() <= 0 ||
                    createRequest.getDptId() <= 0 ||
                    createRequest.getFee() <= -1) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                logger.error("updateMedicalService API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (HISCoreUtil.isValidObject(medicalServicesService.findByTitleAndDeletedFalseAgainstId(createRequest.getId(), createRequest.getTitle()))) {
                response.setResponseMessage(messageBundle.getString("med.service.already.exist"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_ALREADY_EXIST.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());

                logger.error("updateMedicalService API - Already Exist.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (HISCoreUtil.isValidObject(medicalServicesService.updateMedicalService(createRequest))) {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("med.service.update.success"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_UPDATE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());

                logger.error("updateMedicalService API - Successfully saved.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateMedicalService API - updating failed.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Response with all search Filtered Medical Services.
     * @author Jamal
     * @description API will return detail of all filtered  Medical Services.
     * @since 17-05-2017
     */
    @ApiOperation(httpMethod = "GET", value = "Search  Medical Services ",
            notes = "This method will return Searched  Medical Services",
            produces = "application/json", nickname = "Get Searched  Medical Services",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Searched  Medical Services fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> searchMedicalServicesByParam(HttpServletRequest request,
                                                          @PathVariable("page") int pageNo,
                                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                          @RequestParam(value = "serviceId") long serviceId,
                                                          @RequestParam(value = "serviceName") String serviceName,
                                                          @RequestParam(value = "branchId") long branchId,
                                                          @RequestParam(value = "departmentId") long departId,
                                                          @RequestParam(value = "serviceFee") double serviceFee) {

        logger.info("searchMedicalServicesByParam API Called");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("med.service.search.not.found"));
        response.setResponseCode(ResponseEnum.MED_SERVICE_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {

            List<MedicalServiceWrapper> medS = this.medicalServicesService.searchMedicalServicesByParam(
                    (serviceId > 0 ? serviceId : null),
                    (serviceName.length() > 0 ? serviceName : null),
                    (branchId > 0 ? branchId : null),
                    (departId > 0 ? departId : null),
                    (serviceFee > 0 ? serviceFee : null),
                    pageNo, pageSize);
            int medServCount = this.medicalServicesService.countSearchMedicalServicesByParam(
                    (serviceId > 0 ? serviceId : null),
                    (serviceName.length() > 0 ? serviceName : null),
                    (branchId > 0 ? branchId : null),
                    (departId > 0 ? departId : null),
                    (serviceFee > 0 ? serviceFee : null));

            if (!HISCoreUtil.isListEmpty(medS)) {
                logger.info("searchMedicalServicesByParam fetched from DB successfully...");
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (medServCount > pageSize) {
                    int remainder = medServCount % pageSize;
                    int totalPages = medServCount / pageSize;
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
                returnValues.put("data", medS);

                response.setResponseMessage(messageBundle.getString("med.service.search.found"));
                response.setResponseCode(ResponseEnum.MED_SERVICE_SEARCH_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("searchMedicalServicesByParam fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("searchMedicalServicesByParam Exception.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
