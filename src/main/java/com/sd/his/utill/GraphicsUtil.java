package com.sd.his.utill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * @author    : irfan nasim
 * @Date      : 18-Jul-17
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
 * @Project   : adminportal
 * @Package   : com.sd.ap.util
 * @FileName  : GraphicsUtil
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
public class GraphicsUtil {

    public static final Logger logger = LoggerFactory.getLogger(GraphicsUtil.class);

    public GraphicsUtil() {
    }

    /**
     * Resizes the supplied BufferedImage to the specified dimensions. The
     * original image type is maintained.
     *
     * @param originalBufferedImage
     * @param width                 value to be resized to in pixels
     * @param height                value to be resized to in pixels
     * @return a BufferedImage with the new dimensions
     */
    public BufferedImage resize(
            BufferedImage originalBufferedImage,
            int width,
            int height) {
        /*
         * Obtain the image type
         */
        int type = originalBufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalBufferedImage.getType();

        /*
         * Create a new buffered image the new size and draw in the old image
         */
        BufferedImage resizedImage = new BufferedImage(height, width, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalBufferedImage, 0, 0, height, width, null);
        g.dispose();

        return resizedImage;
    }

}
