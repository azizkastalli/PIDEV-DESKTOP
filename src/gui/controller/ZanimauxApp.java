/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author azizkastalli
 */
public class ZanimauxApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      
        Parent root = null;
        
        
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ZanimauxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   /*
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/HomeAdmin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ZanimauxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
        Scene scene = new Scene(root);    
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setTitle("test");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}