/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement;

import com.ihmProject.hotelManagement.controller.LoginPageController;
import com.ihmProject.hotelManagement.controller.ResultsPageController;
import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.service.impl.HotelImpl;
import com.ihmProject.hotelManagement.spring.service.impl.LoginImpl;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.security.auth.login.LoginContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * FXML Controller class
 *
 * @author anoir
 */
@Component
@FxmlView("/fxml/SearchPage.fxml")
public class MainPageController implements Initializable {

    public ConfigurableApplicationContext applicationContext;

    private HotelImpl hotel;
    //  private LoginImpl login ;
    @FXML
    private JFXComboBox cmBoxCitiesSearch;
    @FXML
    private Label searchError;

    private ObservableList<String> data;

    @Autowired
    public MainPageController(HotelImpl hotel) {
        this.hotel = hotel;
    }

    public void loadData() {
        System.out.println("hi");
        System.out.println(data);

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
        try {
            cmBoxCitiesSearch.getScene().getWindow().hide();
            JavaFxApplication.cityName = this.cmBoxCitiesSearch.getValue().toString();
            FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
            Parent root = fxWeaver.loadView(ResultsPageController.class);
            Scene scene = new Scene(root);
            JavaFxApplication.newStage.setScene(scene);
            JavaFxApplication.newStage.show();
            JavaFxApplication.newStage.setResizable(true);
        } catch (NullPointerException e) {
            searchError.setVisible(true);
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

}
