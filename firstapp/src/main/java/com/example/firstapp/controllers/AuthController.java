package com.example.firstapp.controllers;

import com.example.firstapp.dao.UserDao;
import com.example.firstapp.models.User;
import com.example.firstapp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//TODO: SPRING SECURITY Y CONTROL DE ERRORES
@RestController
public class AuthController{
    @Autowired //permite inyectar dependencias
    private UserDao userDao;

    @Autowired
    private JwtUtils jwtUtils;
    // login endpoint
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String logUser(@RequestBody User user) {
        User userCredentials = userDao.getUserWithCredentials(user);
       if (  userCredentials != null ){
           String userToken = jwtUtils.create(String.valueOf(userCredentials.getId()), userCredentials.getEmail());
           return userToken;
       }
       return "FAIL";
    }

}
