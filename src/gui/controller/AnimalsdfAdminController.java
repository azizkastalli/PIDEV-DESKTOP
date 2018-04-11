/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;


import entites.AnimalSdf;
import static gui.controller.ListeAnimauxsdfController.M;
import static gui.controller.LoginController.loggduser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AnimalsdfAdminController implements Initializable {
    
    public static AnimalSdf P= new AnimalSdf();
     AnimalSdf p = new AnimalSdf();
     CrudAnimalsdf myTool = new CrudAnimalsdf();

    @FXML
    private HBox ev;
    @FXML
    private TableView<AnimalSdf> tableA;
    
    @FXML
    private TableColumn<AnimalSdf, Date> date;
    @FXML
    private TableColumn<AnimalSdf, String> lieu;
    @FXML
    private TableColumn<AnimalSdf, String> sexe;
    @FXML
    private TableColumn<AnimalSdf, ImageView> img;
    private TableColumn<AnimalSdf, Integer> idc;
    private ObservableList <AnimalSdf> data;
    @FXML
    private Button adopt;
    @FXML
    private TableColumn<AnimalSdf, Integer> id;
    //private FileInputStream fis;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        data= FXCollections.observableArrayList();
        try {
            for(int i=0;i<myTool.SelectAll().size();i++)
            {
                p=(AnimalSdf) myTool.SelectAll().get(i);
                       if (p.getEtat()==1);
        {
            Notifications N = Notifications.create()
                    .title("Demande Adoption")
                    .text("l'utilistaeur "+ p.getId_client()+" a envoyer un demande d'adoption pour l'animal numero "+p.getId())
                    .position(Pos.CENTER)
                    .hideAfter(Duration.seconds(20));
            
                    N.showConfirm();     
                    System.out.println(p.getEtat());
        }
                data.add(p);
         
            }
         
        
            
        } catch (SQLException ex) {
            Logger.getLogger(AnimalsdfAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_trouvaille"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_trouvaille"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        img.setCellValueFactory(new PropertyValueFactory<>("IV"));
        
        //tableA.setItems(null);
        tableA.setItems(data);
        
       
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
        alert1.setContentText("Suppression RÃ©ussite rÃ©ussite !");
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
         try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
                                 }
    }

    @FXML
    private void Aprove(ActionEvent event) throws SQLException {
         if (tableA.getSelectionModel().getSelectedItem()!= null){
         tableA.getSelectionModel().getSelectedItem().setEtat(2);
        M=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
            
            AnimalSdf asdf= new AnimalSdf(M.getId(), M.getSexe(), M.getDate_trouvaille(),
           M.getNom_image(), M.getEtat(), M.getLieu_trouvaille(), M.getId_client(), M.getId_categorie());
            CrudAnimalsdf adf = new CrudAnimalsdf();
            adf.Update(asdf);
            JOptionPane.showMessageDialog(null, "demande approuvé avec succes");
    }
    }

    @FXML
    private void Try(MouseEvent event) throws SQLException {
        
         P=tableA.getItems().get(tableA.getSelectionModel().getSelectedIndex());
        AnimalSdf asdf= new AnimalSdf(P.getId(), P.getSexe(), P.getDate_trouvaille(), P.getNom_image(),
                P.getEtat(), p.getLieu_trouvaille(), P.getId_client(), P.getId_categorie());
       // CrudAnimalsdf adf = new CrudAnimalsdf();
         //   adf.SelectAll();
        
        
        if (asdf.getEtat()==1)
        {
            adopt.setVisible(true);
        }
        else {
            adopt.setVisible(false);
        }
    }
    
}
