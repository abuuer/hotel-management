/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihmProject.hotelManagement;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 *
 * @author anoir
 */
public class StageManager {
    public static void changeScene(Label label,Parent controller ){
        label.getScene().getWindow().hide();
        Parent root = controller;
        Scene scene = new Scene(root);
        JavaFxApplication.newStage.setScene(scene);
        JavaFxApplication.newStage.show();
        JavaFxApplication.newStage.setResizable(false);
    }
}
