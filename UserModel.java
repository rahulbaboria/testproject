package com.loyality.testproject;

/**
 * Created by Rohit Gupta on 22-04-2017.
 */

public class UserModel {
    private int userId;
    private String fName;
    private String lName;
    private String emailId;
    private String phoneNo;
    private String password;

    public UserModel(int userId,String fName, String lName, String emailId, String phoneNo, String password) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public int getUserId(){
        return userId;
    }
    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getPassword() {
        return password;
    }
}
