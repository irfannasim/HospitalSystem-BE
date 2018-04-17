package com.sd.his.utill;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * @author    : irfan nasim
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
 * @Package   : com.sd.his.utill
 * @FileName  : Test
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
public class Test {
    public static void main(String ...args){
        System.out.println(new BCryptPasswordEncoder().encode("Password*1"));
    }
}
