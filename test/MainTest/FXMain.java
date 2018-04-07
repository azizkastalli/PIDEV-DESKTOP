/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTest;

import gui.controller.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author azizkastalli
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                 Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/Acceuil.fxml"));
        } catch (IOException ex) {
           
        }
        
        Scene scene = new Scene(root);    
        primaryStage.setTitle("test");
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
