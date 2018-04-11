/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.User;
import static gui.controller.LoginController.loggduser;
import static gui.controller.ServiceAdminController.P;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudUser;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeDresseurController implements Initializable {

    public static User U= new User();
    private ObservableList <User> data;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableView<User> tableView;
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
    private Button Rdv;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CrudUser myTool = new CrudUser();
        User u = new User();
        data= FXCollections.observableArrayList();
        
        for(int i=0;i<myTool.SelectAll().size();i++)
        {
            
            u=(User) myTool.SelectAll().get(i);
            if (u.getRoles().equals("a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}"))
            data.add(u);
        }
         
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
       
                                                                      
      
        tableView.setItems(null);
        tableView.setItems(data);
         
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

    @FXML
    private void SetRdv(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            U=tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
            //id.setText(String.valueOf(p.getId()));
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/RdvDresseur.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une reclamation");
            }
    }
    
}