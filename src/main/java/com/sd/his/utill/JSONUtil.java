package com.sd.his.utill;

import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @author    : irfan
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
 * @Package   : com.sd.ap.util
 * @FileName  : JSONUtil
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */

public class JSONUtil {

    public static String listToJSON(List<?> objects) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jSON = objectMapper.writeValueAsString(objects);
            return jSON;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String objectToJSON(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jSON = objectMapper.writeValueAsString(object);
            return jSON;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String gsonListToJSON(List<?> objects){
        return new Gson().toJson(objects);
    }
}