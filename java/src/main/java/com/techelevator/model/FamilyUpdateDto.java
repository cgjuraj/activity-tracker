package com.techelevator.model;

public class FamilyUpdateDto {

    private String familyName;

    public FamilyUpdateDto(String familyName) {
        this.familyName = familyName;
    }

    public FamilyUpdateDto() {
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }
}