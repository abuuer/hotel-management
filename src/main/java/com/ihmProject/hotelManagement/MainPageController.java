/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement;

import com.ihmProject.hotelManagement.controller.ResultsPageController;
import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.service.impl.HotelImpl;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * FXML Controller class
 *
 * @author anoir
 */
@Component
@FxmlView("SearchPage.fxml")
public class MainPageController implements Initializable {

    private ConfigurableApplicationContext applicationContext;
    private Stage stage;

    private HotelImpl hotel;
    @FXML
    private JFXComboBox cmBoxCitiesSearch;
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
    }

    public void switchToReserch(ActionEvent event) throws IOException {
        System.out.println("Search clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ResultsPage.fxml"));
        Scene scene = new Scene((Pane) loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ResultsPageController r = new ResultsPageController();
        stage.setScene(scene);
        ResultsPageController controller = loader.<ResultsPageController>getController();
        controller.initData(this.cmBoxCitiesSearch.getValue().toString());
        stage.show();
        //(String)this.cmBoxCitiesSearch.itemsProperty().getValue().toString()
    }

}
