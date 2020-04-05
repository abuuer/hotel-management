/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement;

import com.ihmProject.hotelManagement.controller.LoginPageController;
import com.ihmProject.hotelManagement.controller.ResultsPageController;
import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.bean.Reservation;
import com.ihmProject.hotelManagement.spring.service.impl.HotelImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * FXML Controller class
 *
 * @author anoir
 */
@Component
@FxmlView("/fxml/SearchPage.fxml")
public class MainPageController implements Initializable {

    //  private LoginImpl login ;
    @FXML
    private JFXComboBox cmBoxCitiesSearch;
    @FXML
    private Label searchError;
    @FXML
    private JFXDatePicker checkInDate;

    @FXML
    private JFXDatePicker checkOutDate;

    @FXML
    private JFXSlider numberAdults;

    @FXML
    private JFXSlider numberChildren;

    @FXML
    private JFXSlider numberRooms;

    private ObservableList<String> data;
    public ConfigurableApplicationContext applicationContext;
    private HotelImpl hotel;

    @Autowired
    public MainPageController(HotelImpl hotel) {
        this.hotel = hotel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        List<Hotel> results = hotel.findAll();
        for (Hotel result : results) {
            if (!data.contains(result.getCity())) {
                data.add(result.getCity());
            }
        }
        this.cmBoxCitiesSearch.setItems(data);
        searchError.setVisible(false);
    }

    public void switchToReserch(ActionEvent event) throws IOException {
        System.out.println(checkInDate.getValue());
        System.out.println((int) numberAdults.getValue());
        System.out.println(generateRandomReference());
        JavaFxApplication.reservation.setCheckIn(checkInDate.getValue());
        JavaFxApplication.reservation.setCheckOut(checkOutDate.getValue());
        JavaFxApplication.reservation.setNumberOfAdults((int) numberAdults.getValue());
        JavaFxApplication.reservation.setNumberOfChilds((int) numberChildren.getValue());
        JavaFxApplication.reservation.setNumberOfRooms((int) numberRooms.getValue());
        JavaFxApplication.reservation.setReference(generateRandomReference());
        if (cmBoxCitiesSearch.getValue() == null) {
            searchError.setVisible(true);
        } else if (checkInDate.getValue() == null || checkOutDate.getValue() == null) {
            searchError.setVisible(true);
        } else {
            cmBoxCitiesSearch.getScene().getWindow().hide();
            JavaFxApplication.chosenCity = this.cmBoxCitiesSearch.getValue().toString();
            FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
            Parent root = fxWeaver.loadView(ResultsPageController.class);
            Scene scene = new Scene(root);
            JavaFxApplication.newStage.setScene(scene);
            JavaFxApplication.newStage.show();
            JavaFxApplication.newStage.setResizable(true);
        }

    }

    public void switchToLogin(ActionEvent event) throws IOException {
        cmBoxCitiesSearch.getScene().getWindow().hide();
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginPageController.class);
        Scene scene = new Scene(root);
        JavaFxApplication.newStage.setScene(scene);
        JavaFxApplication.newStage.show();
        JavaFxApplication.newStage.setResizable(false);
    }

    public static String generateRandomReference() {
        Random random = new Random();
        String generatedString = random.ints(48, 122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return "ref-" + generatedString;
    }

}
