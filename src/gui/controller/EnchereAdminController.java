/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Journal;
import entites.Session;
import java.sql.Time;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceJournal;
import services.CrudSession;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class EnchereAdminController implements Initializable {

    @FXML
    private HBox ev;
    @FXML
    private TableColumn<Session, Integer> id;
    @FXML
    private TableColumn<Session, Double> mise;
    @FXML
    private TableColumn<Session, String> gagnant;
    @FXML
    private TableColumn<Session, String> etat;
    @FXML
    private TableView<Session> table;
    
    private final TableView<Journal> tableH = new TableView<>();
    @FXML
    private Pane tablePane;
    @FXML
    private TableColumn<Session, CheckBox> action;
    @FXML
    private Button delete;

     ServiceJournal SJ = new ServiceJournal();
     CrudSession CS = new CrudSession();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          CrudSession session =new CrudSession();
        ArrayList arrayList=null;
        try {
             arrayList = (ArrayList) session.SelectAll();
        } catch (SQLException ex) {}
        
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        
        id.setCellValueFactory(new PropertyValueFactory<Session,Integer>("id"));
        mise.setCellValueFactory(new PropertyValueFactory<Session,Double>("derniere_mise"));
        gagnant.setCellValueFactory(new PropertyValueFactory<Session,String>("id_gagnant"));
        etat.setCellValueFactory(new PropertyValueFactory<Session,String>("etat"));
        action.setCellValueFactory(new PropertyValueFactory<Session,CheckBox>("checkbox"));
        
    }    
    
      @FXML
    private void ClickStore(MouseEvent event) {
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/StoreAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickEvenement(MouseEvent event) {
        
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/EvenementAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               // app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickEncheres(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/EnchereAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickServices(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
             //   app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickVeterinaire(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/VeterinaireAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickProduit(MouseEvent event) {
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ProduitAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
    
    @FXML
    private void HomeClick(ActionEvent event) {
              try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/HomeAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               // app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void HistoriqueSession(MouseEvent event)
    {
        ArrayList <Journal>liste = new ArrayList<Journal>();
        Session S = table.getSelectionModel().getSelectedItem();        
         
        try {
           liste = (ArrayList) SJ.SelectJournal(S);
        } catch (SQLException ex) {
            Logger.getLogger(EnchereAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList observableList = FXCollections.observableArrayList(liste);
        tableH.setItems(observableList);        

        TableColumn client = new TableColumn("nom client");
        TableColumn miseclient = new TableColumn("mise");
        TableColumn heureclient = new TableColumn("heure de mise");
       
        client.setCellValueFactory(new PropertyValueFactory<Journal,String>("id_client"));
        miseclient.setCellValueFactory(new PropertyValueFactory<Journal,Double>("mise"));
        heureclient.setCellValueFactory(new PropertyValueFactory<Journal,Time>("date_mise"));
        
        
        tablePane.getChildren().remove(table);
        tableH.getColumns().addAll(client,miseclient,heureclient);
        tablePane.getChildren().add(tableH);
   //     tableH.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
        
    }

    @FXML
    private void DeleteSessions(MouseEvent event) {
        
            ObservableList<Session> data = table.getItems();
               ObservableList<Session> oblist = FXCollections.observableArrayList();
               
               for(Session S : data)
               {
                  if(S.getCheckbox().isSelected())
                  {
                      try {
                          CS.Delete(S); 
                          SJ.DeleteJournal(S);
                          oblist.add(S);
                      } catch (SQLException ex) {
                          Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
               }
             data.removeAll(oblist);
        
    }
    
}
