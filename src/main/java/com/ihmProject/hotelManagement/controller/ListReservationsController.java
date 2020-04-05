package com.ihmProject.hotelManagement.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.MainPageController;
import com.ihmProject.hotelManagement.spring.bean.Reservation;
import com.ihmProject.hotelManagement.spring.service.impl.ReservationImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
@Component
@FxmlView("/fxml/ListReservation.fxml")
public class ListReservationsController implements Initializable {
	
	 private ReservationImpl reservationService;
	 
    @FXML
    private ImageView backGroundImage;

    @FXML
    private JFXTextField firstNameInput;

    @FXML
    private JFXTextField lastNameInput;

    @FXML
    private JFXButton gobackbtn;
    

    @FXML
    private TableView<Reservation> tableView;

    @FXML
    private TableColumn<Reservation, Long> id;

    @FXML
    private TableColumn<Reservation, String> reference;

    @FXML
    private TableColumn<Reservation, String> checkIn;

    @FXML
    private TableColumn<Reservation, String> checkOut;

    @FXML
    private TableColumn<Reservation, String> adults;

    @FXML
    private TableColumn<Reservation, String> children;

    @FXML
    private TableColumn<Reservation, String> rooms;
    
    @FXML
    private JFXTextArea textarea;
    

    @FXML
    void goBackTosearch(ActionEvent event) {
    	gobackbtn.getScene().getWindow().hide();
  	  FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class
  		        );
  		        Parent root = fxWeaver.loadView(MainPageController.class
  		        );
  		        Scene scene = new Scene(root);
  		        Stage stage = new Stage();
  		        stage.setScene(scene);
  		        stage.show();
  		        stage.setResizable(false);
    }
   // private List<Reservation> parseReservationList(){ 	
    //}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firstNameInput.setText(JavaFxApplication.client.getFirstName());
		lastNameInput.setText(JavaFxApplication.client.getLastName());
		
		List<Reservation> listReservation =reservationService.findByUserName(JavaFxApplication.login.getUserName());
		for(Reservation r : listReservation) {
			String s = r.toString();
			textarea.appendText(s);
		}
		/*id.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
		reference.setCellValueFactory(new PropertyValueFactory<User,String>("reference"));
		checkIn.setCellValueFactory(new PropertyValueFactory<User,String>("checkIn"));
		checkOut.setCellValueFactory(new PropertyValueFactory<User,String>("checkOut"));
		adults.setCellValueFactory(new PropertyValueFactory<User,String>("adults"));
		children.setCellValueFactory(new PropertyValueFactory<User,String>("children"));
		rooms.setCellValueFactory(new PropertyValueFactory<User,String>("rooms"));
		
		tableView.getItems().setAll(parseReservationList());*/
		}
		
		
}

