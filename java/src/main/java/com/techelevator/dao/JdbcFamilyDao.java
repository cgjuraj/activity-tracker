package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.Family;
import com.techelevator.model.FamilyUsers;
import com.techelevator.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcFamilyDao implements FamilyDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcFamilyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Family> getListOfFamilies() {
        List<Family> families = new ArrayList<>();
        String sql = "SELECT * FROM family;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            families.add(mapRowToFamily(rowSet));
        }
        return families;
    }

    @Override
    public List<FamilyUsers> getListOfFamilyMembers(int familyId) {
        List<FamilyUsers> familyMembers = new ArrayList<>();
        String sql = "SELECT * FROM users JOIN family ON users.family_id = family.family_id WHERE users.family_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, familyId);
        String sql2 = "SELECT * FROM family WHERE family_id = ?";
        SqlRowSet rowSet2 = jdbcTemplate.queryForRowSet(sql2, familyId);
        String familyName = "";
        if (rowSet2.next()) {
            familyName = rowSet2.getString("family_name");
        }
        while (rowSet.next()) {
            User user = mapRowToUser(rowSet);
            FamilyUsers familyMemberObject = new FamilyUsers(familyName,familyId, user.getUsername(),user.getId());
            familyMembers.add(familyMemberObject);
        }
        return familyMembers;
    }

    @Override
    public Family getFamilyById(int familyId) {
        Family family;
        String sql = "SELECT * FROM family WHERE family_id = ?; ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, familyId);
        if (rowSet.next()) {
            family = mapRowToFamily(rowSet);
            return family;
        } else return null;
    }

    @Override
    public int getFamilyByUserId(int userId) {
       int id = 0; // can this = 0?
        String sql = "SELECT * FROM users WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,userId);
        if (rowSet.next()) {
            id = rowSet.getInt("family_id");
        }
        return id;
    }

    @Override
    public int addFamily(Family newFamily,int id) {
        int newFamilyId;
        String sql = "INSERT INTO family (family_name) VALUES (?) RETURNING family_id;";
        newFamilyId= jdbcTemplate.queryForObject(sql, Integer.class, newFamily.getFamilyName());
        String sql2 = "UPDATE users SET family_id = ? WHERE user_id = ?";
         jdbcTemplate.update(sql2, newFamilyId, id);
        return newFamilyId;
//        String sql = "INSERT INTO family (family_name, user_id) VALUES (?, ?);";
//        jdbcTemplate.update(sql, newFamily.getFamilyName(), newFamily.getUserId());
        // try {


        //  } catch (DataAccessException e) {
        //   return false;
        //  }
        //   return true;
        // bool or void
        // add try catch if false
    }

    @Override
    public void deleteFamily(int familyId) {
        String sql = "DELETE FROM family WHERE family_id = ?;";
        jdbcTemplate.update(sql, familyId);
    }

    @Override
    public void updateFamily(String familyName, int familyId) {
        String sql = "UPDATE family SET family_name = ? WHERE family_id = ?;";
        jdbcTemplate.update(sql, familyName, familyId);
    }

    @Override
    public void addFamilyMember(int userId, int familyId) {
        String sql = "UPDATE users SET family_id = ? WHERE user_id = ?";
       // try {
        jdbcTemplate.update(sql, familyId, userId);
       // } catch (DataAccessException e) {
         //   return false;
        //}
        //return true;
    }




    private Family mapRowToFamily (SqlRowSet rs) {
        Family family = new Family();
        family.setFamilyId(rs.getInt("family_id"));
        family.setFamilyName(rs.getString("family_name"));
        return family;
    }

    private FamilyUsers mapRowToFamilyUsers (SqlRowSet rs) {
        FamilyUsers familyUsers = new FamilyUsers();
        familyUsers.setFamilyId(rs.getInt("family_id"));
        familyUsers.setFamilyName(rs.getString("family_name"));
        familyUsers.setUsername(rs.getString("username"));
        return familyUsers;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }


}

