package com.ihmProject.hotelManagement.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ihmProject.hotelManagement.JavaFxApplication;
import static com.ihmProject.hotelManagement.MainPageController.generateRandomReference;
import com.ihmProject.hotelManagement.spring.bean.Reservation;
import com.ihmProject.hotelManagement.spring.service.impl.ReservationImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author anoir
 */
@Component
@FxmlView("/fxml/Reservation.fxml")
public class ReservationController implements Initializable {

    @FXML
    private JFXTextField firstNameInput;

    @FXML
    private JFXRadioButton maleRadio;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton femaleRadio;

    @FXML
    private JFXTextArea specialRequestsInput;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXTextField adressInput;

    @FXML
    private JFXTextField emaillAdressConfInput;

    @FXML
    private JFXTextField emaillAdressInput;

    @FXML
    private JFXTextField lastNameInput;

    @FXML
    private JFXTextField ageInput;

    @FXML
    private JFXTextArea errorText;

    private ReservationImpl reservationService;

    @Autowired
    public ReservationController(ReservationImpl reservationImpl) {
        this.reservationService = reservationImpl;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(JavaFxApplication.chosenHotel);
        errorText.setVisible(false);
    }

    public void getClientDetails(ActionEvent event) {
        JavaFxApplication.client.setCode(generateRandomReference());
        JavaFxApplication.client.setFirstName(firstNameInput.getText());
        JavaFxApplication.client.setLastName(lastNameInput.getText());
        JavaFxApplication.client.setAdress(adressInput.getText());
        JavaFxApplication.client.setEmail(emaillAdressInput.getText());
        JavaFxApplication.client.setAge(ageInput.getText());
        JavaFxApplication.reservation.setClient(JavaFxApplication.client);
        int result = reservationService.save(JavaFxApplication.reservation);

        if (result == 1) {
            firstNameInput.getScene().getWindow().hide();
            FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
            Parent root = fxWeaver.loadView(PaymentController.class);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } else {
            errorText.setVisible(true);
        }
    }

    public void goBackTosearch(ActionEvent event) throws IOException {
        firstNameInput.getScene().getWindow().hide();
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ResultsPageController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}
