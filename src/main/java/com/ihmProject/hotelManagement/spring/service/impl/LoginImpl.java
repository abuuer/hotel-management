/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.fac.LoginService;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class LoginImpl implements LoginService {

    @Autowired
    private SignupService signupService;
    @Autowired
    private LoginService loginService;

    @Override
    public int confirmLogin(Login login) {
        SignUp foundedUser = loginService.findByUserName(login.getUserName());
        System.out.println(foundedUser);
        String rawPwdHashed = null;
        if (foundedUser == null) {
            return -1;
        } else if (login.getRawPassword() == null) {
            return -2;
        } else {
            try {
                //hash rawPassword
                byte[] salt = foundedUser.getPasswordSlt();
                Base64.Encoder enc = Base64.getEncoder();
                KeySpec spec = new PBEKeySpec(login.getRawPassword().toCharArray(), salt, 65536, 128);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] hash = factory.generateSecret(spec).getEncoded();
                rawPwdHashed = enc.encodeToString(hash);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                ex.getStackTrace();
            }
            System.out.println(rawPwdHashed);
            System.out.println(foundedUser.getPassword());
            if (!rawPwdHashed.equals(foundedUser.getPassword())) {
                return -3;
            } else {
                return 1;
            }
        }

    }

    @Override
    public SignUp findByUserName(String userName) {
        return signupService.findByUserName(userName);
    }
}
