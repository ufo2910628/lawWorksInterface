/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dtxx.util;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class RandomUtil {


    private static final Random s_random = new SecureRandom();
    private static final String s_valid_chars = "0123456789";    // exclude confusing chars 0,1,O,I...

    public static String generateTempPassword() {

        final int PASSWORD_LENGTH = 4;

        byte[] rand = new byte[PASSWORD_LENGTH];
        char[] temp = new char[PASSWORD_LENGTH];

        synchronized (s_random) {
            s_random.nextBytes(rand);
        }

        for (int idx = 0; idx != PASSWORD_LENGTH; ++idx) {
            int offset = rand[idx] % s_valid_chars.length();
            char rchar = s_valid_chars.charAt(offset >= 0 ? offset : offset + s_valid_chars.length());

            temp[idx] = rchar;
        }
        return new String(temp);
    }

    public static void main(String[] args)
    {

        System.out.println(RandomUtil.generateTempPassword());
    }
}
