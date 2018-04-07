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
import entites.AnimalSdf;
import static gui.controller.LoginController.loggduser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class AnimalsdfController implements Initializable, MapComponentInitializedListener {

    private GoogleMap Map;

    private GeocodingService geocodingService;
    private StringProperty address = new SimpleStringProperty();
    
    @FXML
    private TextField idc;
    @FXML
    private ComboBox<?> idca;
    @FXML
    private ComboBox<String> sexe;
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
    
    @FXML
    private GoogleMapView mapView;
   
    
    CrudAnimalsdf myTool = new CrudAnimalsdf();
    @FXML
    private Button btn1;
        
       
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         mapView.addMapInializedListener(this);
               address.bind(lieu.textProperty());
               ArrayList  arrayList;
        
        try {
            arrayList = (ArrayList) myTool.ExtractNom();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);
            idca.setItems(observableList);
        // TODO
        } catch (SQLException ex) {
        }
        sexe.getItems().addAll("male","femelle");
        idc.setText(String.valueOf(loggduser.getId()));
        idc.setEditable(false);
        
    }    

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
        CrudAnimalsdf myTool = new CrudAnimalsdf();
        AnimalSdf p = new AnimalSdf();
        String a=(String) idca.getValue();
        int id= this.myTool.ExtractId(a);
        //int id = Integer.parseInt(idca.getText());
        p.setId_categorie(id);
        p.setSexe(sexe.getValue());
        p.setNom_image(img.getText());
        LocalDate date1 = this.date.getValue();
        Date date2 = Date.valueOf(date1);
        p.setDate_trouvaille(date2);
        p.setLieu_trouvaille(lieu.getText());
        
        myTool.Create(p);
        JOptionPane.showMessageDialog(null, "declamation ajoutÃ©");
        
    }

    @FXML
    private void Menu(MouseEvent event) {
         String dest=""; 
        String destination=""; 
        String type = event.getSource().getClass().getName();
        
        if(type.equals("javafx.scene.control.Label"))
          {
          Label ev = (Label) event.getSource();
          dest=ev.getId();
          }
         else if(type.equals("javafx.scene.image.ImageView"))
         {
         ImageView ev = (ImageView) event.getSource();
         dest=ev.getId();
         }
        
         switch (dest) {
            case "services":
            
            
                destination="RubriqueServices.fxml";
                break;
            
                
            
         }
         if(destination!="")
        {
 
        
         Parent home_page_parent = null;
            try {
                home_page_parent = FXMLLoader.load(getClass().getResource("/gui/"+destination));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
       
        }
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
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
    private void Selectfile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        if (file != null)
        {
            img.setText(file.getAbsolutePath());
            Image Image = new Image(file.toURI().toString(),100,150,true,true);
                   
                    
        }
    }
    }
    

