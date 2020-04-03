/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Payment;
import com.ihmProject.hotelManagement.spring.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import com.ihmProject.hotelManagement.spring.service.fac.PaymentService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anoir
 */
@Service
public class PaymentImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public int pay(Payment payment) {
        Payment foundedPayment = paymentRepository.findByReference(payment.getReference());
        int x = confirmBankingDetails(payment.getCardNumber());
        String cardNumberCrypted = encryptCardNumber(payment.getCardNumber());
        if(foundedPayment != null){
            return -1 ;
        } else if( x == -1){
            return -2 ;
        } else {
            payment.setCardNumber(cardNumberCrypted); 
            paymentRepository.save(payment);
            return 1 ;
        }
    }

    @Override
    public int confirmBankingDetails(String cardNumber) {
        int[] ints = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            ints[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            return 1;
        } else {
           return -1;
        }
    }
    
    private String encryptCardNumber(String CardNumber){
        String criptedNumber = null ;
        try {
            //encrypt cardNumber
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(CardNumber.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            criptedNumber = enc.encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.getStackTrace();
        }
        return criptedNumber ; 
    }

    @Override
    public Payment findByReference(String refernce) {
        return paymentRepository.findByReference(refernce);
    }
}
