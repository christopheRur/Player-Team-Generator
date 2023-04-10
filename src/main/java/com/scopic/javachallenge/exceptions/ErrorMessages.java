package com.scopic.javachallenge.exceptions;


import com.scopic.javachallenge.models.Player;
import org.json.JSONObject;
import static com.scopic.javachallenge.constants.CommonConstant.MESSAGE;

public class ErrorMessages {


    /**
     * Will return the error message in json format
     * @param message
     * @return JSONObject
     */
    public static JSONObject sendError(String message){

        JSONObject resp= new JSONObject().put(MESSAGE,message);

        return resp;

    }
    public static JSONObject sendSuccess(String message){

        JSONObject resp= new JSONObject().put(MESSAGE,message);

        return resp;

    }

}
