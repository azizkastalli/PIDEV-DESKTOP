/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.AnimalSdf;
import static gui.controller.LoginController.loggduser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeAnimauxsdfController implements Initializable {

     public static AnimalSdf P= new AnimalSdf();
     public static AnimalSdf M= new AnimalSdf();
    @FXML
    private Label acceuil1;
    @FXML
    private Label services1;
    @FXML
    private Label produits1;
    @FXML
    private Label veterinaires1;
    @FXML
    private TableView<AnimalSdf> tableA;
   
    @FXML
    private TableColumn<AnimalSdf, Date> date;
    @FXML
    private TableColumn<AnimalSdf, String> lieu;
    @FXML
    private TableColumn<AnimalSdf, String> sexe;
    @FXML
    private TableColumn<AnimalSdf, ImageView> img;
    
    private ObservableList <AnimalSdf> data;
    @FXML
    private Button Modifier;
    @FXML
    private Button adopter;
 CrudAnimalsdf myTool = new CrudAnimalsdf();
    @FXML
    private Button and;
    @FXML
    private TableColumn<?, ?> id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        AnimalSdf p = new AnimalSdf();
        data= FXCollections.observableArrayList();
        try {
            for(int i=0;i<myTool.SelectAll().size();i++)
            {
                p=(AnimalSdf) myTool.SelectAll().get(i);
                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalsdfAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_trouvaille"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_trouvaille"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        img.setCellValueFactory(new PropertyValueFactory<>("IV"));
        tableA.setItems(null);
        tableA.setItems(data);
       
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
    private void Modif(ActionEvent event) throws IOException {
         if (tableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            P=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
            //id.setText(String.valueOf(p.getId()));
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ModifierASDF.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une reclamation");
            }
    }


    @FXML
    private void Try(MouseEvent event) {
        if (tableA.getSelectionModel().getSelectedItem()!= null)
        {
            System.out.println(tableA.getSelectionModel().getSelectedItem().getId_client());
                 if ((loggduser.getId())!= (tableA.getSelectionModel().getSelectedItem().getId_client()) )
       {
           Modifier.setDisable(true);
           
       }
                 else 
                 {
                      Modifier.setDisable(false);
                      
                 }
            }
        M=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
        if(M.getEtat()==2)
        {
            adopter.setDisable(true);
        }
        else 
        {
            adopter.setDisable(false);
        }
    }

    @FXML
    private void Adoter(ActionEvent event) throws SQLException {
        if (tableA.getSelectionModel().getSelectedItem()!= null){
         tableA.getSelectionModel().getSelectedItem().setEtat(1);
        M=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
            
            AnimalSdf asdf= new AnimalSdf(M.getId(), M.getSexe(), M.getDate_trouvaille(),
           M.getNom_image(), M.getEtat(), M.getLieu_trouvaille(), M.getId_client(), M.getId_categorie());
            CrudAnimalsdf adf = new CrudAnimalsdf();
            adf.Update(asdf);
            JOptionPane.showMessageDialog(null, "votre demande d'adoption a ete effectue");
    }
    }

    @FXML
    private void Ajout(ActionEvent event) throws IOException {
        Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Animalsdf.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void ListeAdopté(ActionEvent event) throws IOException {
         try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/AnimalAdopte.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
                                 }
    }
}
