/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.fac;


import com.ihmProject.hotelManagement.spring.bean.SignUp;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface SignupService {

    int save(SignUp login);

    SignUp findByUserName(String userName);
    
    List<SignUp> findAll();
    
}
