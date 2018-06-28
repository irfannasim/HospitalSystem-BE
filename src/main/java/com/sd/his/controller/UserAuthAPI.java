package com.sd.his.controller;

import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.Permission;
import com.sd.his.model.Role;
import com.sd.his.model.User;
import com.sd.his.request.AdminLoginRequestWrapper;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.service.HISUserService;
import com.sd.his.service.PermissionService;
import com.sd.his.service.RoleService;
import com.sd.his.utill.APIUtil;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.PermissionWrapper;
import com.sd.his.wrapper.RoleWrapper;
import com.sd.his.wrapper.UserWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
 * @Package   : com.sd.his.controller
 * @FileName  : UserAuthAPI
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@RequestMapping("/user/auth")
@RestController
public class UserAuthAPI {

    @Autowired
    private HISUserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    private final Logger logger = LoggerFactory.getLogger(UserAuthAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    /**
     * @return Response with Admin detail.
     * @author Irfan Nasim
     * @description API will return admin detail.
     * @since 16-04-2018
     */
    @ApiOperation(httpMethod = "POST", value = "Admin LoggedIn",
            notes = "This method will return logged in Admin",
            produces = "application/json", nickname = "Logging In Admin",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Logged in Admin fetched", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(HttpServletRequest request,
                                    @RequestBody AdminLoginRequestWrapper loginReq) {

        String loggedInUser = request.getRemoteUser().toString();
        logger.info("Sign in up Admin requested by User Name: " + loggedInUser);
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("admin.login.error"));
        response.setResponseCode(ResponseEnum.ADMIN_LOGGEDIN_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            // get requested user
            User dbAdmin = userService.findByUsernameOrEmailAndActiveTrueAndDeletedFalse(loggedInUser, loggedInUser);

            if (!HISCoreUtil.isValidObject(dbAdmin)) {
                response.setResponseMessage(messageBundle.getString("admin.not.found"));
                response.setResponseCode(ResponseEnum.ADMIN_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("The Admin is not found...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            /*if (!SecurityUtil.hasRole(UserRoleEnum.SUPER_ADMIN.getValue(), dbAdmin) &&
                    !SecurityUtil.hasRole(UserRoleEnum.ADMIN.getValue(), dbAdmin)) {
                logger.info("User fetched from DB...");
                response.setResponseMessage(messages.get("invalid.portal.login"));
                response.setResponseCode(ResponseEnum.WRONG_PORTAL.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("User fetched from DB not Admin...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (dbAdmin.getContact().getLocked()) {
                response.setResponseMessage(messages.get("admin.locked"));
                response.setResponseCode(ResponseEnum.ADMIN_LOCKED.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("Admin Locked...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (!dbAdmin.getContact().getActive()) {
                response.setResponseMessage(messages.get("admin.inactive"));
                response.setResponseCode(ResponseEnum.ADMIN_INACTIVE.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("Admin Inactive...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (dbAdmin.getContact().getBlocked()) {
                response.setResponseMessage(messages.get("admin.blocked"));
                response.setResponseCode(ResponseEnum.ADMIN_BLOCKED.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("Admin Suspended...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
*/
            if (HISCoreUtil.isValidObject(dbAdmin)) {
                if (BCrypt.checkpw(loginReq.getPassword(), dbAdmin.getPassword())) {

                    UserWrapper admin = userService.buildUserWrapper(dbAdmin);
                    response.setResponseData(admin);
                    response.setResponseMessage(messageBundle.getString("admin.login.success"));
                    response.setResponseCode(ResponseEnum.ADMIN_ACCESS_GRANTED.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    logger.info("Admin Logged in successfully...");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        } catch (Exception ex) {
            logger.error("Admin Logged In failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @return Response with System Authorities.
     * @author Irfan Nasim
     * @description API will return All Authorities like Roles and Permissions.
     * @since 20-04-2018
     */
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
    @RequestMapping(value = "/authorities", method = RequestMethod.GET)
    public ResponseEntity<?> getRolesAndPermissions(HttpServletRequest request) {

        logger.info("Get Roles and Permissions api called...");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("role.permissions.error"));
        response.setResponseCode(ResponseEnum.ROLE_PERMISSION_FETCH_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<Role> dbRoles = roleService.getAllActiveRoles();
            List<Permission> dbPermissions = permissionService.getAllActivePermissions();
            Map<String, Object> returnValues = new LinkedHashMap<>();

            if (HISCoreUtil.isListEmpty(dbRoles)) {
                response.setResponseMessage(messageBundle.getString("role.permissions.not.found.error"));
                response.setResponseCode(ResponseEnum.NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.info("Role and Permission not found");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            List<RoleWrapper> allRolesAndPermissions = APIUtil.buildRoleWrapper(dbRoles);
            List<PermissionWrapper> allPermissions = APIUtil.buildPermissionWrapper(dbPermissions);
            if (!HISCoreUtil.isListEmpty(allRolesAndPermissions)) {
                returnValues.put("allRoleAndPermissions", allRolesAndPermissions);
                returnValues.put("allPermissions", allPermissions);

                response.setResponseMessage(messageBundle.getString("role.permissions.success"));
                response.setResponseCode(ResponseEnum.ROLE_PERMISSION_FETCH_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("All roles and permission fetched successfully");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("Get Roles and Permissions failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
