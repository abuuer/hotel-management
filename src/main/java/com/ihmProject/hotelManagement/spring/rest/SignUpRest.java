package com.ihmProject.hotelManagement.spring.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("hotels/SignUp")
public class SignUpRest {

    @Autowired
    private SignupService signupService;

    @PostMapping("/")
    public int save(@RequestBody SignUp login) {
        return signupService.save(login);
    }

    @GetMapping("/userName/{userName}")
    public SignUp findByUserName(@PathVariable String userName) {
        return signupService.findByUserName(userName);
    }

    @GetMapping("/")
    public List<SignUp> findAll() {
        return signupService.findAll();
    }

   

}
