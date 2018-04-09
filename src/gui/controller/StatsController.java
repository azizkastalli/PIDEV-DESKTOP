/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.AnimalPerdu;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import utils.Dbconnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatsController implements Initializable {
    
    
    public static AnimalPerdu P;
    ObservableList<PieChart.Data> Chart;
    //ArrayList <String> etat = new ArrayList<String>();
    //ArrayList <Integer> nbr = new ArrayList<Integer>();
    @FXML
    private PieChart pieChart;
    PreparedStatement pst;
    ResultSet rs;
    Connection cnx;
    @FXML
    private HBox ev;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            Chart = FXCollections.observableArrayList();
            int  perdu = 0;
            int trouve = 0;
            try {
                cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/zanimauxintegré", "root", "");
                pst =cnx.prepareStatement("Select etat from animalperdu");
                rs=pst.executeQuery();
                ArrayList<Integer> state = new ArrayList<>();
                while(rs.next()) {
                    //    System.out.println(rs.getBoolean("etat"));
                    
                    if (rs.getBoolean("etat")==false )
                        
                        state.add(0);
                    else
                        state.add(1);
                }
                
                for(Integer i :state)
                {
                    switch (state.get(i)){
                        case 0:
                            System.out.println("perdu = "+i);
                            
                            perdu ++;
                           
                            break;
                            
                            
                        case 1:
                            System.out.println("trouvé = "+i);
                            trouve++;
                            break;
                            
                            
                    }
                    
                    
                    
                }
                
                 
                
            } catch (SQLException ex) {
                Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("animal perdu = "+perdu+"animal trouve ="+trouve);
            Chart.add(new PieChart.Data("perdu",perdu));
              Chart.add(new PieChart.Data("trouve",trouve));
             pieChart.setData(Chart);
        } 

    @FXML
    private void HomeClick(ActionEvent event) {
    }

    @FXML
    private void ClickStore(MouseEvent event) {
    }

    @FXML
    private void ClickEvenement(MouseEvent event) {
    }

    @FXML
    private void ClickEncheres(MouseEvent event) {
    }

    @FXML
    private void ClickServices(MouseEvent event) {
    }

    @FXML
    private void ClickVeterinaire(MouseEvent event) {
    }

    @FXML
    private void ClickProduit(MouseEvent event) {
    }
        
        
   
    
}
