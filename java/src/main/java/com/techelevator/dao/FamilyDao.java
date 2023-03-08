package com.techelevator.dao;

import com.techelevator.model.Family;
import com.techelevator.model.User;
import com.techelevator.model.FamilyUsers;
import com.techelevator.model.FamilyUpdateDto;


import java.util.List;

public interface FamilyDao {
    List<FamilyUsers> getListOfFamilyMembers(int familyId);
    List<Family> getListOfFamilies();
    Family getFamilyById(int familyId);
    int getFamilyByUserId(int userId);
  //  int getUsernameByFamily ()
    int addFamily (Family newFamily,int id);
    void deleteFamily (int familyId);
    void updateFamily (String familyName, int familyId);
    void addFamilyMember (int userId, int familyId);




}
