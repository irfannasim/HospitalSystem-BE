package com.sd.his.utill;

import java.net.HttpURLConnection;
import java.net.URL;

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
 * @Package   : com.sd.his.util
 * @FileName  : ResourceCheckUtil
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class ResourceCheckUtil {

    public static boolean urlExists(String Url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(Url).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}
