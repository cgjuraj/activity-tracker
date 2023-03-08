package com.techelevator.controller;

import com.techelevator.dao.BookDao;
import com.techelevator.dao.FamilyDao;
import com.techelevator.dao.ReadingActivityDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Book;
import com.techelevator.model.ReadingActivity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ReadingActivityController {

    @Autowired
    private ReadingActivityDao readingActivityDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FamilyDao familyDao;

    @Autowired
    private BookDao bookDao;

    @RequestMapping(path = "/activities")
    public List<ReadingActivity> getAllReadingActivities() {
        List<ReadingActivity> activityList = readingActivityDao.getAllReadingActivities();
        return activityList;
    }

    @RequestMapping(path = "/activities/user/{userId}")
    public int getReadingTimeByUserId (@PathVariable int userId){
        return readingActivityDao.getReadingTimeByUserId(userId);
    }

    @RequestMapping(path = "/activities/family/{familyId}")
    public int getReadingTimeByFamilyId (@PathVariable int familyId) {
        return readingActivityDao.getReadingTimeByFamilyId(familyId);
    }

    @RequestMapping(path = "/addActivity", method = RequestMethod.POST)
    public void addActivity (@RequestBody ReadingActivity newActivity, Principal principal) {


        // we know that newActivity.getTitle() gives us the title
        // given that title, go to some DAO and retrieve the id that corresponds to that book
        Book foundBook = bookDao.getByTile(newActivity.getTitle());
        newActivity.setBookId(foundBook.getBookId());

        int foundId = userDao.findIdByUsername(principal.getName());
        newActivity.setUserId(foundId);

        int foundFamilyId = familyDao.getFamilyByUserId(foundId);
        newActivity.setFamilyId(foundFamilyId);

        System.out.println("debug");
        System.out.println(newActivity);

        readingActivityDao.addActivity(newActivity);
    }

    @RequestMapping(path = "/deleteActivity/{activityId}", method = RequestMethod.DELETE)
    public void deleteActivity (@PathVariable int activityId) {
        // NEEDS PARENT-ONLY PERMISSIONS
        readingActivityDao.deleteActivity(activityId);
    }

    @RequestMapping(path = "/updateActivity/{activityId}", method = RequestMethod.PUT)
    public void updateActivity (@RequestBody ReadingActivity updateActivity, @PathVariable int activityId) {
        readingActivityDao.updateActivity(updateActivity, activityId);
    }






    @RequestMapping(path = "/addActivity/parent/{userId}", method = RequestMethod.POST)
    public void addParentActivity (@RequestBody ReadingActivity newparentActivity) {
        readingActivityDao.addActivity(newparentActivity);
    }
    @RequestMapping(path = "/addActivity/child/{userId}", method = RequestMethod.POST)
    public void addChildActivity (@RequestBody ReadingActivity newChildActivity) {
        readingActivityDao.addActivity(newChildActivity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/addActivity/parentToChild/{userId}", method = RequestMethod.POST)
    public void addParentToChildActivity (@RequestBody ReadingActivity newParentToChildActivity) {
        readingActivityDao.addActivity(newParentToChildActivity);
    }

}
