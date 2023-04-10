package com.scopic.javachallenge.validation;

import static com.scopic.javachallenge.constants.CommonConstant.AUTH_TOKEN;

public class AuthTokenValidation {
    public static boolean validateAuthToken(String authHeader) {

        try{

        if (authHeader == null || !authHeader.contains("Bearer ")) {
            return false;
        }

        if(AUTH_TOKEN.equals(authHeader)){
            return true;
        }
        }catch (Exception e){

           return false;
        }

        return false;

    }
}
