package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.ReadingActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReadingActivityDao implements ReadingActivityDao{

    JdbcTemplate jdbcTemplate;

    @Autowired
   private BookDao bookDao;

    public JdbcReadingActivityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ReadingActivity> getAllReadingActivities() {
        List<ReadingActivity> activityList = new ArrayList<>();
        String sql = "SELECT * FROM reading_activity;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            activityList.add(mapRowToReadingActivities(rowSet));
        }
        return activityList;
    }

    @Override
    public int getReadingTimeByUserId(int userId) {
        String sql = "SELECT sum(time_read) FROM reading_activity WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        if (rowSet.next()) {
            return rowSet.getInt("sum"); //total time read added together
        }
        return 0; // doesn't exist
    }

    @Override
    public int getReadingTimeByFamilyId(int familyId) {
        String sql = "SELECT sum(time_read) FROM reading_activity WHERE family_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, familyId);
        if (rowSet.next()) {
            return rowSet.getInt("sum");
        }
        return 0;
    }

    @Override
    public void addActivity(ReadingActivity newActivity) {
//        Book book = bookDao.getByTile(newActivity.getBookTitle());
        int bookId = 0;

        String sqlTitle = "SELECT book_id FROM book WHERE title = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlTitle, newActivity.getTitle());

        if (rowSet.next()) {
            bookId = rowSet.getInt("book_id");
        }

        String sqlActivity = "INSERT INTO reading_activity (user_id, family_id, book_id, time_read, book_format, notes) VALUES (?,?,?,?,?,?);";
        jdbcTemplate.update(sqlActivity, newActivity.getUserId(), newActivity.getFamilyId(), bookId, newActivity.getTimeRead(), newActivity.getBookFormat(), newActivity.getNotes());

    }

    @Override
    public void deleteActivity(int activityId) {
        String sql = "DELETE FROM reading_activity WHERE activity_id = ?;";
        jdbcTemplate.update(sql, activityId);


    }

    @Override
    public void updateActivity(ReadingActivity updateActivity, int activityId) {
        String sql = "UPDATE reading_activity SET book_id = ?, time_read = ?, book_format = ?, notes = ?;";
        jdbcTemplate.update(sql, updateActivity.getBookId(), updateActivity.getTimeRead(), updateActivity.getBookFormat(), updateActivity.getNotes());

    }


    @Override
    public void parentActivity(ReadingActivity parentActivity, int userId) {
        String sql = "INSERT INTO reading_activity (user_id, family_id, book_id, time_read, book_format, notes) VALUES (?,?,?,?,?,?);";
        jdbcTemplate.update(sql,parentActivity.getUserId(), parentActivity.getFamilyId(), parentActivity.getBookId(),parentActivity.getTimeRead(), parentActivity.getBookFormat(), parentActivity.getNotes(), userId);
    }

    @Override
    public void ChildActivity(ReadingActivity childActivity, int userId) {
        String sql = "INSERT INTO reading_activity (user_id, family_id, book_id, time_read, book_format, notes) VALUES (?,?,?,?,?,?);";
        jdbcTemplate.update(sql,childActivity.getUserId(), childActivity.getFamilyId(), childActivity.getBookId(),childActivity.getTimeRead(), childActivity.getBookFormat(), childActivity.getNotes(), userId);
    }

    @Override
    public void ParentToChildActivity(ReadingActivity parentToChildActivity, int userId) {
        String sql = "INSERT INTO reading_activity (user_id, family_id, book_id, time_read, book_format, notes) VALUES (?,?,?,?,?,?);";
        jdbcTemplate.update(sql,parentToChildActivity.getUserId(), parentToChildActivity.getFamilyId(), parentToChildActivity.getBookId(),parentToChildActivity.getTimeRead(), parentToChildActivity.getBookFormat(), parentToChildActivity.getNotes(), userId);
    }




    private ReadingActivity mapRowToReadingActivities (SqlRowSet rowSet) {
        ReadingActivity readingActivity = new ReadingActivity();
        readingActivity.setActivityId(rowSet.getInt("activity_id"));
        readingActivity.setUserId(rowSet.getInt("user_id"));
        readingActivity.setFamilyId(rowSet.getInt("family_id"));
        readingActivity.setBookId(rowSet.getInt("book_id"));
        readingActivity.setBookFormat(rowSet.getString("book_format"));
        readingActivity.setNotes(rowSet.getString("notes"));
        return readingActivity;
    }
}
