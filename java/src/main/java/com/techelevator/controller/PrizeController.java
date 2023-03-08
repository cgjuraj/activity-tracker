package com.techelevator.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.FamilyDao;
import com.techelevator.dao.PrizeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Book;
import com.techelevator.model.Family;
import com.techelevator.model.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PrizeController {

    @Autowired
    private PrizeDao prizeDao;

    @Autowired
    private FamilyDao familyDao;

    @Autowired
    private UserDao userDao;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ResponseStatus (HttpStatus.CREATED)
//    @RequestMapping(path = "/addFamilyPrize", method = RequestMethod.POST)
//    public ResponseEntity <PrizeResponse> addPrizeToFamily(@RequestBody Prize newPrize, Principal principal) {
//        System.out.println(principal.getName());
//        int id = userDao.findIdByUsername(principal.getName()); //find family by name/something else
//        newPrize.setFamilyId(id);
//
//        prizeDao.addPrizeToFamily(newPrize, principal);
//        return null;
//    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus (HttpStatus.CREATED)
    @RequestMapping(path = "/addFamilyPrize", method = RequestMethod.POST)
    public void addPrizeToFamily(@RequestBody Prize newPrize) {

        prizeDao.addPrizeToFamily(newPrize);
    }

    /*@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/addUserPrize", method = RequestMethod.POST)
    public void addPrizeToUser(@RequestBody Prize newPrize) {
        prizeDao.addPrizeToUser(newPrize);
    }*/

//    @RequestMapping(path = "/prizes/list/user/{userId}")
//    public List<Prize> getAllPrizesByUserId(@PathVariable int userId) {
//        return prizeDao.getAllPrizesByUserId(userId);
//
//    }

    @RequestMapping(path = "/prizes/prize/{prizeId}")
    public Prize getPrizesById(@PathVariable int prizeId) {
        Prize prize = prizeDao.getPrizesById(prizeId);
        return prize;
    }

    @RequestMapping(path = "/prizes/list/family/{familyId}")
    public List<Prize> getAllPrizesByFamilyId(@PathVariable int familyId) {
        return prizeDao.getAllPrizesByFamilyId(familyId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/deletePrize/{prizeId}", method = RequestMethod.DELETE)
    public void deletePrize(@PathVariable int prizeId) {
        prizeDao.deletePrize(prizeId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/updatePrize/{prizeId}", method = RequestMethod.PUT)
    public void updatePrize(@PathVariable int prizeId, @RequestBody Prize prize) {
        prizeDao.updatePrize(prizeId, prize);
    }

    public static class PrizeResponse {
        private int prizeId;

//        @JsonProperty("prize_id");
        public int getPrizeId() {
            return prizeId;
        }

        public void setPrizeId(int prizeId) {
            this.prizeId = prizeId;
        }

        public PrizeResponse(int prizeId) {
            this.prizeId = prizeId; }

        }
    }