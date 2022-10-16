package com.kasimkartal866.demoapp.orm;


public class UserPass {
    private String userName;
    private String pass;

    public UserPass(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }


    public String getPass() {
        return pass;
    }

}
