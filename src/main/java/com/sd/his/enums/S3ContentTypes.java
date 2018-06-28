package com.sd.his.enums;

/*
 * @author    : irfan
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
 * @Package   : com.sd.ap.enums
 * @FileName  : S3ContentTypes
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */

public enum S3ContentTypes {

    PDF("PDF"),
    JPEG("JPEG"),
    JPG("JPG"),
    PNG("PNG"),
    GIF("GIF"),
    BMP("BMP"),
    TIFF("TIFF"),
    PLAIN("PLAIN"),
    RTF("RTF"),
    MSWORD("MSWORD"),
    ZIP("ZIP");

    private String value;

    S3ContentTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
