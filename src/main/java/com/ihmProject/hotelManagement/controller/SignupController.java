package com.ihmProject.hotelManagement.controller;

import com.ihmProject.hotelManagement.LoginPageController;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.StageManager;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/fxml/SignupPage.fxml")

public class SignupController implements Initializable {

    @FXML
    private JFXPasswordField signupPswd;

    @FXML
    private JFXButton registerLoginButton;

    @FXML
    private JFXButton signupBtn;

    @FXML
    private JFXTextField signupuser;

    @FXML
    private JFXPasswordField confirmPswd;

    @FXML
    private Label error;

    private SignupService signupService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setVisible(false);
    }

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @FXML
    void registerAction(ActionEvent event) {
        if (confirmPswd.getText().isEmpty() || signupPswd.getText().isEmpty()
                || signupuser.getText().isEmpty() || !signupPswd.getText().equals(confirmPswd.getText())) {
            error.setVisible(true);
        } else {
            JavaFxApplication.signUp.setUserName(signupuser.getText());
            JavaFxApplication.signUp.setPassword(signupPswd.getText());
            signupService.save(JavaFxApplication.signUp);
            FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class
            );
            Parent root = fxWeaver.loadView(LoginPageController.class
            );
            StageManager.changeScene(error, root);
        }
    }

    @FXML
    void backToLoginAction(ActionEvent event) {
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class
        );
        Parent root = fxWeaver.loadView(LoginPageController.class
        );
        StageManager.changeScene(error, root);
    }
}
