/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class QuantiteController implements Initializable {

    @FXML
    private TextField nbrArticle;
    @FXML
    private Button AjouterArtcile;
    Produit p;
    @FXML
    private Label id;

    public static int qt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutArticle(ActionEvent event) {
        
        id.setText(""+StoreController.p.getId());
        nbrArticle.setText(nbrArticle.getText());
        qt = Integer.parseInt(nbrArticle.getText());
        System.out.println(id.getText());
        
       ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public TextField getNbrArticle() {
        return nbrArticle;
    }

    public void setNbrArticle(int nbrArticle) {
        
        this.nbrArticle.setText(""+nbrArticle);
    }

    public Label getId() {
        return id;
    }

    public void setId(int id) {
        
        this.id.setText(""+id);
    }
    
}
