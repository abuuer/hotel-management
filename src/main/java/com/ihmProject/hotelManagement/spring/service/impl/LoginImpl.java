/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.repository.LoginRepository;
import com.ihmProject.hotelManagement.spring.service.fac.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class LoginImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Login findByUserName(String userName) {
        return loginRepository.findByUserName(userName);
    }

    @Override
    public int save(Login login) {
        Login foundedUser = loginRepository.findByUserName(login.getUserName());
        if (foundedUser != null) {
            return -1;
        } else {
            loginRepository.save(login);
            return 1 ;
        }
    }

}
