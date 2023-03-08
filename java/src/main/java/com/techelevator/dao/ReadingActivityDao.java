package com.techelevator.dao;

import com.techelevator.model.ReadingActivity;

import java.util.List;

public interface ReadingActivityDao {

    List <ReadingActivity> getAllReadingActivities ();
//  get activity by user id
    int getReadingTimeByUserId (int userId);
    int getReadingTimeByFamilyId (int familyId);
//  get activity by family id
    void addActivity (ReadingActivity newActivity);
    void deleteActivity (int activityId);
    void updateActivity (ReadingActivity updateActivity, int activityId);

    void parentActivity (ReadingActivity parentActivity, int userId);
    void ChildActivity (ReadingActivity childActivity, int userId);
    void ParentToChildActivity (ReadingActivity parentToChildActivity, int userId);





}
