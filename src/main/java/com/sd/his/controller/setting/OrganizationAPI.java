package com.sd.his.controller.setting;
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

import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.Branch;
import com.sd.his.model.Organization;
import com.sd.his.model.User;
import com.sd.his.repositories.OrganizationRepository;
import com.sd.his.request.BranchRequestWrapper;
import com.sd.his.request.OrganizationRequestWrapper;
import com.sd.his.response.BranchResponseWrapper;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.response.OrganizationResponseWrapper;
import com.sd.his.service.HISUserService;
import com.sd.his.service.OrganizationService;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.TimezoneWrapper;
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

@RestController
@RequestMapping("/setting/organization")
public class OrganizationAPI {

    @Autowired
    private HISUserService hisUserService;

    @Autowired
    private OrganizationService organizationService;

    private final Logger logger = LoggerFactory.getLogger(BranchAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @ApiOperation(httpMethod = "POST", value = "Create Organization",
            notes = "This method will Create Organization",
            produces = "application/json", nickname = "Create Organization",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Organization successfully created", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createOrganization(HttpServletRequest request,
                                                @RequestBody OrganizationRequestWrapper organizationRequestWrapper) {
        logger.info("Create Organization API called...");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("org.save.error"));
        response.setResponseCode(ResponseEnum.ORGANIZATION_SAVE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (HISCoreUtil.isNull(organizationRequestWrapper.getCompanyName()) ||
                    HISCoreUtil.isNull(organizationRequestWrapper.getUserName()) ||
                    HISCoreUtil.isNull(organizationRequestWrapper.getEmail())) {

                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                logger.error("createOrganization API - insufficient params");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            User userAlreadyExist = hisUserService.findByUserName(organizationRequestWrapper.getUserName());
            Organization orgAlreadyExist = organizationService.findOrgnazatinoByCompanyName(organizationRequestWrapper.getCompanyName());

            if (HISCoreUtil.isValidObject(userAlreadyExist) ||
                    HISCoreUtil.isValidObject(orgAlreadyExist)) {

                response.setResponseMessage(messageBundle.getString("org.already.exist.error"));
                response.setResponseCode(ResponseEnum.ORGANIZATION_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            Organization savedOrganization = organizationService.saveOrganization(organizationRequestWrapper);
            if (HISCoreUtil.isValidObject(savedOrganization)) {
                response.setResponseData(savedOrganization);
                response.setResponseMessage(messageBundle.getString("org.save.success"));
                response.setResponseCode(ResponseEnum.ORGANIZATION_SAVE_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("Organization created successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("Organization Creation Failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "All Organization",
            notes = "This method will return all Organization",
            produces = "application/json", nickname = "All Organization",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Organization fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/timezone", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTimezones(HttpServletRequest request) {

        logger.error("getAll Organization API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("timezone.fetch.error"));
        response.setResponseCode(ResponseEnum.TIMEZONE_FETCH_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAll Timezone API - Timezone fetching from DB");
            List<TimezoneWrapper> timezone = organizationService.getAllTimeZone();
            if (HISCoreUtil.isListEmpty(timezone)) {
                response.setResponseMessage(messageBundle.getString("timezone.not-found"));
                response.setResponseCode(ResponseEnum.TIMEZONE_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("Timezone API - Timezone not found");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.setResponseMessage(messageBundle.getString("timezone.fetch.success"));
            response.setResponseCode(ResponseEnum.TIMEZONE_FETCH_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(timezone);

            logger.error("getAllBranches API - Branches successfully fetched.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("getAllBranches API -  exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "All Branches",
            notes = "This method will return all Branches",
            produces = "application/json", nickname = "All Branches",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Organization fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBranches(HttpServletRequest request) {

        logger.error("getAllOrganization API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("organization.fetch.error"));
        response.setResponseCode(ResponseEnum.ORGANIZATION_FETCH_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAllOrganization API - org fetching from DB");
            List<OrganizationResponseWrapper> branches = organizationService.getAllActiveOrganizations();
            if (HISCoreUtil.isListEmpty(branches)) {
                response.setResponseMessage(messageBundle.getString("organization.not-found"));
                response.setResponseCode(ResponseEnum.ORGANIZATION_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("getAllOrganization API -Organization not found");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.setResponseMessage(messageBundle.getString("organization.fetch.success"));
            response.setResponseCode(ResponseEnum.ORGANIZATION_FETCH_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(branches);

            logger.error("getAllOrganization API - Organization successfully fetched.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("getAllOrganization API -  exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "Paginated Organization",
            notes = "This method will return Paginated Organization",
            produces = "application/json", nickname = "Get Paginated Organization ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated Organization fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaginatedOrganization(HttpServletRequest request,
                                                         @PathVariable("page") int page,
                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        logger.info("getAllOrganization paginated..");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("organization.not-found"));
        response.setResponseCode(ResponseEnum.ORGANIZATION_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<OrganizationResponseWrapper> organizationWrappers = organizationService.findAllPaginatedOrganization(page, pageSize);
            int countBranch = organizationService.totalOrganizations();

            if (!HISCoreUtil.isListEmpty(organizationWrappers)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (countBranch > pageSize) {
                    int remainder = countBranch % pageSize;
                    int totalPages = countBranch / pageSize;
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
                returnValues.put("data", organizationWrappers);

                response.setResponseMessage(messageBundle.getString("organization.fetch.success"));
                response.setResponseCode(ResponseEnum.ORGANIZATION_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("getAllOrganization Fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("get all paginated Organization failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(httpMethod = "GET", value = "Fetch Organization",
            notes = "This method will return Organization based on Id",
            produces = "application/json", nickname = "Get Single Organization",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Organization found successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrganizationById(HttpServletRequest request,
                                                 @PathVariable("id") long id) {

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("organization.not-found"));
        response.setResponseCode(ResponseEnum.ORGANIZATION_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            OrganizationResponseWrapper dbOrganization = this.organizationService.getOrganizationByIdWithResponse(id);

            if (HISCoreUtil.isValidObject(dbOrganization)) {
                response.setResponseData(dbOrganization);
                response.setResponseCode(ResponseEnum.ORGANIZATION_FETCH_SUCCESS.getValue());
                response.setResponseMessage(messageBundle.getString("organization.fetch.success"));
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("Organization Found successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Organization Not Found", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(httpMethod = "PUT", value = "Update Organization ",
            notes = "This method will Update Organization",
            produces = "application/json", nickname = "Update Organization",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Organization successfully updated", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOrganization(HttpServletRequest request,
                                                @PathVariable("id") int id,
                                                @RequestBody OrganizationRequestWrapper organizationRequestWrapper) {

        logger.info("update Organization API called..." + organizationRequestWrapper.getUserName());

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("organization.update.error"));
        response.setResponseCode(ResponseEnum.ORGANIZATION_UPDATE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            Organization alreadyExistOrganization = organizationService.getByID(id);
            if (HISCoreUtil.isValidObject(alreadyExistOrganization)) {
                logger.info("Organization founded...");
                OrganizationRequestWrapper organizationUpdated = organizationService.updateOrganization(organizationRequestWrapper, alreadyExistOrganization);
                if (HISCoreUtil.isValidObject(organizationUpdated)) {
                    logger.info("Organization Updated...");
                    response.setResponseData(organizationUpdated);
                    response.setResponseMessage(messageBundle.getString("organization.update.success"));
                    response.setResponseCode(ResponseEnum.ORGANIZATION_UPDATE_SUCCESS.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    logger.info("Organization updated successfully...");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                logger.info("Organization not found...");
                response.setResponseMessage(messageBundle.getString("organization.not-found"));
                response.setResponseCode(ResponseEnum.ORGANIZATION_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("Organization not updated...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception ex) {
            logger.error("Update Organization Failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}