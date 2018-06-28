package com.sd.his.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.sd.his.configuration.AWSS3;
import com.sd.his.configuration.S3KeyGen;
import com.sd.his.enums.S3ContentTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/*
 * @author    : irfan Nasim
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
 * @Package   : com.sd.his.service
 * @FileName  : AWSService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service("awsService")
public class AWSService {
    private static int IMG_WIDTH = 100;
    private static int IMG_HEIGHT = 100;

    @Autowired
    S3KeyGen s3KeyGen;
    @Autowired
    AWSS3 awss3;

    public boolean saveProfileImage(InputStream inputStream, Long id) {

        boolean returnValue = false;

        BufferedImage bufferedImage;

        try {
            // Convert the event input stream into a buffered image
            bufferedImage = ImageIO.read(inputStream);

            // Generate an s3Key
            String[] s3Key = s3KeyGen.profileGraphic(id);

            // Save the image to S3
            awss3.imageToS3(
                    bufferedImage,
                    s3Key[1],
                    S3ContentTypes.PNG,
                    CannedAccessControlList.PublicRead);
            returnValue = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public boolean uploadImage(InputStream inputStream, Long id) {
        boolean returnValue = false;
        BufferedImage bufferedImage;

        try {
            // Convert the event input stream into a buffered image
            bufferedImage = ImageIO.read(inputStream);
            int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
            IMG_WIDTH = bufferedImage.getWidth() / 5;
            IMG_HEIGHT = bufferedImage.getHeight() / 5;

            BufferedImage resizeImageJpg = resizeImage(bufferedImage, type);
            String[] s3ThumbnailKey = s3KeyGen.profileThumbnailGraphic(id);
            String[] s3Key = s3KeyGen.profileGraphic(id);
            // Save the full image to S3
            awss3.imageToS3(
                    bufferedImage,
                    s3Key[1],
                    S3ContentTypes.PNG,
                    CannedAccessControlList.PublicRead);

            // Save the thumbnails image to S3
            awss3.imageToS3(
                    resizeImageJpg,
                    s3ThumbnailKey[1],
                    S3ContentTypes.PNG,
                    CannedAccessControlList.PublicRead);
            returnValue = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public String getProfileImageUrl(Long id) {
        return s3KeyGen.getUserProfileGraphicPublicUserId(id, false);
    }

    public String getProfileThumbnailImageUrl(Long id) {
        return s3KeyGen.getUserProfileThumbnailGraphicPublicUserId(id, false);
    }
}
