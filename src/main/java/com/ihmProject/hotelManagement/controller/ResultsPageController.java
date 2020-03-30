/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.controller;

import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.service.impl.HotelImpl;
import com.jfoenix.controls.JFXNodesList;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author anoir
 */
@Component
@FxmlView("ResultsPage.fxml")
public class ResultsPageController implements Initializable {

    private HotelImpl hotel;
    private ObservableList<String> data;
    @FXML
    private JFXNodesList nodeListHotels;
    @FXML
    private ImageView resHotelImage;
    @FXML
    private Label resHotelName;
    @FXML
    private Label resCityName;
    @FXML
    private Label resStars;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(String cityName) {
        resCityName.setText(cityName);
       // data = FXCollections.observableArrayList();
        List<Hotel> results = hotel.findByCity("Marrakesh");
        System.out.println(results);
//        for (Hotel result : results) {
//            this.resHotelName.setText(result.getCity());
//            this.resStars.setText(result.getZipCode());
//        }
        

    }

}
