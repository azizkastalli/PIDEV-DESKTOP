/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AcceuilController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private ImageView vet;
    @FXML
    private Label vete;
    @FXML
    private ImageView pan;
    @FXML
    private Label pani;
    @FXML
    private ImageView enc;
    @FXML
    private Label ench;
    @FXML
    private ImageView sto;
    @FXML
    private Label stor;
    @FXML
    private ImageView ser;
    @FXML
    private Label serv;
    @FXML
    private ImageView eve;
    @FXML
    private Label even;
    @FXML
    private Label espace;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void minimize(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    private void close(ActionEvent event) {
        System.exit(0);
    }

    private void open_mails(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/views/MailsUi.fxml"));
        parent.getChildren().removeAll();
        parent.getChildren().setAll(fxml);   
    }

    private void RubriqueMagasin(MouseEvent event) throws IOException {

    }

    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    
}
