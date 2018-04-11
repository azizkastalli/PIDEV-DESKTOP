/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import static gui.controller.ListeRdvController.R;
import static gui.controller.LoginController.loggduser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudRdv_dresseur;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierRDVController implements Initializable {

    @FXML
    private TextField idd;
    @FXML
    private TextField idc;
   
    @FXML
    private TextField ida;
    @FXML
    private ComboBox<String> etat;
    private TextField lieu;
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
    @FXML
    private Button Modifer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ida.setText(String.valueOf(ListeRdvController.R.getId_animal()));
        idc.setText(String.valueOf(ListeRdvController.R.getId_client()));
        Date date1 = ListeRdvController.R.getDate_rdv();
        LocalDate date2 = date1.toLocalDate();
        date.setValue(date2);
        idd.setText(ListeRdvController.R.getId_dresseur());
        etat.getItems().addAll("confirmé","non confirmé");
        etat.setValue((ListeRdvController.R.getEtat1()));
        ida.setEditable(false);
        idc.setEditable(false);
        date.setEditable(false);
        idd.setEditable(false);
       
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
            case "acceuil1":
                destination="acceuil.fxml";
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
        MenuController menu = new MenuController();
        menu.GestionMenu(event);

    }

    @FXML
    private void Modif(ActionEvent event) throws SQLException, IOException {
       
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage=new Stage();
               CrudRdv_dresseur myTool = new CrudRdv_dresseur();
               
               if ((ida.getText().equals("")) && (idc.getText().equals("")) && (date.getValue().toString().compareTo("") == 0) && (lieu.getText().compareTo("") == 0)&& (idd.getText().compareTo("") == 0)) {
            JOptionPane.showMessageDialog(null,"un ou plusieurs champs sont vides");

            } 
                  else {
                      
        /*int id = Integer.parseInt(ida.getText());
        R.setId_animal(id);
         
        LocalDate date1 = this.date.getValue();
        Date date2 = Date.valueOf(date1);
        R.setDate_rdv(date2);
        
        R.setId_client(idc.getText());
        R.setId_dresseur(idd.getText());*/
        //boolean etatt = String.valueOf(etat.getText());
         String etatt= etat.getValue();
       switch (etatt)
       {
           case "non confirmé": 
               R.setEtat(false);
               break ;
           case "confirmé":
               R.setEtat(true);
               break;
       }
                   System.out.println(etatt);
        myTool.Update(R);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Modification reussite !");
           alert1.showAndWait();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/ListeRdv.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                }
                      
                  }
        else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ListeRdv.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }
        
    }
    
}
