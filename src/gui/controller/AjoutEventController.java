/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Categorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.CrudCategorie;
import services.CrudEvenement;
import entites.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import rest.file.uploader.tn.FileUploader;
import static gui.controller.LoginController.loggduser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * FXML Controller class
 *
 * @author iheb bf
 */
public class AjoutEventController implements Initializable {

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
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField Nom;
    @FXML
    private TextField nom_image;
    private TextField nombre;

    @FXML
    private TextField description;
    @FXML
    private Spinner<Integer> spin;
    @FXML
    private ComboBox catégorie;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private JFXTimePicker heure1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final int initialValue = 10;
        SpinnerValueFactory<Integer> valueFactory
                =// 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 50000, initialValue);
        spin.setValueFactory(valueFactory);
        CrudCategorie CC = new CrudCategorie();

        ArrayList arrayList;

        try {
            arrayList = (ArrayList) CC.ExtractNom();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);
            catégorie.setItems(observableList);
            // TODO
        } catch (SQLException ex) {
        }

        extension = new ArrayList<>();
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
    private void AjouterEvenement(ActionEvent event) throws ParseException, IOException, SQLException{

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateD = date_debut.getValue().format(formatter);
        String dateF = date_fin.getValue().format(formatter);
        LocalTime LT = heure.getValue();
        LocalTime LT1 = heure1.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        Date parsedDate1 = null;
        try {
            parsedDate = dateFormat.parse(dateD);

            parsedDate1 = dateFormat.parse(dateF);
        } catch (ParseException ex) {
            Logger.getLogger(GererEnchersController.class.getName()).log(Level.SEVERE, null, ex);
        }

        parsedDate.setMinutes(LT.getMinute());
        parsedDate.setHours(LT.getHour());
        parsedDate.setMinutes(LT1.getMinute());
        parsedDate.setHours(LT1.getHour());
        Timestamp DateDebut = new java.sql.Timestamp(parsedDate.getTime());
        Timestamp DateFin = new java.sql.Timestamp(parsedDate1.getTime());

        System.err.println(DateDebut.getMonth());

        Date date = new Date();
        LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        int hour = localDate.getHour();
        int minute = localDate.getMinute();

        try {
            Timestamp dateSys = new Timestamp(System.currentTimeMillis());
            if (DateDebut.before(dateSys) || DateFin.before(dateSys)) {
                new Alert(Alert.AlertType.ERROR, "date debut et date fin doit etre apres date aujourd'hui").show();

            } else if (!DateDebut.before(DateFin)) {
                new Alert(Alert.AlertType.ERROR, "date debut  doit etre avant date fin").show();}
                else if(Nom.getText().isEmpty()||description.getText().isEmpty()||nom_image.getText().isEmpty()){
                        new Alert(Alert.AlertType.ERROR, "attention champs vides").show();
                        
            }
            else {
                CrudEvenement CE = new CrudEvenement();
                CrudCategorie CC = new CrudCategorie();
                String nom = Nom.getText();
                String Des = description.getText();

                int Nombre = spin.getValue();

                String a = (String) catégorie.getValue();
                int id_categorie = CC.ExtractId(a);
                String image = nom_image.getText();
                int id_association = loggduser.getId();
                Evenement E = new Evenement(nom, Des, Nombre, DateDebut, DateFin, false, image, id_categorie, id_association);

                CE.Create(E);
            }
        } catch (SQLException ex) {
        }

    }

    @FXML
    private void upload(ActionEvent event) throws ProtocolException, IOException {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", extension));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            nom_image.setText(f.getName());
            String path = "C:\\Users\\iheb ben fraj\\Desktop\\piJava\\pidesktop1.0\\src\\utils\\assets\\";
            FileUploader fu = new FileUploader("http://localhost/fileUpload/");
            //System.out.println(f.g);

            String s = f.getAbsoluteFile().toPath().toString();

            System.out.println(s);
            String fileNameInServer = fu.upload(s);
            f.renameTo(new File(path + f.getName()));
        }
    }
}
