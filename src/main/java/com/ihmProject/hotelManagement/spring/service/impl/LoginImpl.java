/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.spring.service.impl;

import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.repository.LoginRepository;
import com.ihmProject.hotelManagement.spring.service.fac.LoginService;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
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
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class LoginImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private SignupService signupService;

    @Override
    public int confirmLogin(Login login) {
        SignUp foundedUser = signupService.findByUserName(login.getUserName());
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
            if (!rawPwdHashed.equals(foundedUser.getPassword())) {
                return -3;
            } else {
                return 1;
            }
        }

    }

    @Override
    public Login findByUserName(String userName) {
        return loginRepository.findByUserName(userName);
    }

    @Override
    public int save(Login login) {
        Login fLogin = loginRepository.findByUserName(login.getUserName());
        SignUp fSignUp = signupService.findByUserName(login.getSignUp().getUserName());
        if (fLogin != null) {
            return -1;
        } else {
            try {
                SecureRandom random = new SecureRandom();
                byte[] salt = new byte[16];
                random.nextBytes(salt);
                KeySpec spec = new PBEKeySpec(login.getRawPassword().toCharArray(), salt, 65536, 128);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] hash = factory.generateSecret(spec).getEncoded();
                Base64.Encoder enc = Base64.getEncoder();
                login.setRawPassword(enc.encodeToString(hash));
                loginRepository.save(login);
                return 1;
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(LoginImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;
        }
    }
}
