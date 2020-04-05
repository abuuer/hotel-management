/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.controller;

import net.rgielen.fxweaver.core.FxmlView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.MainPageController;
import com.ihmProject.hotelManagement.spring.service.impl.PaymentImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author anoir
 */
@Component
@FxmlView("/fxml/Payment.fxml")
public class PaymentController implements Initializable {

    @FXML
    private RadioButton bitcoin;

    @FXML
    private ToggleGroup cardType;

    @FXML
    private RadioButton visa;

    @FXML
    private RadioButton other;

    @FXML
    private RadioButton master;

    @FXML
    private JFXButton paySubmit;

    @FXML
    private JFXTextField cardNumberInput;

    @FXML
    private JFXTextField expDateInput;

    @FXML
    private JFXTextField cvcInput;

    @FXML
    private Label errorCard;

    @FXML
    private Label emptyError;

    @FXML
    private JFXTextField cardNameInput;

    private PaymentImpl paymentService;

    @Autowired
    public PaymentController(PaymentImpl paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorCard.setVisible(false);
        emptyError.setVisible(false);
    }

    public void submitPayment() throws IOException {
        if (cardNumberInput.getText().isEmpty() || expDateInput.getText().isEmpty()
                || cvcInput.getText().isEmpty() || cardNameInput.getText().isEmpty()) {
            emptyError.setVisible(true);
        } else {
            JavaFxApplication.payment.setCardNumber(cardNumberInput.getText());
            JavaFxApplication.payment.setExpDate(expDateInput.getText());
            JavaFxApplication.payment.setCardCVC(cvcInput.getText());
            JavaFxApplication.payment.setReference(MainPageController.generateRandomReference());
            JavaFxApplication.payment.setNameOnCard(cardNameInput.getText());
            int res = paymentService.pay(JavaFxApplication.payment);

            if (res == -2) {
                errorCard.setVisible(true);
            } else {
                Pane myPane = FXMLLoader.load(getClass().getResource("/fxml/Success.fxml"));
                Scene myScene = new Scene(myPane);
                JavaFxApplication.newStage.setScene(myScene);
                JavaFxApplication.newStage.show();
            }

        }
    }

}
