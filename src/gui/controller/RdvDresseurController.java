/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Rdv_Dresseur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudRdv_dresseur;
import static gui.controller.LoginController.loggduser;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import services.CrudAnimalperdu;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RdvDresseurController implements Initializable {

    
    @FXML
    private TextField idc;
    @FXML
    private ComboBox<Integer> ida;
    private TextField etat;
    @FXML
    private TextField idd;
    @FXML
    private Pane menubar;
    @FXML
    private Label acceuil1;
    @FXML
    private Label services1;
    @FXML
    private Label produits1;
    @FXML
    private Label veterinaires1;
    @FXML
    private DatePicker date;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CrudAnimalperdu myTool = new CrudAnimalperdu();
       //etat.setEditable(false);  
       idc.setText(String.valueOf(loggduser.getId()));
       idd.setText(String.valueOf(ListeDresseurController.U.getId()));
       idc.setEditable(false);
       idd.setEditable(false);
       ArrayList  arrayList;
        
        try {
            arrayList = (ArrayList) myTool.ExtractId();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);
            ida.setItems(observableList);
        // TODO
        } catch (SQLException ex) {
        }
    }    

    @FXML
    private void ajout(ActionEvent event) throws SQLException, IOException {
        CrudRdv_dresseur myTool = new CrudRdv_dresseur();
        Rdv_Dresseur r = new Rdv_Dresseur();
         int id= ida.getValue();
        r.setId_animal(id);
        LocalDate daten = LocalDate.now();
        LocalDate date1 = this.date.getValue();
        if (date1.isBefore(daten)) {
            JOptionPane.showMessageDialog(null, "date inferieur a la date d'aujourd'hui");
        }
        else {
        Date date2 = Date.valueOf(date1);
        r.setDate_rdv(date2);}
        //r.setCoordonnees(coordonnees.getText());
        r.setId_client(idc.getText());
        r.setId_dresseur(idd.getText());
//boolean etatt = Boolean.getBoolean(etat.getText());
        
        myTool.Create(r);
        JOptionPane.showMessageDialog(null, "rendez vous ajoute");
         Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/ListeRdv.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
        
        
    }

    @FXML
    private void Menu(MouseEvent event) {
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
            case "services1":
            
            switch (loggduser.getRoles()){
             case"a:1:{i:0;s:11:\"ROLE_CLIENT\";}":
                destination="RubriqueServices.fxml";
                break;
                
             case"a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}" :
                 destination="ListeRdv.fxml";
                break;}
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
        MenuController menu = new MenuController();
        menu.GestionMenu(event);

    }
    
}