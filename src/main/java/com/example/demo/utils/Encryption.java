package com.example.demo.utils;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.algorithms.Type;
import com.amdelamar.jhash.exception.InvalidHashException;

/**
 * @Author WeLong
 * @create 2019/10/24 19:37
 */
public class Encryption {


    /**
     *
     * @param str
     * @return cryptPassword
     */
    public static String crypt(String str) {
        char[] password = str.toCharArray();
        String cryptPassword = Hash.password(password).algorithm(Type.BCRYPT).create();
        return cryptPassword;
    }


    /**
     *
     * @param password
     * @param cryptPassword
     * @return boolean
     * @throws InvalidHashException
     */
    public static boolean encrypt(String password,String cryptPassword) throws InvalidHashException {
        char[] chars = password.toCharArray();
        if(Hash.password(chars).verify(cryptPassword)){
            return true;
        }else{
            return false;
        }
    }
}
