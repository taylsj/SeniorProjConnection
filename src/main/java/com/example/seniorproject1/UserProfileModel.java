package com.example.seniorproject1;

public class UserProfileModel {

    private static UserProfileModel instance = null;

    private String firstName = "";
    private String lastName = "";
    private String userName = "";
    private String email = "";
    private String aboutMe = "";

    public static UserProfileModel getInstance() {
        if (instance == null) {
            instance = new UserProfileModel();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public  String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String x) {
        aboutMe =  x;
    }

    public void setUserName(String x) {
        userName = x;
    }


}
