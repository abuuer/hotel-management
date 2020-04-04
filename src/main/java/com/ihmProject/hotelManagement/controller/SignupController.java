package com.ihmProject.hotelManagement.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.MainPageController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/fxml/SignupPage.fxml")

public class SignupController implements Initializable {
	
	 
	 @FXML
	    private JFXTextField loginUsernameLabel;

	    @FXML
	    private JFXPasswordField loginPasswordLabel;

	    @FXML
	    private JFXButton registerLoginButton;

	    @FXML
	    private JFXButton registerButton;

	    @FXML
	    private JFXProgressBar loginProgress;

	    @FXML
	    private JFXButton registerBackToSearchBtn;

	    @FXML
	    private JFXTextField loginUsernameLabel1;

	    @FXML
	    private ToggleGroup Gender;
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	loginProgress.setVisible(false);
		}

	    @FXML
	    void registerAction(ActionEvent event) {
	    	PauseTransition pt = new PauseTransition();
			pt.setDuration(Duration.seconds(4));
			pt.setOnFinished(ev -> {
				System.out.println("signed up succesfully");
			});
			pt.play();
	    }

	    @FXML
	    void backToLoginAction(ActionEvent event) {
	    	registerLoginButton.getScene().getWindow().hide();
	    	  FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class
	    		        );
	    		        Parent root = fxWeaver.loadView(LoginPageController.class
	    		        );
	    		        Scene scene = new Scene(root);
	    		        JavaFxApplication.newStage.setScene(scene);
	    		        JavaFxApplication.newStage.show();
	    		        JavaFxApplication.newStage.setResizable(false);
	    }
	    
	    @FXML
	    void backToSearchAction(ActionEvent event) {
	    	registerBackToSearchBtn.getScene().getWindow().hide();
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
		
}
