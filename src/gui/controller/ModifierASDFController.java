/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import static gui.controller.AnimalsdfAdminController.P;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudAnimalsdf;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierASDFController implements Initializable,MapComponentInitializedListener {

    @FXML
    private TextField idc;
    @FXML
    private ComboBox<String> idca;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private TextField img;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker date;
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap Map;

    private GeocodingService geocodingService;
    private StringProperty address = new SimpleStringProperty();
    @FXML
    private HBox ev;
    @FXML
    private Button btn1;

    CrudAnimalsdf myTool = new CrudAnimalsdf();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ArrayList  arrayList;
         try {
            arrayList = (ArrayList) myTool.ExtractNom();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);
            idca.setItems(observableList);
        // TODO
        } catch (SQLException ex) {
        }
         String a=(String) idca.getValue();
        idca.setValue(a);
        idc.setText(String.valueOf(AnimalsdfAdminController.P.getId_client()));
        Date date1 = AnimalsdfAdminController.P.getDate_trouvaille();
        LocalDate date2 = date1.toLocalDate();
        date.setValue(date2);
        lieu.setText(AnimalsdfAdminController.P.getLieu_trouvaille());
        sexe.getItems().addAll("male","femelle");
        sexe.setValue(AnimalsdfAdminController.P.getSexe());
        img.setText(AnimalsdfAdminController.P.getNom_image());
       
    }    

    @FXML
    private void Modif(ActionEvent event) throws SQLException, IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage=new Stage();
               CrudAnimalsdf myTool = new CrudAnimalsdf();
               
               if ((idca.getValue().equals("")) && (idc.getText().equals("")) && (date.getValue().toString().compareTo("") == 0) && (lieu.getText().compareTo("") == 0)&& (img.getText().compareTo("") == 0)) {
            JOptionPane.showMessageDialog(null,"un ou plusieurs champs sont vides");

            } 
                  else {
            String a=(String) idca.getValue();          
        int id= this.myTool.ExtractId(a);
        P.setId_categorie(id);
         int id2 = Integer.parseInt(idc.getText());
        P.setId_client(id2);
        LocalDate daten = LocalDate.now();
        LocalDate date1 = this.date.getValue();
        if (date1.isAfter(daten)) {
            JOptionPane.showMessageDialog(null, "date superieur a la date d'aujourd'hui");
        }
        else {
        Date date2 = Date.valueOf(date1);
        P.setDate_trouvaille(date2);}
        P.setLieu_trouvaille(lieu.getText());
        P.setSexe(sexe.getValue());
        P.setNom_image(img.getText());
      
        myTool.Update(P);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Modification rÃ©ussite !");
           alert1.showAndWait();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/AnimalsdfAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                }
                      
                  }
        else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AnimalsdfAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }
        }

    @FXML
    private void ShowPlace(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
           
			LatLong latLong = null;

			if (status == GeocoderStatus.ZERO_RESULTS) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
				alert.show();
				return;
			} else if (results.length > 1) {
				Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
				alert.show();
				latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
			} else {
				latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
			}

			Map.setCenter(latLong);
                         MarkerOptions markerOptions = new MarkerOptions();

                         markerOptions.position( latLong )
                .visible(Boolean.TRUE)
                .title("My Marker");

                         Marker marker = new Marker( markerOptions );

                         Map.addMarker(marker);
                       

		});
    }

    @Override
    public void mapInitialized() {
geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(36.898944179565284, 10.18963326511232))
				.mapType(MapTypeIdEnum.ROADMAP)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(true)
				.zoom(16);
                
                

		Map = mapView.createMap(mapOptions);      }

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
        try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

    @FXML
    private void ClickVeterinaire(MouseEvent event) {
    }

    @FXML
    private void ClickProduit(MouseEvent event) {
    }

    @FXML
    private void SelectFile(ActionEvent event) {
         FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        if (file != null)
        {
            img.setText(file.getName());
            Image Image = new Image(file.toURI().toString(),100,150,true,true);
                   
                    
        }
    }
    }

    
    

