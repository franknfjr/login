package com.example.frank.login.domain.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 * Created by Frank on 21/07/2017.
 */

public class CryptWithMD5 {
    private static MessageDigest md;

    public static String cryptWithMD5(String pass){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Log.e("LOG", Level.SEVERE+" - "+ex);
        }
        return null;


    }
}