/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import static gui.controller.ModifSuppEventController.E;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.FileChooser;
import rest.file.uploader.tn.FileUploader;
import services.CrudCategorie;
import services.CrudEvenement;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class ModifierEventController implements Initializable {
List<String> extension;
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
    private Label espace;
    @FXML
    private FontAwesomeIconView addstoreicon;
    @FXML
    private FontAwesomeIconView addenchersicon;
    @FXML
    private TextField Nom;
    @FXML
    private TextField nom_image;
    @FXML
    private TextField description;
  
    @FXML
    private Spinner<Integer> spin;
    @FXML
    private ComboBox<String> catégorie;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Nom.setText(E.getNom());
        nom_image.setText(E.getNom_image());
        description.setText(E.getDescription());
        
           
        SpinnerValueFactory<Integer> valueFactory =// 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 50000, E.getNbr_participants());
          spin.setValueFactory(valueFactory);
          CrudCategorie CC=new CrudCategorie();
               System.err.println( E.getDate_debut().getMonth());

            
            
            ArrayList  arrayList;
        
        try {
            arrayList = (ArrayList) CC.ExtractNom();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);
            catégorie.setItems(observableList);
        // TODO
        } catch (SQLException ex) {
           
        }
        
         extension=new ArrayList<>();
        extension.add("*.jpg");
         extension.add("*.png");
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


    @FXML
    private void ModifierEvenement(ActionEvent event) throws ParseException, SQLException {
        
           CrudEvenement CE=new CrudEvenement();
            CrudCategorie CC=new CrudCategorie();
        String nom=Nom.getText();
        String Des=description.getText();
       
       int Nombre= spin.getValue();
   
       
        
    String a=(String) catégorie.getValue();
   int id_categorie= CC.ExtractId(a);
 
      
        
        String image=nom_image.getText();
       
    
            Boolean etat=E.getEtat();
     
        try {
              if(Nom.getText().isEmpty()||description.getText().isEmpty()||nom_image.getText().isEmpty()){
                        new Alert(Alert.AlertType.ERROR, "attention champs vides").show();
                        
            }
              else{   Evenement E1=new  Evenement(nom, Des, Nombre,false, image,id_categorie);
        System.out.println(E.toString()); 
                  CE.Update1(E1,E.getId());
              new Alert(Alert.AlertType.INFORMATION, "Evenement Modifier").show();}
        } catch (SQLException ex) {
        }
        
      
       
                
    }

    @FXML
    private void upload(ActionEvent event) throws ProtocolException, IOException {
           
        FileChooser fc=new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", extension));
        File f=fc.showOpenDialog(null);
        if(f!=null)
        {
        nom_image.setText(f.getName());
        String path="C:\\Users\\iheb ben fraj\\Desktop\\piJava\\pidesktop1.0\\src\\utils\\assets\\";
         FileUploader fu = new FileUploader("http://localhost/fileUpload/");
            //System.out.println(f.g);
            
           String s= f.getAbsoluteFile().toPath().toString();
           
          
   
            System.out.println(s);
       String fileNameInServer = fu.upload(s);
         f.renameTo(new File(path+f.getName()));
    }
        
    }
    
}
