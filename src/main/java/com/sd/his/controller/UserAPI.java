package com.sd.his.controller;

import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.User;
import com.sd.his.request.PatientRequest;
import com.sd.his.response.AdminDashboardDataResponseWrapper;
import com.sd.his.response.GenericAPIResponse;
import com.sd.his.response.ProfileImageUploadResponse;
import com.sd.his.response.UserResponseWrapper;
import com.sd.his.service.AWSService;
import com.sd.his.service.HISUserService;
import com.sd.his.service.PatientService;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.PatientWrapper;
import com.sd.his.wrapper.UserCreateRequest;
import com.sd.his.wrapper.UserWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/user")
public class UserAPI {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private HISUserService userService;
    @Autowired
    private AWSService awsService;
    @Autowired
    PatientService patientService;

    private final Logger logger = LoggerFactory.getLogger(UserAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @ApiOperation(httpMethod = "GET", value = "Admin LoggedIn",
            notes = "This method will return logged in User",
            produces = "application/json", nickname = "Logging In ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Logged in Admin fetched", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/loggedInUser", method = RequestMethod.GET)
    public ResponseEntity<?> getLoggedInUser(HttpServletRequest request, Principal principal) {
        logger.info("LoggedIn User API - getLoggedInUser API initiated.");
        String name = principal.getName();

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("admin.loggedIn.fetched.error"));
        response.setResponseCode(ResponseEnum.ADMIN_LOGGEDIN_FETCHED_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (HISCoreUtil.isValidObject(name)) {
                logger.info("LoggedIn User API - fetching user from DB.");
                User user = userService.findByUserName(name);
                if (HISCoreUtil.isValidObject(user)) {
                    logger.info("LoggedIn User API - user successfully fetched...");
                    UserWrapper userWrapper = userService.buildLoggedInUserWrapper(user);

                    response.setResponseMessage(messageBundle.getString("admin.loggedIn.fetched.success"));
                    response.setResponseCode(ResponseEnum.ADMIN_LOGGEDIN_FETCHED_SUCCESS.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    response.setResponseData(userWrapper);

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        } catch (Exception ex) {
            logger.error("LoggedIn User API - getLoggedInUser failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Admin Logged out ",
            notes = "This method will Log out the User",
            produces = "application/json", nickname = "Logging Out ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User Logout success", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logOutUser(HttpServletRequest request) {
        logger.info("LogoutUser API initiated...");

        String authHeader = request.getHeader("Authorization");
        logger.info("Checking Request Header...:" + authHeader);

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.logout.error"));
        response.setResponseCode(ResponseEnum.USER_LOGGED_OUT_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(authHeader);

        try {
            if (!HISCoreUtil.isNull(authHeader)) {
                String tokenValue = authHeader.replace("Bearer", "").trim();
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
                tokenStore.removeAccessToken(accessToken);

                response.setResponseMessage(messageBundle.getString("user.logout.success"));
                response.setResponseCode(ResponseEnum.USER_LOGGED_OUT_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(null);

                logger.info("User logging out ...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("logOutUser failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "POST", value = "Create User ",
            notes = "This method will Create User",
            produces = "application/json", nickname = "Create User",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully created", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(HttpServletRequest request,
                                        @RequestBody UserCreateRequest createRequest) {

        long date = System.currentTimeMillis();
        logger.info("Create User API called..." + createRequest.getUserType());
        logger.info("Create User API called..." + createRequest.getUserName());
        createRequest.setCreatedOn(date);
        createRequest.setUpdatedOn(date);
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.add.error"));
        response.setResponseCode(ResponseEnum.USER_ADD_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {

            if (HISCoreUtil.isNull(createRequest.getUserType())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);

                logger.error("Create User insufficient params");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (!HISCoreUtil.isNull(createRequest.getUserType())) {
                User alreadyExist = userService.findByUserName(createRequest.getUserName());

                if (HISCoreUtil.isValidObject(alreadyExist)) {
                    response.setResponseMessage(messageBundle.getString("user.add.already-found.error"));
                    response.setResponseCode(ResponseEnum.USER_ALREADY_EXIST_ERROR.getValue());
                    response.setResponseStatus(ResponseEnum.ERROR.getValue());
                    response.setResponseData(null);
                    logger.error("User already exist with the same name...");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

                User savedUser = userService.saveUser(createRequest);
                if (HISCoreUtil.isValidObject(savedUser)) {
                    response.setResponseData(savedUser);
                    response.setResponseMessage(messageBundle.getString("user.add.success"));
                    response.setResponseCode(ResponseEnum.USER_ADD_SUCCESS.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    logger.info("User created successfully...");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        } catch (Exception ex) {
            logger.error("Create User Failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Paginated Users",
            notes = "This method will return Paginated Users",
            produces = "application/json", nickname = "Get Paginated Users ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated Users fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaginatedUsers(HttpServletRequest request,
                                                  @PathVariable("page") int page,
                                                  @RequestParam(value = "pageSize",
                                                          required = false, defaultValue = "10") int pageSize) {
        logger.info("getAllUsers paginated..");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.not.found"));
        response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            List<UserResponseWrapper> userWrappers = userService.findAllUsers(page, pageSize);
            int countUser = userService.totalUser();
            if (!HISCoreUtil.isListEmpty(userWrappers)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (countUser > pageSize) {
                    int remainder = countUser % pageSize;
                    int totalPages = countUser / pageSize;
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
                returnValues.put("data", userWrappers);

                response.setResponseMessage(messageBundle.getString("user.fetched.success"));
                response.setResponseCode(ResponseEnum.USER_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("getAllPaginatedUser Fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("get all paginated User failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "PUT", value = "Update User ",
            notes = "This method will Update User",
            produces = "application/json", nickname = "Update User",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully updated", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @PathVariable("id") int id,
                                        @RequestBody UserCreateRequest createRequest) {


        long date = System.currentTimeMillis();
        logger.info("update User API called..." + createRequest.getUserType());
        logger.info("update User API called..." + createRequest.getUserName());
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.update.error"));
        response.setResponseCode(ResponseEnum.USER_UPDATE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (!HISCoreUtil.isNull(createRequest.getUserType())) {
                User alreadyExistUser = userService.findById(id);

                if (HISCoreUtil.isValidObject(alreadyExistUser)) {
                    logger.info("User founded...");
                    User userUpdated = userService.updateUser(createRequest, alreadyExistUser);

                    if (HISCoreUtil.isValidObject(userUpdated)) {
                        logger.info("User saved...");
                        response.setResponseData(userUpdated);
                        response.setResponseMessage(messageBundle.getString("user.update.success"));
                        response.setResponseCode(ResponseEnum.USER_UPDATE_SUCCESS.getValue());
                        response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                        logger.info("User updated successfully...");

                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                } else {
                    logger.info("User not found...");
                    response.setResponseMessage(messageBundle.getString("user.not.found"));
                    response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
                    response.setResponseStatus(ResponseEnum.ERROR.getValue());
                    response.setResponseData(null);
                    logger.error("User not updated...");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("Update User insufficient params");
            }
        } catch (Exception ex) {
            logger.error("Update User Failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "User",
            notes = "This method will return User on base of id",
            produces = "application/json", nickname = "Get Single User",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(HttpServletRequest request,
                                         @PathVariable("id") long id
    ) {

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.not.found"));
        response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            UserResponseWrapper user = this.userService.findByIdAndResponse(id);

            if (HISCoreUtil.isValidObject(user)) {
                response.setResponseData(user);
                response.setResponseMessage(messageBundle.getString("user.found"));
                response.setResponseCode(ResponseEnum.USER_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("User Found successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("user.not.found"));
                response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                logger.info("User Not Found ...");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("User Not Found", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete User",
            notes = "This method will Delete User on base of id",
            produces = "application/json", nickname = "Delete Single User",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User Deleted successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(HttpServletRequest request,
                                        @PathVariable("id") long id) {

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.delete.error"));
        response.setResponseCode(ResponseEnum.USER_NOT_DELETED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            User user = this.userService.findById(id);
            if (HISCoreUtil.isValidObject(user)) {
                user = userService.deleteUser(user);
                if (user.isDeleted()) {
                    response.setResponseData(user);
                    response.setResponseMessage(messageBundle.getString("user.delete.success"));
                    response.setResponseCode(ResponseEnum.USER_DELETED_SUCCESS.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    logger.info("User Deleted successfully...");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("user.not.found"));
                response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                logger.info("User Not Found ...");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("User Not Deleted", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "Search User",
            notes = "This method will return User on base of search",
            produces = "application/json", nickname = "Search Users",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> searchUser(HttpServletRequest request,
                                        @PathVariable("page") int page,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                        @RequestParam(value = "name") String name,
                                        @RequestParam(value = "role") String role,
                                        @RequestParam(value = "email") String email) {
        logger.info("search:" + role);
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.not.found"));
        response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {

            List<UserWrapper> userWrappers = userService.searchByNameOrEmailOrRole(name, email, role, page, pageSize);

            int countUser = userService.totalUser();

            if (!HISCoreUtil.isListEmpty(userWrappers)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (countUser > pageSize) {
                    int remainder = countUser % pageSize;
                    int totalPages = countUser / pageSize;
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
                returnValues.put("data", userWrappers);

                response.setResponseMessage(messageBundle.getString("user.fetched.success"));
                response.setResponseCode(ResponseEnum.USER_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);
                logger.info("searched User Fetched successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("searched User failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "GET", value = "User By Role",
            notes = "This method will return Users By Role",
            produces = "application/json", nickname = "Get Users By role ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users fetched successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/role/", method = RequestMethod.GET)
    public ResponseEntity<?> findUserByRole(HttpServletRequest request,
                                            @RequestParam(value = "name") String role) {

        logger.info("find User By Role..");
        logger.info("user type..." + role);
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.not.found"));
        response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (!HISCoreUtil.isNull(role)) {
                List<UserResponseWrapper> userWrappers = userService.findByRole(role);

                if (!HISCoreUtil.isListEmpty(userWrappers)) {
                    response.setResponseMessage(messageBundle.getString("user.fetched.success"));
                    response.setResponseCode(ResponseEnum.USER_FOUND.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    response.setResponseData(userWrappers);
                    logger.info("user on base of role fetched successfully...");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("Create User insufficient params");

            }
        } catch (Exception ex) {
            logger.error("user by role failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "SuperAdmin dashboard data",
            notes = "This method will return super admin dashboard data",
            produces = "application/json", nickname = "Dashboard Data",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SuperAdmin Dashboard Data fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ResponseEntity<?> getAdminDashboardData(HttpServletRequest request, Principal principal) {
        logger.info("Administrator Dashboard Data - getAdminDashboardData API initiated.");
        String name = principal.getName();

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("admin.dashboard.data.fetched.error"));
        response.setResponseCode(ResponseEnum.ADMIN_DASHBOARD_FETCHED_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (HISCoreUtil.isValidObject(name)) {
                logger.info("Administrator Dashboard Data - fetching user from DB.");
                User user = userService.findByUserName(name);
                if (HISCoreUtil.isValidObject(user)) {
                    logger.info("Administrator Dashboard Data - user successfully fetched...");
                    AdminDashboardDataResponseWrapper adminData = userService.buildAdminDashboardData();
                    if (HISCoreUtil.isValidObject(adminData)) {
                        response.setResponseMessage(messageBundle.getString("admin.dashboard.data.fetched.success"));
                        response.setResponseCode(ResponseEnum.ADMIN_DASHBOARD_FETCHED_SUCCESS.getValue());
                        response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                        response.setResponseData(adminData);

                        return new ResponseEntity<>(response, HttpStatus.OK);
                    } else {
                        response.setResponseMessage(messageBundle.getString("admin.dashboard.data.fetched.error"));
                        response.setResponseCode(ResponseEnum.ADMIN_DASHBOARD_FETCHED_FAILED.getValue());
                        response.setResponseStatus(ResponseEnum.ERROR.getValue());
                        response.setResponseData(adminData);

                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Administrator Dashboard Data - getAdminDashboardData failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Upload Profile Image",
            notes = "This method will upload the profile image of any user.",
            produces = "application/json", nickname = "Upload Profile Image",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Profile image of user uploaded successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/uploadProfileImg/{id}", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"))
    public ResponseEntity<?> uploadProfileImage(HttpServletRequest request,
                                                @PathVariable("id") long id,
                                                @RequestParam("file") MultipartFile file) {
        logger.info("uploadProfileImage API called for user: " + id);
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.profile.image.uploaded.error"));
        response.setResponseCode(ResponseEnum.USER_PROFILE_IMG_UPLOAD_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            User user = userService.findUserById(id);

            if (HISCoreUtil.isValidObject(user)) {
                if (HISCoreUtil.isValidObject(file)) {
                    byte[] byteArr = file.getBytes();
                    InputStream is = new ByteArrayInputStream(byteArr);
                    Boolean isSaved = awsService.uploadImage(is, id);
                    if (isSaved) {
                        String imgURL = awsService.getProfileThumbnailImageUrl(id);
                        user.getProfile().setProfileImg(imgURL);
                        userService.updateUser(user);

                        response.setResponseMessage(messageBundle.getString("user.profile.image.uploaded.success"));
                        response.setResponseCode(ResponseEnum.USER_PROFILE_IMG_UPLOAD_SUCCESS.getValue());
                        response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                        response.setResponseData(new ProfileImageUploadResponse(user));

                        return new ResponseEntity<>(response, HttpStatus.OK);
                    } else {
                        response.setResponseMessage(messageBundle.getString("user.profile.invalid.media"));
                        response.setResponseCode(ResponseEnum.USER_PROFILE_INVALID_FILE_ERROR.getValue());
                        response.setResponseStatus(ResponseEnum.ERROR.getValue());
                        response.setResponseData(null);

                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseMessage(messageBundle.getString("user.profile.invalid.media"));
                    response.setResponseCode(ResponseEnum.USER_PROFILE_INVALID_FILE_ERROR.getValue());
                    response.setResponseStatus(ResponseEnum.ERROR.getValue());
                    response.setResponseData(null);

                }
                userService.updateUser(user);
            }
        } catch (Exception ex) {
            logger.error("Admin pr update failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Paginated Patients",
            notes = "This method will return Paginated Patients",
            produces = "application/json", nickname = "Paginated Patients",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated Patients fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/patient/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPaginatedUserByUserType(HttpServletRequest request,
                                                           @PathVariable("page") int page,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                           @RequestParam(value = "userType") String userType) {

        logger.error("getAllPaginatedUserByUserType API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("patient.fetch.error"));
        response.setResponseCode(ResponseEnum.PATIENT_FETCHED_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAllPaginatedUserByUserType -  fetching from DB");
            List<PatientWrapper> patients = userService.findAllPaginatedUserByUserType(page, pageSize, userType);
            int userCount = userService.countAllPaginatedPatients(userType);

            logger.error("getAllPaginatedUserByUserType - fetched successfully");

            if (!HISCoreUtil.isListEmpty(patients)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (userCount > pageSize) {
                    int remainder = userCount % pageSize;
                    int totalPages = userCount / pageSize;
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
                returnValues.put("data", patients);

                response.setResponseMessage(messageBundle.getString("patient.fetched.success"));
                response.setResponseCode(ResponseEnum.PATIENT_FETCHED_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);

                logger.error("getAllPaginatedUserByUserType API successfully executed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("getAllPaginatedUserByUserType exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Get All Patients",
            notes = "This method will return All Patients",
            produces = "application/json", nickname = "All Patients",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Patients fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/patient/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPatients(HttpServletRequest request) {

        logger.error("getAllPatients - API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("patient.fetch.error"));
        response.setResponseCode(ResponseEnum.PATIENT_FETCHED_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            logger.error("getAllPatients API -  fetching from DB");
            List<PatientWrapper> patients = userService.findAllPatients();

            if (HISCoreUtil.isListEmpty(patients)) {
                logger.error("getAllPatients - API Patients not found.");
                response.setResponseMessage(messageBundle.getString("patient.fetch.error"));
                response.setResponseCode(ResponseEnum.PATIENT_FETCHED_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
            }
            response.setResponseMessage(messageBundle.getString("patient.fetched.success"));
            response.setResponseCode(ResponseEnum.PATIENT_FETCHED_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(patients);

            logger.error("getAllPatients API - fetched successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("getAllPatients API - exception..", ex.fillInStackTrace());

            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete Patient",
            notes = "This method will Delete the Patient",
            produces = "application/json", nickname = "Delete Patient ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted Patient successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/patient/delete/{patientId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePatient(HttpServletRequest request,
                                           @PathVariable("patientId") long patientId) {
        logger.info("deletePatient API - Called..");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("patient.delete.error"));
        response.setResponseCode(ResponseEnum.PATIENT_DELETE_ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (patientId <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("deletePatient API - insufficient params.");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            userService.deletePatientById(patientId);
            response.setResponseMessage(messageBundle.getString("patient.delete.success"));
            response.setResponseCode(ResponseEnum.PATIENT_DELETE_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(null);
            logger.info("deleteServiceTax API - Deleted Successfully...");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("deletePatient API - deleted failed.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "POST", value = "Save Patient",
            notes = "This method will save the patient.",
            produces = "application/json", nickname = "Save Patient",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Save Patient successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public ResponseEntity<?> savePatient(HttpServletRequest request,
                                         @RequestBody PatientRequest patientRequest) {
        logger.info("savePatient API - initiated..");
        GenericAPIResponse response = new GenericAPIResponse();
        try {

            if (HISCoreUtil.isNull(patientRequest.getEmail()) ||
                    HISCoreUtil.isNull(patientRequest.getUserName()) ||
                    patientRequest.getUserName() == "" ||
                    HISCoreUtil.isNull(patientRequest.getCellPhone())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("savePatient API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (userService.isEmailAlreadyExists(patientRequest.getEmail())) {
                response.setResponseMessage(messageBundle.getString("user.add.email.already-found.error"));
                response.setResponseCode(ResponseEnum.USER_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("savePatient API - email already found.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (userService.isUserNameAlreadyExists(patientRequest.getUserName())) {
                response.setResponseMessage(messageBundle.getString("user.add.already-found.error"));
                response.setResponseCode(ResponseEnum.USER_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("savePatient API - user already found.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            userService.savePatient(patientRequest);
            response.setResponseMessage(messageBundle.getString("patient.save.success"));
            response.setResponseCode(ResponseEnum.PATIENT_SAVE_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(null);
            logger.error("savePatient API - successfully saved.");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("savePatient exception.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "User",
            notes = "This method will return User on base of id",
            produces = "application/json", nickname = "Get Single User",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/patient/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByIdAndByUserType(HttpServletRequest request,
                                                      @PathVariable("id") long id) {

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.not.found"));
        response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            PatientRequest user = this.userService.getUserByUserTypeAndId(id);
            if (HISCoreUtil.isValidObject(user)) {
                response.setResponseData(user);
                response.setResponseMessage(messageBundle.getString("user.found"));
                response.setResponseCode(ResponseEnum.USER_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("User Found successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("user.not.found"));
                response.setResponseCode(ResponseEnum.USER_NOT_FOUND.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                logger.info("User Not Found ...");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("User Not Found", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "POST", value = "Update Patient",
            notes = "This method will Update the patient.",
            produces = "application/json", nickname = "Update Patient",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update Patient successfully ", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/patient/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePatient(HttpServletRequest request,
                                           @RequestBody PatientRequest patientRequest) {
        logger.info("updatePatient API - initiated.");
        GenericAPIResponse response = new GenericAPIResponse();
        try {
            if (patientRequest.getUserId() <= 0) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updatePatient API - Please select proper user, userId not available with request patientRequest.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (HISCoreUtil.isNull(patientRequest.getEmail()) ||
                    HISCoreUtil.isNull(patientRequest.getUserName()) ||
                    patientRequest.getUserName() == "" ||
                    patientRequest.getSelectedDoctor() <= 0 ||
                    HISCoreUtil.isNull(patientRequest.getCellPhone())) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updatePatient API - insufficient params.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (userService.isEmailAlreadyExistsAgainstUserId(patientRequest.getUserId(), patientRequest.getEmail())) {
                response.setResponseMessage(messageBundle.getString("user.add.email.already-found.error"));
                response.setResponseCode(ResponseEnum.USER_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updatePatient API - user already found.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (userService.isUserNameAlreadyExistsAgainstUserId(patientRequest.getUserId(), patientRequest.getUserName())) {
                response.setResponseMessage(messageBundle.getString("user.add.already-found.error"));
                response.setResponseCode(ResponseEnum.USER_ALREADY_EXIST_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("updatePatient API - user already found.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            userService.updatePatient(patientRequest);
            response.setResponseMessage(messageBundle.getString("patient.update.success"));
            response.setResponseCode(ResponseEnum.PATIENT_UPDATE_SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(null);
            logger.error("updatePatient API - successfully updated.");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updatePatient exception.", e.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ApiOperation(httpMethod = "GET", value = "Paginated Patients Search",
            notes = "This method will return Paginated Patients Search",
            produces = "application/json", nickname = "Paginated Patients Search",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paginated Patients Search fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/patient/search{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getSearchAllPaginatedUserByUserType(HttpServletRequest request,
                                                                 @PathVariable("page") int page,
                                                                 @RequestParam(value = "pageSize",
                                                                         required = false,
                                                                         defaultValue = "10") int pageSize,
                                                                 @RequestParam(value = "userType") String userType,
                                                                 @RequestParam(value = "userName") String userName) {

        logger.error("getSearchAllPaginatedUserByUserType API initiated");
        GenericAPIResponse response = new GenericAPIResponse();


        try {
            logger.error("getSearchAllPaginatedUserByUserType -  fetching from DB");
            List<PatientWrapper> patients = userService.searchAllPaginatedUserByUserTypeAndName(page, pageSize, userType, userName);
            int userCount = userService.countSearchAllPaginatedUserByUserTypeAndName(userType, userName);

            logger.error("getSearchAllPaginatedUserByUserType - fetched successfully");

            if (!HISCoreUtil.isListEmpty(patients)) {
                Integer nextPage, prePage, currPage;
                int[] pages;

                if (userCount > pageSize) {
                    int remainder = userCount % pageSize;
                    int totalPages = userCount / pageSize;
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
                returnValues.put("data", patients);

                response.setResponseMessage(messageBundle.getString("patient.fetched.success"));
                response.setResponseCode(ResponseEnum.PATIENT_FETCHED_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(returnValues);

                logger.error("getSearchAllPaginatedUserByUserType API successfully executed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseMessage(messageBundle.getString("patient.fetch.error"));
                response.setResponseCode(ResponseEnum.PATIENT_FETCHED_ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception ex) {
            logger.error("getSearchAllPaginatedUserByUserType exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

