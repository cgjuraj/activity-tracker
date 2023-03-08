package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Prize {

    private int prizeId;
    @JsonProperty ("prize_name")
    private String prizeName;
    @JsonProperty ("max_prizes")
    private int maxPrizes;

    @JsonProperty ("prize_description")
    private String prizeDescription;
    @JsonProperty ("milestone")
    private int milestone;

    @JsonProperty ("family_id")
    private int familyId;

    private int userId;
    @JsonProperty ("start_date")
    private Date startDate;

    @JsonProperty ("end_date")
    private Date endDate;


    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }


    public Prize(int prizeId, String prizeName, int maxPrizes, String prizeDescription, int milestone, int familyId,  Date startDate, Date endDate) {
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.maxPrizes = maxPrizes;
        this.prizeDescription = prizeDescription;
        this.milestone = milestone;
        this.familyId = familyId;
       // this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Prize() {
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getMaxPrizes() {
        return maxPrizes;
    }

    public void setMaxPrizes(int maxPrizes) {
        this.maxPrizes = maxPrizes;
    }

    public String getPrizeDescription() {
        return prizeDescription;
    }

    public void setPrizeDescription(String prizeDescription) {
        this.prizeDescription = prizeDescription;
    }

    public int getMilestone() {
        return milestone;
    }

    public void setMilestone(int milestone) {
        this.milestone = milestone;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

  /*  public int getUserId() {
        return userId;
    }
*/
  /*  public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
