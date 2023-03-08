package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {
    @Autowired
    private UserDao userDao;

   // @RequestMapping (path = "/login/accounts",method = RequestMethod.POST)
    /*public User createAccount (Principal principal) {
        User user = userDao.create(principal.getName()) ;
        return user;
    }*/

}
