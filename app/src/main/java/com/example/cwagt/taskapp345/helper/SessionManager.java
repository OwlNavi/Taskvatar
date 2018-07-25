package com.example.cwagt.taskapp345.helper;

import com.example.cwagt.taskapp345.object.User;

/**
 * Created by cwagt on 20/07/2018.
 */

public class SessionManager {

    public  static SessionManager sessionManager;

    private static User currentUser;

    private SessionManager(){
        currentUser = null;
    }

    public static SessionManager getSessionManager(){
        if(sessionManager == null){
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }
}
