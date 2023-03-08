package com.techelevator.model;

public class FamilyUsers {
    private String familyName;
    private int familyId;
    private String username;
    private int user_id;

    public FamilyUsers(String familyName, int familyId, String username, int user_id) {
        this.familyName = familyName;
        this.familyId = familyId;
        this.username = username;
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FamilyUsers() {
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }



    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }
}

