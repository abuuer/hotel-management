package com.ihmProject.hotelManagement.controller;


import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("SignupPage.fxml")

public class SignupController implements Initializable {
	
	  @FXML
	    private ToggleGroup Gender;
	  
	   @FXML
	    private Button signUp;

	    @FXML
	    private Button Login;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void signupAction(ActionEvent e) {
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(4));
		pt.setOnFinished(ev -> {
			System.out.println("signed up succesfully");
		});
		pt.play();
	}
	
	@FXML
	public void loginAction(ActionEvent e) {
		signUp.getScene().getWindow().hide();
		
		
	}
		
}
