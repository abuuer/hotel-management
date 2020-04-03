/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.fac;

import com.ihmProject.hotelManagement.spring.bean.Payment;

/**
 *
 * @author anoir
 */
public interface PaymentService  {
    
    int pay(Payment payment);
    
    //useing Luhn algorithm
    int confirmBankingDetails(String cardNumber);
    
    Payment findByReference(String refernce);
    
}
