/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.repository;

import com.ihmProject.hotelManagement.spring.bean.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anoir
 */
@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {

    SignUp findByUserName(String userName);
    
    
}
