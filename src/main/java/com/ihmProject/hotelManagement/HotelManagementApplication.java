package com.ihmProject.hotelManagement;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelManagementApplication {

    public static void main(String[] args) {
        //SpringApplication.run(HotelManagementApplication.class, args);
        Application.launch(JavaFxApplication.class, args);

    }


}
