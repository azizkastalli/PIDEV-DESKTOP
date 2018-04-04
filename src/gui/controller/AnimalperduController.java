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
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import entites.AnimalPerdu;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import javax.xml.bind.Marshaller;
import services.CrudAnimalperdu;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AnimalperduController implements Initializable, MapComponentInitializedListener {
    
    static AnimalPerdu p = new AnimalPerdu();
    private GoogleMap Map;

    private GeocodingService geocodingService;
    private StringProperty address = new SimpleStringProperty();
    
  
    

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
    
    @FXML
    private GoogleMapView mapView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
               etat.setEditable(false);
        //mapView = new GoogleMapView();
               mapView.addMapInializedListener(this);
               address.bind(lieu.textProperty());

        
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
       //boolean etatt = Boolean.getBoolean(etat.getText());
        //p.setEtat(etatt);
        myTool.Create(p);
        JOptionPane.showMessageDialog(null, "reclamation ajouté");
        
        
    }

    @FXML
    private void Menu(MouseEvent event) {
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
                
                

		Map = mapView.createMap(mapOptions);
            }

   

   

    @FXML
    private void Showplace(ActionEvent event) {
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

		});
    }
    
    }

    
    

