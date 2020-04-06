/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Client;
import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.repository.SignUpRepository;
import com.ihmProject.hotelManagement.spring.service.fac.ClientService;
import com.ihmProject.hotelManagement.spring.service.fac.LoginService;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class SignUpImpl implements SignupService {

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private ClientService clientService;

    @Override
    public int save(SignUp signUp) {
        try {
            SignUp foundedUser = signUpRepository.findByUserName(signUp.getUserName());
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(signUp.getPassword().toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            signUp.setPassword(enc.encodeToString(hash));
            signUp.setPasswordSlt(salt);
            if (foundedUser != null) {
                return -1;
            } else {
                signUpRepository.save(signUp);
                return 1;
            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.getStackTrace();
        }
        return 2;
    }

    @Override
    public SignUp findByUserName(String userName) {
        return signUpRepository.findByUserName(userName);
    }

    @Override
    public List<SignUp> findAll() {
        return signUpRepository.findAll();
    }
}


