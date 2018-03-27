/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.AnimalPerdu;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.CrudAnimalperdu;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AnimalperduController implements Initializable {

    @FXML
    private TextField ida;
    @FXML
    private DatePicker date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField etat;
    @FXML
    private Button ajouter;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;
    @FXML
    private Label veterinaires;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
    @FXML
    private void ajout(ActionEvent event) throws SQLException {
        CrudAnimalperdu myTool = new CrudAnimalperdu();
        AnimalPerdu p = new AnimalPerdu();
        int id = Integer.parseInt(ida.getText());
        p.setId_animal(id);
        LocalDate date1 = this.date.getValue();
        Date date2 = Date.valueOf(date1);
        p.setDate_disparition(date2);
        p.setLieu_dispairition(lieu.getText());
       boolean etatt = Boolean.getBoolean(etat.getText());
        p.setEtat(etatt);
        myTool.Create(p);
        JOptionPane.showMessageDialog(null, "reclamation ajouté");
        
        
    }

    @FXML
    private void Menu(MouseEvent event) {
    }
    
    }

    
    

