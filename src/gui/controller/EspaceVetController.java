/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Rdv_Veterinaire;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.CrudRdv_Vet;
import utils.Dbconnection;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EspaceVetController implements Initializable {

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
    private FontAwesomeIconView addstoreicon;
    @FXML
    private FontAwesomeIconView addenchersicon;
    @FXML
    private FontAwesomeIconView gererproduiticon;
    @FXML
    private FontAwesomeIconView gererenchersicon;
    @FXML
    private ImageView addstore;
    @FXML
    private ImageView gererproduit;
    @FXML
    private Label espace;
    
    
    @FXML
    private TableView<Rdv_Veterinaire> tbl_rdv;
    @FXML
    private TableColumn<Rdv_Veterinaire, String> col_id;
    @FXML
    private TableColumn<Rdv_Veterinaire, String> col_date;
    @FXML
    private TableColumn<Rdv_Veterinaire, String> col_client;
    @FXML
    private TableColumn<Rdv_Veterinaire, String> col_prix;
    @FXML
    private Text txt_client;
    @FXML
    private Text txt_date;
    @FXML
    private Text txt_duree;
    @FXML
    private Text txt_prix;
    @FXML
    private Text txt_etat;
    
    @FXML
    private Button btn_ref;
    @FXML
    private Button btn_accept;
    @FXML
    private HBox hb_msg_rdv;
    @FXML
    private Text txt_msg_rdv;
    
    List<Rdv_Veterinaire> lst_rdv ;
    List<Rdv_Veterinaire> lst_rdv_rech ;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initData();
    }    

    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }

    @FXML
    private void MenuEspace(MouseEvent event) {
           MenuController menu = new MenuController();
        menu.GestionMenuEspace(event);
    }
    
    private void initData(){
        lst_rdv = new ArrayList<>();
        Dbconnection cnx = Dbconnection.getInstance();
        CrudRdv_Vet serv = new CrudRdv_Vet(cnx.getConnection());
        
        try {
            lst_rdv = serv.SelectAllByVet(LoginController.loggduser.getId());
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_id.setCellValueFactory(new PropertyValueFactory<Rdv_Veterinaire, String>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<Rdv_Veterinaire, String>("date_rdv"));
        col_client.setCellValueFactory(new PropertyValueFactory<Rdv_Veterinaire, String>("id_client"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Rdv_Veterinaire, String>("prix"));
        
        if(lst_rdv.size() > 0){
         tbl_rdv.setItems(FXCollections.observableArrayList(lst_rdv));
          tbl_rdv.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Rdv_Veterinaire r = tbl_rdv.getSelectionModel().getSelectedItem();
                setData(r);
                
               btn_accept.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        r.setEtat(1);
                        CrudRdv_Vet serv = new CrudRdv_Vet(Dbconnection.getInstance().getConnection());
                        try {
                            serv.Update(r);
                            setData(r);
                            refData();
                        } catch (SQLException ex) {
                            Logger.getLogger(EspaceVetController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
               
               btn_ref.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        r.setEtat(2);
                        CrudRdv_Vet serv = new CrudRdv_Vet(Dbconnection.getInstance().getConnection());
                        try {
                            serv.Update(r);
                            setData(r);
                            refData();
                        } catch (SQLException ex) {
                            Logger.getLogger(EspaceVetController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
               
            }
            });
                  }
        
        
        
    }
    
    private void setData(Rdv_Veterinaire r){
        txt_client.setText("");
                txt_date.setText(String.valueOf(r.getDate_rdv()));
                txt_duree.setText(String.valueOf(r.getDuree_seance()));
                txt_prix.setText(String.valueOf(r.getPrix()));
                if(tbl_rdv.getSelectionModel().getSelectedItem().isEtat() == 0 ){
                txt_etat.setText("En Attente");
                btn_accept.setScaleY(1);
                btn_ref.setScaleY(1);

                }else if(tbl_rdv.getSelectionModel().getSelectedItem().isEtat() == 1 ){
                txt_etat.setText("Accepté");
                btn_accept.setScaleY(0);
                btn_ref.setScaleY(1);

                } else if(tbl_rdv.getSelectionModel().getSelectedItem().isEtat() == 2 ){
                txt_etat.setText("Refuser");
                btn_accept.setScaleY(0);
                btn_ref.setScaleY(0);
                
                }
    }
    
    private void refData(){
        lst_rdv = new ArrayList<>();
        Dbconnection cnx = Dbconnection.getInstance();
        CrudRdv_Vet serv = new CrudRdv_Vet(cnx.getConnection());
        
        try {
            lst_rdv = serv.SelectAllByVet(LoginController.loggduser.getId());
            tbl_rdv.setItems(FXCollections.observableArrayList(lst_rdv));
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
