/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.fac;

import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.bean.SignUp;

/**
 *
 * @author anoir
 */
public interface LoginService {

    Login findByUserName(String userName);

    int confirmLogin(Login login);
    
    int save(Login login);
}
