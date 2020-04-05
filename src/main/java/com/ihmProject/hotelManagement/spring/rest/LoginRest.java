package com.ihmProject.hotelManagement.spring.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.fac.LoginService;

@RestController
@RequestMapping("hotels/Login")
public class LoginRest {

    @Autowired
    private LoginService loginService;

 

    @GetMapping("/login")
    public int confirmPassword(@RequestBody Login login) {
        return loginService.confirmLogin(login);
    }

}
