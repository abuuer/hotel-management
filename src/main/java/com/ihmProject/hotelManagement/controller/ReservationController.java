package com.ihmProject.hotelManagement.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.StageManager;
import static com.ihmProject.hotelManagement.controller.SearchController.generateRandomReference;
import com.ihmProject.hotelManagement.spring.service.fac.SignupService;
import com.ihmProject.hotelManagement.spring.service.impl.ReservationImpl;
import com.ihmProject.hotelManagement.spring.service.impl.SignUpImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
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
    private Label inputError;

    @FXML
    private JFXTextArea errorText;

    @FXML
    private ImageView userProfil;

    @FXML
    private Label userNameLabel;

    private ReservationImpl reservationService;
    private SignUpImpl signupService;

    @Autowired
    public ReservationController(ReservationImpl reservationService, SignUpImpl signupService) {
        this.reservationService = reservationService;
        this.signupService = signupService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(JavaFxApplication.chosenHotel);
        errorText.setVisible(false);
        inputError.setVisible(false);
        userNameLabel.setText(JavaFxApplication.login.getUserName());
    }

    public void getClientDetails(ActionEvent event) {
        errorText.setVisible(false);
        JavaFxApplication.client.setCode(generateRandomReference());
        JavaFxApplication.client.setFirstName(firstNameInput.getText());
        JavaFxApplication.client.setLastName(lastNameInput.getText());
        JavaFxApplication.client.setAdress(adressInput.getText());
        JavaFxApplication.client.setEmail(emaillAdressInput.getText());
        JavaFxApplication.client.setAge(ageInput.getText());
        JavaFxApplication.reservation.setClient(JavaFxApplication.client);
        System.out.println("login user = :" + JavaFxApplication.login.getUserName());
        System.out.println("sign up : " + signupService.
                findByLoginUserName(JavaFxApplication.login.getUserName()));
        JavaFxApplication.client.setSignUp(signupService.
                findByLoginUserName(JavaFxApplication.login.getUserName()));

        if (lastNameInput.getText().isEmpty() || ageInput.getText().isEmpty()
                || emaillAdressInput.getText().isEmpty()
                || !emaillAdressInput.getText().equals(emaillAdressConfInput.getText())) {
            inputError.setVisible(true);
        } else {
            int result = reservationService.save(JavaFxApplication.reservation);
            if (result == 1) {
                firstNameInput.getScene().getWindow().hide();
                FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
                Parent root = fxWeaver.loadView(PaymentController.class);
                StageManager.changeScene(inputError, root);
            } else {
                errorText.setVisible(true);
            }
        }

    }

    public void goBackTosearch(ActionEvent event) throws IOException {
        firstNameInput.getScene().getWindow().hide();
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ResultsController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}
