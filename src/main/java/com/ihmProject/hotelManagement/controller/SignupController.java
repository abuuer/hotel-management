package com.ihmProject.hotelManagement.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ihmProject.hotelManagement.JavaFxApplication;
import com.ihmProject.hotelManagement.MainPageController;
import com.ihmProject.hotelManagement.spring.bean.SignUp;
import com.ihmProject.hotelManagement.spring.service.impl.SignUpImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/fxml/SignupPage.fxml")

public class SignupController implements Initializable {
	
	 
	 @FXML
	    private JFXPasswordField loginPasswordLabel;

	    @FXML
	    private JFXButton registerLoginButton;

	    @FXML
	    private JFXButton loginRegisterButton;

	    @FXML
	    private JFXButton registerBackToSearchBtn;

	    @FXML
	    private JFXTextField loginUsernameLabel;

	    @FXML
	    private JFXPasswordField loginPasswordLabel1;
	    
	    @FXML
	    private JFXTextArea errorText;
	   
	    
	    private SignUpImpl signUpService ; 
	    
	    @Autowired
	    public SignupController(SignUpImpl signUpImpl) {
	    	this.signUpService = signUpImpl ;
	    }
	    
	    
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	errorText.setVisible(false);
	    	
		}

	    @FXML
	    public void registerAction(ActionEvent event) {
	    		JavaFxApplication.signUp = new SignUp();
	    	 JavaFxApplication.signUp.setUserName(loginUsernameLabel.getText());
	    	 JavaFxApplication.signUp.setPassword(loginPasswordLabel.getText());
	    	 JavaFxApplication.signUp.setPassword(loginPasswordLabel1.getText());
	    	 if(loginUsernameLabel.getText().isEmpty() || loginPasswordLabel.getText().isEmpty() || !loginPasswordLabel.getText().equals(loginPasswordLabel1.getText()) )
	    	 {
	    		 errorText.setVisible(true);	 
	    	 }
	    	 else { 
	    		 int result = signUpService.save(JavaFxApplication.signUp);
	    		 if(result == 1) { 
	    			 loginUsernameLabel.getScene().getWindow().hide();
	                 FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class);
	                 Parent root = fxWeaver.loadView(MainPageController.class);
	                 Scene scene = new Scene(root);
	                 Stage stage = new Stage();
	    		        stage.setScene(scene);
	    		        stage.show();
	    		        stage.setResizable(false);
	                 System.out.println("signed up succesfully"); 
	    		 }
	    		 else {errorText.setVisible(true);}
	    	 }	
	    }

	    @FXML
	    void backToLoginAction(ActionEvent event) {
	    	registerLoginButton.getScene().getWindow().hide();
	    	  FxWeaver fxWeaver = JavaFxApplication.applicationContext.getBean(FxWeaver.class
	    		        );
	    		        Parent root = fxWeaver.loadView(LoginPageController.class
	    		        );
	    		        Scene scene = new Scene(root);
	    		        Stage stage = new Stage();
	    		        stage.setScene(scene);
	    		        stage.show();
	    		        stage.setResizable(false);
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
