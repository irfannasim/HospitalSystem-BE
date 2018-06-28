package com.sd.his.controller.setting;

import com.sd.his.controller.UserAuthAPI;
import com.sd.his.enums.AuthorityEnum;
import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.Permission;
import com.sd.his.model.Role;
import com.sd.his.request.AssignAuthoritiesRequestWrapper;
import com.sd.his.request.RoleAndPermissionCreateRequest;
import com.sd.his.request.RoleAndPermissionUpdateRequest;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.service.PermissionService;
import com.sd.his.service.RoleService;
import com.sd.his.utill.APIUtil;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.PermissionWrapper;
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
import java.util.List;
import java.util.ResourceBundle;

/*
 * @author    : Irfan Nasim
 * @Date      : 27-Apr-18
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
 * @FileName  : RoleAndPermissionAPI
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@RequestMapping("/setting/rolePermission")
@RestController
public class RoleAndPermissionAPI {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    private final Logger logger = LoggerFactory.getLogger(UserAuthAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    /**
     * @return Response with status of Role & Permission.
     * @author Irfan Nasim
     * @description API will return status of Role & Permission creation.
     * @since 27-04-2018
     */
    @ApiOperation(httpMethod = "POST", value = "Create Role & Permission",
            notes = "This method will Create Role & Permission",
            produces = "application/json", nickname = "Create Role & Permission",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Role & Permission successfully created", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoleAndPermission(HttpServletRequest request,
                                                  @RequestBody RoleAndPermissionCreateRequest createRequest) {


        long date = System.currentTimeMillis();
        logger.info("addRoleAndPermission API called...");
        createRequest.setCreatedOn(date);
        createRequest.setUpdatedOn(date);
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("role.add.error"));
        response.setResponseCode(ResponseEnum.ROLE_ADD_SUCCESS.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (HISCoreUtil.isNull(createRequest.getType())
                    || HISCoreUtil.isNull(createRequest.getName())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("Create Role & Permission insufficient params");
            }

            if (createRequest.getType().equalsIgnoreCase(AuthorityEnum.ROLE.getValue())) {
                Role alreadyExist = roleService.getRoleByName(createRequest.getName());

                if (HISCoreUtil.isValidObject(alreadyExist)) {
                    response.setResponseMessage(messageBundle.getString("role.add.already-found.error"));
                    response.setResponseCode(ResponseEnum.ROLE_ALREADY_EXIST_ERROR.getValue());
                    response.setResponseStatus(ResponseEnum.ERROR.getValue());
                    response.setResponseData(null);
                    logger.error("Role already exist with the same name...");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

                Role savedRole = roleService.save(createRequest);
                if (HISCoreUtil.isValidObject(savedRole)) {
                    response.setResponseData(null);
                    response.setResponseMessage(messageBundle.getString("role.add.success"));
                    response.setResponseCode(ResponseEnum.ROLE_ADD_SUCCESS.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    logger.info("Role created successfully...");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                Permission alreadyExist = permissionService.getPermissionByName(createRequest.getName());
                if (HISCoreUtil.isValidObject(alreadyExist)) {
                    response.setResponseMessage(messageBundle.getString("permission.add.already-found.error"));
                    response.setResponseCode(ResponseEnum.PERMISSION_ALREADY_EXIST_ERROR.getValue());
                    response.setResponseStatus(ResponseEnum.ERROR.getValue());
                    response.setResponseData(null);
                    logger.error("Permission already exist with the same name...");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

                Permission savedPermission = permissionService.save(createRequest);
                if (HISCoreUtil.isValidObject(savedPermission)) {
                    response.setResponseData(null);
                    response.setResponseMessage(messageBundle.getString("permission.add.success"));
                    response.setResponseCode(ResponseEnum.PERMISSION_ADD_SUCCESS.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    logger.info("Permission created successfully...");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

            }
        } catch (Exception ex) {
            logger.error("addRoleAndPermission failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @return Response with status of  Role & Permission update.
     * @author Irfan Nasim
     * @description API will return status of Role & Permission update.
     * @since 27-04-2018
     */
    @ApiOperation(httpMethod = "POST", value = "Update Role & Permission",
            notes = "This method will Update Role & Permission",
            produces = "application/json", nickname = "Update Role & Permission",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Role & Permission successfully updated", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateRoleAndPermission(HttpServletRequest request,
                                                     @RequestBody RoleAndPermissionUpdateRequest updateRequest) {

        logger.info("updateRoleAndPermission API called...");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("role.add.error"));
        response.setResponseCode(ResponseEnum.ROLE_ADD_SUCCESS.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {

        } catch (Exception ex) {
            logger.error("updateRoleAndPermission failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "All Systems Authorities",
            notes = "This API will return All Authorities like Roles and Permissions",
            produces = "application/json", nickname = "Authorities",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Authorities fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity<?> getRolesAndPermissions(HttpServletRequest request,
                                                    @RequestParam("name") String name) {

        logger.info("Get Permissions by Role api called...");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("permission.error"));
        response.setResponseCode(ResponseEnum.ROLE_PERMISSION_FETCH_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            Role role = roleService.getRoleByName(name);

            if (!HISCoreUtil.isValidObject(role)) {
                response.setResponseMessage(messageBundle.getString("role.not.found.error"));
                response.setResponseCode(ResponseEnum.NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("Role not found ");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            List<Permission> permissions = permissionService.getPermissionByRole(role.getId());

            if (HISCoreUtil.isListEmpty(permissions)) {
                response.setResponseMessage(messageBundle.getString("permission.not.found.error"));
                response.setResponseCode(ResponseEnum.NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("Permission not found against" + role.getName());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            List<PermissionWrapper> allPermissions = APIUtil.buildPermissionWrapper(permissions);
            if (!HISCoreUtil.isListEmpty(allPermissions)) {

                response.setResponseMessage(messageBundle.getString("permission.success"));
                response.setResponseCode(ResponseEnum.ROLE_PERMISSION_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(allPermissions);
                logger.info("permission fetched successfully");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("Get Permissions failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @return Response with Save Authorities.
     * @author Irfan Nasim
     * @description API will return response Authorities assigned or not.
     * @since 20-04-2018
     */
    @ApiOperation(httpMethod = "POST", value = "Assigned Authorities to Roles",
            notes = "This API will return Response Authorities are assigned or not",
            produces = "application/json", nickname = "Assignment of Authorities to Roles",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Authorities Assigned to roles successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/assignAuthorities", method = RequestMethod.POST)
    public ResponseEntity<?> assignAuthoritiesToRoles(HttpServletRequest request,
                                                      @RequestBody AssignAuthoritiesRequestWrapper authRequest) {

        logger.info("Assign Authorities to Roles api called...");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("role.permission.assign.error"));
        response.setResponseCode(ResponseEnum.ROLE_PERMISSION_ASSIGN_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (HISCoreUtil.isNull(authRequest.getSelectedRole())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("assignAuthoritiesToRoles insufficient params");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            Boolean isRolePermissionAssigned = roleService.assignPermissionsToRole(authRequest);
            if (isRolePermissionAssigned) {
                response.setResponseMessage(messageBundle.getString("role.permission.assigned.success"));
                response.setResponseCode(ResponseEnum.ROLE_PERMISSION_ASSIGN_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(null);
                logger.info("assignAuthoritiesToRoles assigned successfully");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("Assign Authorities to Roles api failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
