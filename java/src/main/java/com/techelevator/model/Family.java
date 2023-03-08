package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class Family {
    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("family_id")
    private int familyId;

    @JsonProperty("user_id")
    private int userId;

    public Family(String familyName, int familyId) {
        this.familyName = familyName;
        this.familyId = familyId;
    }


    public Family() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

