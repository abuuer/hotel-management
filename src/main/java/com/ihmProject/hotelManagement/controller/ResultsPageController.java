/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.controller;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.MainPageController;
import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.service.impl.HotelImpl;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.controlsfx.control.Rating;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author anoir
 */
@Component
@FxmlView("/fxml/ResultsPage.fxml")
public class ResultsPageController implements Initializable {

    @FXML
    private ImageView backGroundImage;

    @FXML
    private JFXButton resultsBackToSearchBtn;

    @FXML
    private ImageView hbChildImage;

    @FXML
    private Label resHotelName;

    @FXML
    private Label resCityName;

    @FXML
    private Rating resHotelStars;
    @FXML
    private ImageView hbChildImage1;

    @FXML
    private Label resHotelName1;

    @FXML
    private Label resCityName1;

    @FXML
    private Rating resHotelStars1;

    @FXML
    private ImageView hbChildImage11;

    @FXML
    private Label resHotelName11;

    @FXML
    private Label resCityName11;

    @FXML
    private Rating resHotelStars11;

    @FXML
    private Button nextButton;

    @FXML
    private Label resPageNumber;

    private HotelImpl hotel;
    private List<Hotel> listHotels = new ArrayList<>();
    private int i = 0;
    int cmp = listHotels.size();
    int pageNumber = 1;
    int x = ((int)(listHotels.size() / 3)) + 1;

    @Autowired
    public ResultsPageController(HotelImpl hotel) {
        this.hotel = hotel;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listHotels = hotel.findByCity(JavaFxApplication.cityName);
        resCityName.setText(JavaFxApplication.cityName);
        System.out.println("hi");
        System.out.println(listHotels);
        displayHotels();
        resPageNumber.setText(pageNumber + " / " + x);
    }

    public void goToNextPage() {
        cmp = cmp - 3;
        if (cmp > 3) {
            resPageNumber.setText(pageNumber++ + " / " + x);
            System.err.println(pageNumber++);
            this.i = this.i + 3;
        } else {
            resPageNumber.setText((listHotels.size() / 3) + 1 + " / " + x);
            this.i = listHotels.size() - 3;
        }
        displayHotels();

    }

    public void goToPreviousPage() {
        cmp = cmp - 3;
        if (cmp > 0) {
            resPageNumber.setText(pageNumber-- + " / " + x);
            this.i = this.i - 3;
        } else {
            resPageNumber.setText("1 / " + x);
            this.i = 0;
        }
        displayHotels();

    }

    public void displayHotels() {
        int i = this.i;
        System.out.println(listHotels.size());
        resCityName.setText(listHotels.get(i).getCity());
        resHotelName.setText(listHotels.get(i).getName());
        resHotelStars.setRating(listHotels.get(i).getStars());
        resCityName1.setText(listHotels.get(i + 1).getCity());
        resHotelName1.setText(listHotels.get(i + 1).getName());
        resHotelStars1.setRating(listHotels.get(i + 1).getStars());
        resCityName11.setText(listHotels.get(i + 2).getCity());
        resHotelName11.setText(listHotels.get(i + 2).getName());
        resHotelStars11.setRating(listHotels.get(i + 2).getStars());
    }

    public void goBackToLogin(ActionEvent event) throws IOException {
        resCityName.getScene().getWindow().hide();
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainPageController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
