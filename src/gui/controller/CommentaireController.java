/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import static gui.controller.LoginController.loggduser;
import entites.Commentaire;
import static gui.controller.StoreController.P;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.CrudCommentaire;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommentaireController implements Initializable {

    @FXML
    private VBox parent;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;
    @FXML
    private Label veterinaires;
    @FXML
    private Label evenements;
    @FXML
    private Label espace;
    @FXML
    private Text nomprod;
    @FXML
    private TextField fild;
    @FXML
    private Button comm;
    @FXML
    private ImageView imgprod;
    private ObservableList <Commentaire> data;
    @FXML
    private TableColumn<Commentaire, String> user;
    @FXML
    private TableColumn<Commentaire, String> comment;
    @FXML
    private TableColumn<Commentaire, Date> dat;
    @FXML
    private TableView<Commentaire> tableC;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          tableC.setEditable(true);
         comment.setCellFactory(TextFieldTableCell.forTableColumn());
         
                CrudCommentaire myTool = new CrudCommentaire();
        Commentaire c = new Commentaire();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.Selectcom(P.getLabel()).size();i++)
        {
            c=(Commentaire) myTool.Selectcom(P.getLabel()).get(i);
            data.add(c);
        }
        user.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        comment.setCellValueFactory(new PropertyValueFactory<>("texte"));
        dat.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableC.setItems(data); 
        try {
            
               
                Image img = new Image(new FileInputStream(P.getNom_image()),301,345,false,false);
                imgprod.setImage(img);
                nomprod.setText("Produit: " +P.getLabel());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       
            
    }    

    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }

    @FXML
    private void ajout(ActionEvent event) {
             
        try {
            if(fild.getText().equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("I have a great message for you!");
            alert.setHeaderText(null);
            alert.setContentText("Ecrit ton Commentaire SVP!!");
            alert.showAndWait();
            }
            else
            {
                try {
            CrudCommentaire CC = new CrudCommentaire();
            Commentaire c = new Commentaire();
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            c.setDate(date);
            c.setTexte(fild.getText());
            c.setId_cible(P.getLabel());
            c.setId_client(loggduser.getUsername());
            System.out.println(c.getId_cible());
            System.out.println(c.getDate());
            System.out.println(c.getTexte());
            CC.Create(c);
            
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("I have a great message for you!");
            alert1.setHeaderText(null);
            alert1.setContentText("Commentaire Ajouté!");
            alert1.showAndWait();
           Parent home_page_parent;
                
                    home_page_parent = FXMLLoader.load(getClass().getResource("/gui/Commentaire.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                     Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show(); 
                } catch (IOException ex) {
                    Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }
            } catch (SQLException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Editcomm(CellEditEvent edittedCell) throws SQLException
    {
    CrudCommentaire CC=new CrudCommentaire();
             if(loggduser.getUsername().equals(tableC.getSelectionModel().getSelectedItem().getId_client())){
            int i=tableC.getSelectionModel().getSelectedIndex();
               String nom=tableC.getSelectionModel().getSelectedItem().getId_cible();
           int id= CC.Selectcom(nom).get(i).getId();
                    Commentaire personSelected =  tableC.getSelectionModel().getSelectedItem(); 
                     personSelected.setTexte(edittedCell.getNewValue().toString());   
                       Commentaire L=new Commentaire(id,personSelected.getTexte());
                       CC.Update(L);
                        new Alert(Alert.AlertType.INFORMATION, "Commentaire modifié").show();
                
        }
                 else
                 {
                  new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas modifier cela!!").show();
                 }
    }

    @FXML
    private void Suppcomm(ActionEvent event) {
        
    }
   

}
    

