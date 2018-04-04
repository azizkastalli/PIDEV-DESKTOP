/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;


import entites.AnimalSdf;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AnimalsdfAdminController implements Initializable {
    
    public static AnimalSdf P= new AnimalSdf();

    @FXML
    private HBox ev;
    @FXML
    private TableView<AnimalSdf> tableA;
    @FXML
    private TableColumn<AnimalSdf, Integer> idca;
    @FXML
    private TableColumn<AnimalSdf, Date> date;
    @FXML
    private TableColumn<AnimalSdf, String> lieu;
    @FXML
    private Button button;
    @FXML
    private TableColumn<AnimalSdf, String> sexe;
    @FXML
    private TableColumn<AnimalSdf, String> img;
    @FXML
    private TableColumn<AnimalSdf, Integer> idc;
    private ObservableList <AnimalSdf> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void Load(ActionEvent event) throws SQLException {
        
        CrudAnimalsdf myTool = new CrudAnimalsdf();
        AnimalSdf p = new AnimalSdf();
        data= FXCollections.observableArrayList();
        for(int i=0;i<myTool.SelectAll().size();i++)
        {
            p=(AnimalSdf) myTool.SelectAll().get(i);
            data.add(p);
        }
        idca.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_trouvaille"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_trouvaille"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        img.setCellValueFactory(new PropertyValueFactory<>("nom_image"));
        idc.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        //tableA.setItems(null);
        tableA.setItems(data);
        
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
AnimalSdf A = new AnimalSdf();
        CrudAnimalsdf myTool = new CrudAnimalsdf();
    @FXML
    private void Delete(ActionEvent event) throws IOException, SQLException {
         if (tableA.getSelectionModel().getSelectedItem()!=null)
                 {
                      //id.setText(String.valueOf(p.getId()));
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){ 
            ((Node)event.getSource()).getScene().getWindow().hide();
            A=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
            System.out.println(A.getId());
            
                myTool.Delete(A);
               
            
            
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("I have a great message for you!");
        alert1.setHeaderText(null);
        alert1.setContentText("Suppression Réussite réussite !");
        alert1.showAndWait();
            
           Stage stage=new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/AnimalsdfAdmin.fxml"));
  
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
             ((Node)event.getSource()).getScene().getWindow().hide();
        }
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner une declaration");
            }
    }
    

    @FXML
    private void Referto(ActionEvent event) throws IOException {
        Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }
    
}
