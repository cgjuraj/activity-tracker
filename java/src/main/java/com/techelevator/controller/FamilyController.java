package com.techelevator.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.FamilyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Family;
import com.techelevator.model.FamilyUsers;
import com.techelevator.model.FamilyUpdateDto;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class FamilyController {

    @Autowired
    private FamilyDao familyDao;
    @Autowired
    private UserDao userDao;

    @ResponseStatus (HttpStatus.CREATED)
    @RequestMapping(path = "/addFamily", method = RequestMethod.POST)
    public ResponseEntity <FamilyResponse> addFamily(@RequestBody Family newFamily, Principal principal) {
        System.out.println(principal.getName());
        int id = userDao.findIdByUsername(principal.getName());
        newFamily.setUserId(id);
        familyDao.addFamily(newFamily, id);
        int family =  familyDao.addFamily(newFamily, id);

        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(new FamilyResponse(family), httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/displayUsers", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @RequestMapping(path = "/deleteFamily/{familyId}", method = RequestMethod.DELETE)
    public void deleteFamily(@PathVariable int familyId) {
        familyDao.deleteFamily(familyId);
    }

    @RequestMapping(path = "/updateFamily/{familyId}", method = RequestMethod.PUT)
    public void updateFamily(@RequestBody FamilyUpdateDto familyName, @PathVariable int familyId) {
        familyDao.updateFamily(familyName.getFamilyName(), familyId);
    }

    @RequestMapping(path = "/family/list", method = RequestMethod.GET)
    public List<FamilyUsers> getListOfFamilyMembers(Principal principal) {
        return familyDao.getListOfFamilyMembers(familyDao.getFamilyByUserId(userDao.findIdByUsername(principal.getName())));
    }

    @RequestMapping(path = "/family/{familyId}/{userId}", method = RequestMethod.PUT)
    public void addFamilyMember(@PathVariable int userId, @PathVariable int familyId) {
        familyDao.addFamilyMember(userId, familyId);
    }

    @RequestMapping(path = "/family")
    public List<Family> getListOfFamilies() {
        return familyDao.getListOfFamilies();
    }

    @RequestMapping(path = "/family/{userId}")
    public int getFamilyByUserId(@PathVariable int userId) {
        return familyDao.getFamilyByUserId(userId);
    }

    @RequestMapping (path = "/family/{familyId}")
    public Family getFamilyNameByFamilyId (@PathVariable int familyId) {
    return familyDao.getFamilyById(familyId);
    }

    public static class FamilyResponse {
        private int familyId;

        @JsonProperty("family_id")
        public int getFamilyId() {
            return familyId;
        }

        public void setFamilyId(int familyId) {
            this.familyId = familyId;
        }

        public FamilyResponse(int familyId) {
            this.familyId = familyId;
        }
    }
}