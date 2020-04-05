package com.ihmProject.hotelManagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ihmProject.hotelManagement.controller.SearchController;
import com.ihmProject.hotelManagement.controller.SignupController;
import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.impl.LoginImpl;
import com.ihmProject.hotelManagement.spring.service.impl.SignUpImpl;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author anoir
 */
@Component
@FxmlView("/fxml/LoginPage.fxml")
public class LoginPageController implements Initializable {

    @FXML
    private JFXTextField loginUsernameLabel;

    @FXML
    private JFXPasswordField loginPasswordLabel;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label loginForgotPasxrdLabel;

    @FXML
    private JFXButton loginRegisterButton;

    @FXML
    private Label loginIncorrectPswrd;

    private LoginImpl login;
    private SignUpImpl signUp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginIncorrectPswrd.setVisible(false);
    }

    @Autowired
    public LoginPageController(LoginImpl login, SignUpImpl signUp) {
        this.login = login;
        this.signUp = signUp;
    }

    public void connect(ActionEvent event) throws IOException {
        if (loginUsernameLabel.getText().isEmpty() || loginPasswordLabel.getText().isEmpty()) {
            loginIncorrectPswrd.setVisible(true);
        } else {
            JavaFxApplication.login.setUserName(loginUsernameLabel.getText());
            JavaFxApplication.login.setRawPassword(loginPasswordLabel.getText());
            int res = login.confirmLogin(JavaFxApplication.login);
            try {
                if (res == 1) {
                    JavaFxApplication.login.setSignUp(signUp
                            .findByUserName(JavaFxApplication.login.getUserName()));
                    login.save(JavaFxApplication.login);
                    System.out.println(JavaFxApplication.login);
                    FxWeaver fxWeaver = JavaFxApplication.applicationContext
                            .getBean(FxWeaver.class);
                    Parent root = fxWeaver.loadView(SearchController.class);
                    StageManager.changeScene(loginIncorrectPswrd, root);
                } else {
                    loginIncorrectPswrd.setVisible(true);
                }
            } catch (Exception ev) {
                ev.printStackTrace();
            }
        }

    }

    public void goToRegister(ActionEvent event) throws IOException {
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(SignupController.class);
        StageManager.changeScene(loginIncorrectPswrd, root);
    }

}
