/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.AnimalSdf;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AnimalsdfController implements Initializable {

    @FXML
    private TextField idc;
    @FXML
    private TextField idca;
    @FXML
    private TextField sexe;
    @FXML
    private TextField img;
    @FXML
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
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
        CrudAnimalsdf myTool = new CrudAnimalsdf();
        AnimalSdf p = new AnimalSdf();
        int id = Integer.parseInt(idca.getText());
        p.setId_categorie(id);
        p.setSexe(sexe.getText());
        p.setNom_image(img.getText());
        LocalDate date1 = this.date.getValue();
        Date date2 = Date.valueOf(date1);
        p.setDate_trouvaille(date2);
        p.setLieu_trouvaille(lieu.getText());
        int id2 = Integer.parseInt(idc.getText());
        p.setId_client(id2);
        myTool.Create(p);
        JOptionPane.showMessageDialog(null, "declamation ajouté");
        
    }

    @FXML
    private void Menu(MouseEvent event) {
               MenuController menu = new MenuController();
        menu.GestionMenu(event);
    }
    
}
