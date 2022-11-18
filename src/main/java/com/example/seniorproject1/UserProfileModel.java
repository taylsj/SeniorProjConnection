package com.example.seniorproject1;

public class UserProfileModel {

    private static UserProfileModel instance = null;

    private String firstName = "";
    private String lastName = "";
    private String userName = "";
    private String email = "";
    private String aboutMe = "";
    private int ProfileID = 0;
    private int Leadership = 0;
    private int Communication = 0;
    private int Strategizing = 0;
    private int Concentration = 0;
    private int MultiTasking = 0;

    public static UserProfileModel getInstance() {
        if (instance == null) {
            instance = new UserProfileModel();
        }
        return instance;
    }

    public int getProfID() {
        return ProfileID;
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

    public int getLeadership() {
        return Leadership;
    }

    public int getCommunication() {
        return Communication;
    }
    public int getStrat() {
        return Strategizing;
    }
    public int getConcentration() {
        return Concentration;
    }
    public int getMultiTasking() {
        return MultiTasking;
    }

    public void setAboutMe(String x) {
        aboutMe =  x;
    }

    public void setUserName(String x) {
        userName = x;
    }
    public void setLeaderShip(int x) {
        Leadership = x;
    }
    public void setCommunication(int x) {
        Communication = x;
    }
    public void setStrat(int x) {
        Strategizing = x;
    }
    public void setConcentration(int x) {
        Concentration = x;
    }
    public void setMultiTasking(int x) {
        MultiTasking = x;
    }

    public  void setProfID(int x) {
        ProfileID = x;
    }

}
