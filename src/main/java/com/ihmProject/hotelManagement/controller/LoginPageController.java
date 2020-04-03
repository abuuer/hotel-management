package com.ihmProject.hotelManagement.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.MainPageController;
import com.ihmProject.hotelManagement.spring.bean.Login;
import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.impl.LoginImpl;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import javafx.scene.Parent;

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
    private JFXProgressBar loginProgress;

    @FXML
    private Label loginIncorrectPswrd;

    private LoginImpl login;
    private Login userLogin;
    private SignUp foundedUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginProgress.setVisible(false);
        loginIncorrectPswrd.setVisible(false);
    }

    public LoginPageController(LoginImpl login) {
        this.login = login;
    }

    public void connect(ActionEvent event) throws IOException {
        System.out.println("Connect clicked");
        loginProgress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        String d = loginUsernameLabel.getText();
        foundedUser = login.findByUserName(d);
        pt.setOnFinished(e -> {

        });
        int res = login.confirmLogin(userLogin);
        try {
            if (res == 1) {
                loginIncorrectPswrd.setVisible(false);
            } else {
                loginIncorrectPswrd.setVisible(true);
            }
        } catch (Exception ev) {
            ev.printStackTrace();
        }

    }

//	public void loginPageController(LoginImpl login) {
//		this.login = login ;
//	}
    public void goBackToLogin(ActionEvent event) throws IOException {
        loginButton.getScene().getWindow().hide();
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class
        );
        Parent root = fxWeaver.loadView(MainPageController.class
        );
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
