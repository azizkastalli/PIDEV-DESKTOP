/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entites.AnimalPerdu;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudAnimalperdu;
import utils.Dbconnection;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class ServiceAdminController implements Initializable {
    
    public static AnimalPerdu P= new AnimalPerdu();

    @FXML
    private HBox ev;
    @FXML
    private TableView<AnimalPerdu> tableA;
    //private TableColumn<AnimalPerdu, Integer> ada;
    //@FXML
    //private TableColumn<AnimalPerdu, Date> date;
    //private TableColumn<AnimalPerdu, String> lieu;
    //private TableColumn<AnimalPerdu, Boolean> etat;
    
    
    private ObservableList <AnimalPerdu> data;
   // private Dbconnection dc;
    @FXML
    private Button button;
    @FXML
    private TableColumn<AnimalPerdu, Integer> ida;
    @FXML
    private TableColumn<AnimalPerdu, Date> datee;
    @FXML
    private TableColumn<AnimalPerdu, String> lieuu;
    @FXML
    private TableColumn<AnimalPerdu, String> etatt;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //dc=new Dbconnection();
        // TODO
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
          
            
                //app_stage.hide(); //optional
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
          
            
                //app_stage.hide(); //optional
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
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void Load(ActionEvent event) throws SQLException {
         //Connection conn=dc.getConnection();
        CrudAnimalperdu myTool = new CrudAnimalperdu();
        AnimalPerdu p = new AnimalPerdu();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.SelectAll().size();i++)
        {
            p=(AnimalPerdu) myTool.SelectAll().get(i);
            data.add(p);
        }

        
        ida.setCellValueFactory(new PropertyValueFactory<>("id_animal"));
        datee.setCellValueFactory(new PropertyValueFactory<>("date_disparition"));
        lieuu.setCellValueFactory(new PropertyValueFactory<>("lieu_disparition"));
        etatt.setCellValueFactory(new PropertyValueFactory<>("etat1"));
                                                                              
      
        //tableA.setItems(null);
        tableA.setItems(data);
        
        
    }

    AnimalPerdu A = new AnimalPerdu();
        CrudAnimalperdu myTool = new CrudAnimalperdu();
    @FXML
    private void Delete(ActionEvent event) throws IOException, SQLException {
        
        
                 if (tableA.getSelectionModel().getSelectedItem()!=null)
                 {
                      //id.setText(String.valueOf(p.getId()));
           Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){ 
            ((Node)event.getSource()).getScene().getWindow().hide();
            A=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
            System.out.println(A.getId());
            
                myTool.Delete(A);
               
            
            
            Alert alert1 = new Alert(AlertType.INFORMATION);
        alert1.setTitle("I have a great message for you!");
        alert1.setHeaderText(null);
        alert1.setContentText("Suppression Réussite réussite !");
        alert1.showAndWait();
            
           Stage stage=new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
  
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
             ((Node)event.getSource()).getScene().getWindow().hide();
        }
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une reclamation");
            }
                 }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
       
       
        
         if (tableA.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            P=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
            //id.setText(String.valueOf(p.getId()));
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ModifierANP.fxml"));
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
    private void Referto(ActionEvent event) throws IOException {
        
             Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AnimalsdfAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            
        
        
    }

    }

   

    

