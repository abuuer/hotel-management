/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement.controller;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.StageManager;
import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.service.impl.HotelImpl;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class ResultsController implements Initializable {

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

    @FXML
    private HBox bigHBox;

    @FXML
    private HBox bigHBox1;

    @FXML
    private HBox bigHBox2;

    @FXML
    private VBox hbChildVbox;

    @FXML
    private VBox hbChildVbox1;

    @FXML
    private VBox hbChildVbox2;

    @FXML
    private ImageView userProfil;

    @FXML
    private Label userNameLabel;

    private HotelImpl hotel;
    private List<Hotel> listHotels = new ArrayList<>();
    private int i = 0;
    int cmp = listHotels.size();
    static int pageNumber = 1;
    int x = ((int) (listHotels.size() / 3)) + 2;

    @Autowired
    public ResultsController(HotelImpl hotel) {
        this.hotel = hotel;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listHotels = hotel.findByCity(JavaFxApplication.chosenCity);
        resCityName.setText(JavaFxApplication.chosenCity);
        System.out.println("hi");
        System.out.println(listHotels);
        try {
            displayHotels();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ResultsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resPageNumber.setText(pageNumber + " / " + x);
        userNameLabel.setText(JavaFxApplication.login.getUserName());

    }

    public void goToNextPage() throws FileNotFoundException {
        cmp = cmp - 3;
        if (cmp > 3) {
            resPageNumber.setText(pageNumber++ + " / " + x);
            this.i = this.i + 3;
        } else {
            resPageNumber.setText((listHotels.size() / 3) + 1 + " / " + x);
            this.i = listHotels.size() - 3;
        }
        displayHotels();
    }

    public void goToPreviousPage() throws FileNotFoundException {
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

    public void displayHotels() throws FileNotFoundException {
        int i = this.i;
        System.out.println("les i : " + i);
        hbChildImage.setImage(new Image(new FileInputStream(listHotels.get(i).getImage())));
        resCityName.setText(listHotels.get(i).getCity());
        resHotelName.setText(listHotels.get(i).getName());
        resHotelStars.setRating(listHotels.get(i).getStars());
        hbChildImage1.setImage(new Image(new FileInputStream(listHotels.get(i +1).getImage())));
        resCityName1.setText(listHotels.get(i + 1).getCity());
        resHotelName1.setText(listHotels.get(i + 1).getName());
        resHotelStars1.setRating(listHotels.get(i + 1).getStars());
        hbChildImage11.setImage(new Image(new FileInputStream(listHotels.get(i +2).getImage())));
        resCityName11.setText(listHotels.get(i + 2).getCity());
        resHotelName11.setText(listHotels.get(i + 2).getName());
        resHotelStars11.setRating(listHotels.get(i + 2).getStars());
    }

    public void goBackToLogin(ActionEvent event) throws IOException {
        resCityName.getScene().getWindow().hide();
        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(SearchController.class);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void goToReservation(MouseEvent event) throws IOException {
        Label labelIn = null;

        if (event.getSource() == bigHBox) {
            Node nodeOut = bigHBox.getChildren().get(1);
            if (nodeOut instanceof VBox) {
                labelIn = (Label) ((VBox) nodeOut).getChildren().get(0);
            }
            JavaFxApplication.chosenHotel = hotel.findByName(labelIn.getText());
            JavaFxApplication.reservation.setHotel(JavaFxApplication.chosenHotel);
        } else if (event.getSource() == bigHBox1) {
            Node nodeOut = bigHBox1.getChildren().get(1);
            System.out.println(nodeOut);
            if (nodeOut instanceof VBox) {
                labelIn = (Label) ((VBox) nodeOut).getChildren().get(0);
            }
            JavaFxApplication.chosenHotel = hotel.findByName(labelIn.getText());
            JavaFxApplication.reservation.setHotel(JavaFxApplication.chosenHotel);
        } else if (event.getSource() == bigHBox2) {
            Node nodeOut = bigHBox2.getChildren().get(1);
            System.out.println(nodeOut);
            if (nodeOut instanceof VBox) {
                labelIn = (Label) ((VBox) nodeOut).getChildren().get(0);
            }
            JavaFxApplication.chosenHotel = hotel.findByName(labelIn.getText());
            JavaFxApplication.reservation.setHotel(JavaFxApplication.chosenHotel);
        }

        FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ReservationController.class);
        StageManager.changeScene(resHotelName, root);
    }
}
