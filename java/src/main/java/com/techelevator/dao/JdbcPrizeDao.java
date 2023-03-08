package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.Prize;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPrizeDao implements PrizeDao {
    JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
/*
    @Override
    public void addPrizeToUser(Prize newPrize) {
        String sql = "INSERT INTO prizes (prize_name, prize_description, milestone, start_date,end_date) VALUES (?,?,?,?,?) RETURNING prize_id";
        jdbcTemplate.update(sql, newPrize.getPrizeName(), newPrize.getPrizeDescription(), newPrize.getMilestone(),newPrize.getStartDate(),newPrize.getEndDate());
    }*/

    @Override
    public void addPrizeToFamily(Prize newPrize) {
        String sql = "INSERT INTO prizes (family_id, prize_name, prize_description, milestone, start_date, end_date) VALUES (?, ?,?,?,?,?)";
        jdbcTemplate.update(sql, newPrize.getFamilyId(), newPrize.getPrizeName(), newPrize.getPrizeDescription(),newPrize.getMilestone(), newPrize.getStartDate(),newPrize.getEndDate());
    }

    @Override
    public void deletePrize(int prizeId) {
        String sql = "DELETE FROM prizes WHERE prize_id = ?;";
        jdbcTemplate.update(sql, prizeId);
    }

    @Override
    public void updatePrize(int prizeId, Prize prize) {
        String sql = "UPDATE prizes SET prize_name = ?, prize_description = ?, milestone = ?, max_prizes = ?, start_date = ?, end_date = ? WHERE prize_id = ?;";
        jdbcTemplate.update(sql, prize.getPrizeName(), prize.getPrizeDescription(), prize.getMilestone(), prize.getMaxPrizes(), prize.getStartDate(), prize.getEndDate(), prizeId);
    }

    @Override
    public List<Prize> getAllPrizesByFamilyId(int familyId) {
        List<Prize> prizeList = new ArrayList<>();
        String sql = "SELECT * FROM prizes WHERE family_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, familyId);
        while (rowSet.next()) {
            prizeList.add(mapRowToPrizes(rowSet));
        }
        return prizeList;
    }

    @Override
    public Prize getPrizesById(int prizesId) {
        Prize prize = new Prize();
        String sql = "SELECT * FROM prizes WHERE prize_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, prizesId);
        if (rowSet.next()) {
            prize = mapRowToPrizes(rowSet);

        }
        return prize;

    }



    @Override
    public List<Prize> getAllPrizesByUserId(int userId) {
        List<Prize> getPrizeListByUserId = new ArrayList<>();
        String sql = "SELECT * FROM prizes WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while (rowSet.next()) {
            getPrizeListByUserId.add(mapRowToPrizes(rowSet));
        }
        return getPrizeListByUserId;
    }


    private Prize mapRowToPrizes (SqlRowSet rowSet) {
        Prize prize = new Prize();
        prize.setPrizeId(rowSet.getInt("prize_id"));
        prize.setFamilyId(rowSet.getInt("family_id"));
        prize.setEndDate(rowSet.getDate("end_date"));
        prize.setStartDate(rowSet.getDate("start_date"));
        prize.setMaxPrizes(rowSet.getInt("max_prizes"));
       // prize.setUserId(rowSet.getInt("user_id"));
        prize.setPrizeDescription(rowSet.getString("prize_description"));
        prize.setPrizeName(rowSet.getString("prize_name"));
        prize.setMilestone(rowSet.getInt("milestone"));
        return prize;
    }
}
