package com.sd.his.configuration;

import com.sd.his.model.S3Bucket;
import com.sd.his.model.User;
import com.sd.his.service.HISUserService;
import com.sd.his.service.S3BucketService;
import com.sd.his.utill.HISConstants;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.utill.ResourceCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;

/*
 * @author    : Irfan Nasim
 * @Date      : 26-Jun-18
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
 * @Package   : com.sd.his.configuration
 * @FileName  : S3KeyGen
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Component
public class S3KeyGen {

    public static final Logger logger = LoggerFactory.getLogger(S3KeyGen.class);

    @Autowired
    HISUserService userService;
    @Autowired
    S3BucketService s3BucketService;

    /**
     * Generates the S3 Key name for a profile graphic.
     *
     * @param email
     * @return If a valid email is provided an array is returned containing the
     * s3 bucket at index [0] and the object key at index [1]. If the email is
     * invalid null is returned. IMPORANT: No guarantee is provided that the
     * resource exists! This is simply the theoretical path to the resource.
     */
    public String[] profileGraphic(String email) throws NamingException {
        User user = userService.findByUsernameOrEmail(email, email);
        S3Bucket s3Bucket = s3BucketService.findActiveBucket();

        String key = HISConstants.S3_USER_DIRECTORY
                + "/"
                + HISConstants.S3_USER_PROFILE_DIRECTORY
                + "/"
                + user.getId() + "_" + user.getProfile().getId() + "_"
                + HISConstants.S3_USER_PROFILE_GRAPHIC_NAME;

        String[] completeKey = {s3Bucket.getName(), key};
        return completeKey;
    }


    /**
     * Generates the S3 Key name for a profile graphic.
     *
     * @param id
     * @return If a valid email is provided an array is returned containing the
     * s3 bucket at index [0] and the object key at index [1]. If the email is
     * invalid null is returned. IMPORANT: No guarantee is provided that the
     * resource exists! This is simply the theoretical path to the resource.
     */
    public String[] profileGraphic(Long id) throws NamingException {

        User user = userService.findById(id);
        S3Bucket s3Bucket = s3BucketService.findActiveBucket();

        String key = HISConstants.S3_USER_DIRECTORY
                + "/"
                + HISConstants.S3_USER_PROFILE_DIRECTORY
                + "/"
                + user.getId() + "_" + user.getProfile().getId() + "_"
                + HISConstants.S3_USER_PROFILE_GRAPHIC_NAME;

        String[] completeKey = {s3Bucket.getName(), key};
        return completeKey;
    }

    public String[] profileThumbnailGraphic(Long id) throws NamingException {

        User user = userService.findById(id);
        S3Bucket s3Bucket = s3BucketService.findActiveBucket();

        String key = HISConstants.S3_USER_DIRECTORY
                + "/"
                + HISConstants.S3_USER_PROFILE_DIRECTORY
                + "/"
                + user.getId() + "_" + user.getProfile().getId() + "_"
                + HISConstants.S3_USER_PROFILE_THUMBNAIL_GRAPHIC_NAME;

        String[] completeKey = {s3Bucket.getName(), key};
        return completeKey;
    }


    public String getUserProfileGraphicPublicUserId(Long userId, boolean isDefaultImageRequired) {

        String url = null;

        try {
            User user = userService.findById(userId);
            S3Bucket s3Bucket = s3BucketService.findActiveBucket();

            if (HISCoreUtil.isValidObject(user)) {
                url = s3Bucket.getAccessProtocol()
                        + s3Bucket.getPublicBaseURL()
                        + "/"
                        + s3Bucket
                        + "/"
                        + HISConstants.S3_USER_DIRECTORY
                        + "/"
                        + HISConstants.S3_USER_PROFILE_DIRECTORY
                        + "/"
                        + user.getId() + "_" + user.getProfile().getId() + "_"
                        + HISConstants.S3_USER_PROFILE_GRAPHIC_NAME;
            }

            if (!ResourceCheckUtil.urlExists(url) && isDefaultImageRequired) {
                url = s3Bucket.getAccessProtocol()
                        + s3Bucket.getPublicBaseURL()
                        + "/"
                        + s3Bucket
                        + "/"
                        + HISConstants.S3_CORE_DIRECTORY
                        + "/"
                        + HISConstants.S3_CORE_GRAPHICS_DIRECTORY
                        + "/"
                        + HISConstants.S3_CORE_PROFILE_GRAPHIC_HOLDER_NAME;
            } else if (!ResourceCheckUtil.urlExists(url) && !isDefaultImageRequired) {
                url = null;
            }

        } catch (Exception ex) {
            logger.error("Unknown Error whilst getting object from S3", ex);
        }
        return url;
    }

    public String getUserProfileThumbnailGraphicPublicUserId(Long userId, boolean isDefaultImageRequired) {

        String url = null;

        try {
            User user = userService.findById(userId);
            S3Bucket s3Bucket = s3BucketService.findActiveBucket();

            if (HISCoreUtil.isValidObject(user)) {
                url = s3Bucket.getAccessProtocol()
                        + s3Bucket.getPublicBaseURL()
                        + "/"
                        + s3Bucket
                        + "/"
                        + HISConstants.S3_USER_DIRECTORY
                        + "/"
                        + HISConstants.S3_USER_PROFILE_DIRECTORY
                        + "/"
                        + user.getId() + "_" + user.getProfile().getId() + "_"
                        + HISConstants.S3_USER_PROFILE_THUMBNAIL_GRAPHIC_NAME;
            }

            if (!ResourceCheckUtil.urlExists(url) && isDefaultImageRequired) {
                url = s3Bucket.getAccessProtocol()
                        + s3Bucket.getPublicBaseURL()
                        + "/"
                        + s3Bucket
                        + "/"
                        + HISConstants.S3_CORE_DIRECTORY
                        + "/"
                        + HISConstants.S3_CORE_GRAPHICS_DIRECTORY
                        + "/"
                        + HISConstants.S3_CORE_PROFILE_GRAPHIC_HOLDER_NAME;
            } else if (!ResourceCheckUtil.urlExists(url) && !isDefaultImageRequired) {
                url = null;
            }

        } catch (Exception ex) {
            logger.error("Unknown Error whilst getting object from S3", ex);
        }
        return url;
    }

}
