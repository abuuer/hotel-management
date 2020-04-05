/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.controller;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 *
 * @author anoir
 */
@Component
@FxmlView("/fxml/Success.fxml")
public class SuccessController implements Initializable {

    @FXML
    private ImageView userProfil;

    @FXML
    private Label userNameLabel;

    @FXML
    private JFXButton closeBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameLabel.setText(JavaFxApplication.login.getUserName());
    }

    public void closeWidow() {
        Platform.exit();
    }
}
