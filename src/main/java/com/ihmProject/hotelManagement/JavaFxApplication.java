/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement;

import com.ihmProject.hotelManagement.spring.bean.Client;
import com.ihmProject.hotelManagement.spring.bean.Hotel;
import com.ihmProject.hotelManagement.spring.bean.Payment;
import com.ihmProject.hotelManagement.spring.bean.Reservation;
import com.ihmProject.hotelManagement.spring.bean.SignUp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author anoir
 */
public class JavaFxApplication extends Application {

    public static ConfigurableApplicationContext applicationContext;
    public static Stage newStage ; 
    public static String chosenCity ; 
    public static Hotel chosenHotel = new Hotel(); 
    public static Reservation reservation = new Reservation() ;
    public static Client client = new Client();
    public static Payment payment = new Payment();
    //edit : add this
    public static SignUp signUp = new SignUp();

    //this is where the spring starts
    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(HotelManagementApplication.class)
                .run(args);
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

    //this is where the javaFX
    @Override
    public void start(final Stage stage) {
    	JavaFxApplication.newStage = stage ;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainPageController.class);
        Scene scene = new Scene(root);
        JavaFxApplication.newStage.setScene(scene);
        JavaFxApplication.newStage.show();
        JavaFxApplication.newStage.setResizable(false);
    }
}
