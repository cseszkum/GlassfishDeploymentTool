/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seacon.gdt.utility;

/**
 *
 * @author varsanyi.peter
 */
public class Utility {
    
    public static Boolean convertStrToBoolean(String str) {
        if (str == null || "".equals(str.trim())) {
            str = "false";
        }
        return ("true".equals(str.toLowerCase())) ? true : false;
    }
    
}
