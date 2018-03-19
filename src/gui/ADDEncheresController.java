/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Encheres;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.CrudEncheres;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class ADDEncheresController implements Initializable {

    @FXML
    private Button btadd;
    @FXML
    private TextField tfmise;
    @FXML
    private DatePicker tfdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void ADDEcheres(ActionEvent event) throws SQLException {
        
        double mise = Double.parseDouble(tfmise.getText());
        Date date = java.sql.Date.valueOf(tfdate.getValue()); 
        Encheres E = new Encheres(99,mise,"10","11",date);
        CrudEncheres CE = new CrudEncheres();
        CE.Create(E);
        
    }   
    
}
