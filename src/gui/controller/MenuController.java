/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author azizkastalli
 */
public class MenuController {
    
    
    public MenuController(){}
    
    public void GestionMenu(MouseEvent event)
    {
        String dest=""; 
        String destination=""; 
        String type = event.getSource().getClass().getName();
        
        if(type.equals("javafx.scene.control.Label"))
          {
          Label ev = (Label) event.getSource();
          dest=ev.getId();
          }
         else if(type.equals("javafx.scene.image.ImageView"))
         {
         ImageView ev = (ImageView) event.getSource();
         dest=ev.getId();
         }
       
        switch (dest) {
            case "vet":
            case "veterinaires":
            case "vete":
                destination="RubriqueVeterinaire.fxml";
                break;
            case "espace":
                destination="EspaceMagasin.fxml";
                break;
            case "enc":
            case "ench":
                destination="Encheres.fxml";
                break;
            case "acceuil":
                destination="acceuil.fxml";
                break;
            case "services":
            case "serv":
            case "ser":
                destination="RubriqueServices.fxml";
                break;
            case "produits":
                destination="RubriqueProduits.fxml";
                break;
            case "evenements":
            case "even":
            case "eve":
                destination="RubriqueEvenements.fxml";
                break;
            case "pan":
            case "pani":
                destination="Panier.fxml";
                break;
            case "stor":
            case "sto":
                destination="Store.fxml";
                break;
            default:
                break;
        }
     
        if(destination!="")
        {
 
        
         Parent home_page_parent = null;
            try {
                home_page_parent = FXMLLoader.load(getClass().getResource("/gui/"+destination));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
       
        }
       
          }
    
}
