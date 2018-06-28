package com.sd.his.controller.setting;

import com.sd.his.enums.ResponseEnum;
import com.sd.his.request.EmailTemplateRequest;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.service.EmailTemplateService;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.EmailTemplateWrapper;
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
 * @author    : Muhammad Jamal
 * @Date      : 21-May-18
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
 * @FileName  : Email Template
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@RestController
@RequestMapping("/setting/emailTemplate")
public class EmailTemplateAPI {

    Logger logger = LoggerFactory.getLogger(EmailTemplateAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @Autowired
    EmailTemplateService emailTemplateService;


    @ApiOperation(httpMethod = "GET", value = "Paginated Email Template",
            notes = "This method will return Paginated  Email Templates",
            produces = "application/json", nickname = "Paginated  Email Template",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated  Email Templates fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaginatedEmailTemplate(HttpServletRequest request,
                                                          @PathVariable("page") int page,
                                                          @RequestParam(value = "pageSize", required = false,
                                                                  defaultValue = "10") int pageSize) {

        logger.error("getAllPaginatedEmailTemplate API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("email.template.fetch.error"));
        response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_FETCH_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAllPaginatedEmailTemplate - fetching from DB");
            List<EmailTemplateWrapper> emailTemplates = emailTemplateService.findAllEmailTemplate(page, pageSize);
            int emailTemplateCount = emailTemplateService.countAllEmailTemplate();

            logger.error("getAllPaginatedEmailTemplate - fetched successfully");

            if (!HISCoreUtil.isListEmpty(emailTemplates)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (emailTemplateCount > pageSize) {
                    int remainder = emailTemplateCount % pageSize;
                    int totalPages = emailTemplateCount / pageSize;
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
                returnValues.put("data", emailTemplates);

                response.setResponseMessage(messageBundle.getString("email.template.fetch.success"));
                response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);

                logger.error("getAllPaginatedEmailTemplate API successfully executed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("getAllPaginatedEmailTemplate exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Search Clinical Department",
            notes = "This method will Search Clinical Department",
            produces = "application/json", nickname = "Search Clinical Department",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Search Clinical Department successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> searchEmailTemplate(HttpServletRequest request,
                                                      @PathVariable("page") int page,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                      @RequestParam(value = "title") String title) {

        logger.error("searchEmailTemplate API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("email.template.searched.error"));
        response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_SEARCHED_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (!HISCoreUtil.isValidObject(title)) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("searchEmailTemplate - insufficient params");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            List<EmailTemplateRequest> searchedEmailTemplates = emailTemplateService.searchEmailTemplateByTitle(page, pageSize, title);
            int countSearchedEmailTemplates = emailTemplateService.countSearchEmailTemplateByTitle(title);

            if (HISCoreUtil.isListEmpty(searchedEmailTemplates)) {

                response.setResponseMessage(messageBundle.getString("email.template.searched.not.found"));
                response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_SEARCHED_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("searchEmailTemplate - not found.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            Integer nextPage, prePage, currPage;
            int[] pages;

            if (countSearchedEmailTemplates > pageSize) {
                int remainder = countSearchedEmailTemplates % pageSize;
                int totalPages = countSearchedEmailTemplates / pageSize;
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
            returnValues.put("data", searchedEmailTemplates);

            response.setResponseMessage(messageBundle.getString("email.template.searched.success"));
            response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_SEARCHED_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(returnValues);
            logger.info("searchEmailTemplate - fetched successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("searchEmailTemplate exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete Email Template",
            notes = "This method will Delete the  Email Template",
            produces = "application/json", nickname = "Delete  Email Template ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted  Email Template successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmailTemplate(HttpServletRequest request,
                                                 @RequestParam("id") long id) {
        logger.info("deleteEmailTemplate API - Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("email.template.delete.error"));
        response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (id <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("deleteEmailTemplate API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            emailTemplateService.deleteById(id);
            response.setResponseMessage(messageBundle.getString("email.template.delete.success"));
            response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_DELETE_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(null);
            logger.info("deleteEmailTemplate API - Deleted Successfully...");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("deleteEmailTemplate API - deleted Exception.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Response with status of Email Template.
     * @author Muhammad Jamal
     * @description API will return status of Email Template creation.
     * @since 21-05-2018
     */
    @ApiOperation(httpMethod = "POST", value = "Create Email Template.",
            notes = "This method will Create Email Template.",
            produces = "application/json", nickname = "Create Email Template.",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Email Template successfully created", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveEmailTemplate(HttpServletRequest request,
                                               @RequestBody EmailTemplateRequest createRequest) {
        logger.info("saveEmailTemplate API called...");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("email.template.save.error"));
        response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (HISCoreUtil.isNull(createRequest.getTitle()) ||
                    HISCoreUtil.isNull(createRequest.getSubject()) ||
                    HISCoreUtil.isNull(createRequest.getEmailTemplate())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("saveEmailTemplate insufficient params");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (emailTemplateService.isDupTitle(createRequest.getTitle())) {
                response.setResponseMessage(messageBundle.getString("email.template.already"));
                response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_SAVE_ALREADY.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("saveEmailTemplate Duplicate Title");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            emailTemplateService.saveEmailTemplate(createRequest);

            response.setResponseData(null);
            response.setResponseMessage(messageBundle.getString("email.template.save.success"));
            response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_SAVE_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            logger.info("saveEmailTemplate created successfully...");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("saveEmailTemplate Exception.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "edit Email Template by id",
            notes = "This method will return Email Templates by id",
            produces = "application/json", nickname = "Email Template by id",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get Email Templates  by id successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmailTemplateById(HttpServletRequest request,
                                                  @PathVariable("id") long id) {

        logger.error("getEmailTemplateById API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("email.template.fetch.error"));
        response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_FETCH_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getEmailTemplateById - fetching from DB");
            EmailTemplateRequest emailTemplate = emailTemplateService.getEmailTemplateById(id);
            logger.error("getEmailTemplateById - fetched successfully");


            response.setResponseMessage(messageBundle.getString("email.template.fetch.success"));
            response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_FETCH_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(emailTemplate);

            logger.error("getEmailTemplateById API successfully executed.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("getEmailTemplateById exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return Response with status of Email Template.
     * @author Muhammad Jamal
     * @description API will return status of Email Template creation.
     * @since 22-05-2018
     */
    @ApiOperation(httpMethod = "POST", value = "Update Email Template.",
            notes = "This method will Update Email Template.",
            produces = "application/json", nickname = "Update Email Template.",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Email Template successfully Updated", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmailTemplate(HttpServletRequest request,
                                                 @RequestBody EmailTemplateRequest createRequest) {
        logger.info("updateEmailTemplate API called...");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("email.template.update.error"));
        response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_UPDATE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (createRequest.getId() <= 0 ||
                    HISCoreUtil.isNull(createRequest.getTitle()) ||
                    HISCoreUtil.isNull(createRequest.getSubject()) ||
                    HISCoreUtil.isNull(createRequest.getEmailTemplate())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                logger.error("updateEmailTemplate insufficient params");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (emailTemplateService.isDupTitleAgainstId(createRequest)) {
                response.setResponseMessage(messageBundle.getString("email.template.already"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updateEmailTemplate Duplicate Title");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (HISCoreUtil.isValidObject(emailTemplateService.updateEmailTemplate(createRequest))) {
                response.setResponseMessage(messageBundle.getString("email.template.update.success"));
                response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_UPDATE_SUCCESS.getValue());//EMAIL_TEMP_SUC_08
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("updateEmailTemplate updated successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseMessage(messageBundle.getString("email.template.update.already.deleted"));
                response.setResponseCode(ResponseEnum.EMAIL_TEMPLATE_UPDATE_ALREADY_DELETED.getValue());//EMAIL_TEMP_SUC_11
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("updateEmailTemplate updated successfully...");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("updateEmailTemplate Exception.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
