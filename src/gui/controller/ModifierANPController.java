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
import entites.AnimalPerdu;
import gui.controller.ServiceAdminController;
import static gui.controller.ServiceAdminController.P;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CrudAnimalperdu;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierANPController implements Initializable, MapComponentInitializedListener{

    @FXML
    private TextField ida;
    @FXML
    private TextField lieu;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private DatePicker date;
    
        CrudAnimalperdu myTool = new CrudAnimalperdu();
    @FXML
    private Button ajouter;
    private GoogleMapView mapView;
      private GoogleMap Map;

    private GeocodingService geocodingService;
    private StringProperty address = new SimpleStringProperty();
    @FXML
    private HBox ev;
       
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ida.setText(String.valueOf(ServiceAdminController.P.getId_animal()));
        Date date1 = ServiceAdminController.P.getDate_disparition();
        LocalDate date2 = date1.toLocalDate();
        date.setValue(date2);
        lieu.setText(ServiceAdminController.P.getLieu_disparition());
        etat.getItems().addAll("perdue","trouve");
        etat.setValue(((ServiceAdminController.P.getEtat1())));
        ida.setEditable(false);
        date.setEditable(false);
        lieu.setEditable(false);
    }    



    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage=new Stage();
               CrudAnimalperdu myTool = new CrudAnimalperdu();
               
               if ((ida.getText().equals("")) && (date.getValue().toString().compareTo("") == 0) && (lieu.getText().compareTo("") == 0)) {
            JOptionPane.showMessageDialog(null,"un ou plusieurs champs sont vides");

            } 
                  else {
                      
     
       //boolean etatt = Boolean.getBoolean(etat.getValue());
       String etatt= etat.getValue();
       switch (etatt)
       {
           case "perdue": 
               P.setEtat(false);
               break ;
           case "trouve":
               P.setEtat(true);
               break;
       }
       
                 
        myTool.Update(P);
        Alert alert1 = new Alert(AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Modification rÃ©ussite !");
           alert1.showAndWait();
      Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                }
                      
                  }
        else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }
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
                
                

		Map = mapView.createMap(mapOptions);    }

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
    
    }
    

