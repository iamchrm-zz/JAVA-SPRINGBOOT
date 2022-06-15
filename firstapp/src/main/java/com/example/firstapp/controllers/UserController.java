package com.example.firstapp.controllers;

import com.example.firstapp.dao.UserDao;
import com.example.firstapp.models.User;
import com.example.firstapp.utils.JwtUtils;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    @Qualifier("userDaoImp")
    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtils jwtUtils;
    //default method is GET
    //delete
    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delUser(@RequestHeader(value = "Authorization") String token,
                        @PathVariable Long id){
        if (!tokenValidator(token)){return;}
        userDao.delUser(id);
    }

    //list
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        if (!tokenValidator(token)){return null;}
        return userDao.getUsers();
    }
    //Token Validator
    private boolean tokenValidator(String token){
        String userId = jwtUtils.getKey(token);
        return userId != null;
    }
    //register and hash password security
    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void regUser(@RequestBody User user) {


        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.regUser(user);
    }

}
