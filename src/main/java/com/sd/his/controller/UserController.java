package com.sd.his.controller;

import com.sd.his.model.Contact;
import com.sd.his.model.Permission;
import com.sd.his.model.Role;
import com.sd.his.model.User;
import com.sd.his.model.wrapper.UserWrapper;
import com.sd.his.service.CustomConfigService;
import com.sd.his.service.HISUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private HISUserService userService;
    @Autowired
    private CustomConfigService customConfigService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SimpMessagingTemplate template;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listUser() {
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String create(@RequestBody UserWrapper user) {
        User usermodel = new User();
        usermodel.setEmail(user.getEmail());
        usermodel.setUsername("waqas");
        usermodel.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usermodel.setRole(userService.findRoleById(1));
        long timeMillis = System.currentTimeMillis();
        usermodel.setContact(new Contact(user.getFirstName(), user.getLastName(), user.getPhoneNumber(), true, false, user.getGender(),
                user.getAddress(), user.getCity(), user.getState(), user.getCountry(), timeMillis, timeMillis, usermodel, usermodel
        ));
        usermodel.setActive(true);
        userService.save(usermodel);
        return user.getEmail();
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        logger.info("token:logout" + authHeader);
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            logger.info("token:logout" + accessToken);
            tokenStore.removeAccessToken(accessToken);
        }
    }

    @DeleteMapping(path = {"user/{id}"})
    public User delete(@PathVariable("id") long id) {
        return userService.DeleteProduct(id);
    }


    @RequestMapping(value = "roles/all", method = RequestMethod.GET)
    public List<Role> listRoles() {
        return userService.allRoles();
    }

    @PostMapping("/addroles")
    public void saveRole(@RequestBody Role role) {
        List<Permission> obj = userService.findPermissionById(1);
        role.setPermissions(obj);
        role.setCreatedOn(System.currentTimeMillis());
        role.setDeleted(false);
        userService.saveRole(role);
    }

    @PostMapping("/addpermissions")
    public void savePersmission(@RequestBody Permission permission) {
        permission.setActive(true);
        permission.setCreatedOn(System.currentTimeMillis());
        permission.setDeleted(false);
        userService.savePermissions(permission);
    }

    @GetMapping("/allpermissions")
    public List<Permission> findAllPermission() {
        return userService.findAllPermissions();
    }


}

