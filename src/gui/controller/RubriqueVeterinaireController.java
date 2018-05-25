/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Rdv_Veterinaire;
import entites.User;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeArray;
import services.CrudRdv_Vet;
import services.CrudUser;
import utils.Dbconnection;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class RubriqueVeterinaireController implements Initializable {

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
    private Label evenements;
    @FXML
    private FontAwesomeIconView addstoreicon;
    @FXML
    private FontAwesomeIconView addenchersicon;
    @FXML
    private FontAwesomeIconView gererproduiticon;
    @FXML
    private FontAwesomeIconView gererenchersicon;
    @FXML
    private ImageView addstore;
    @FXML
    private ImageView gererproduit;
    @FXML
    private Label espace;
    @FXML
    private TableView<User> tbl_vet;
    @FXML
    private TableColumn<User, String> col_id;
    @FXML
    private TableColumn<User, String> col_nom;
    @FXML
    private TableColumn<User, String> col_prenom;
    @FXML
    private TableColumn<User, String> col_tel;
    @FXML
    private Text txt_nom;
    @FXML
    private Text txt_prenom;
    @FXML
    private Text txt_mail;
    @FXML
    private Text txt_tel;
    @FXML
    private Text txt_adress;
    @FXML
    private Text txt_prix;
    @FXML
    private Button btn_reserv;
    @FXML
    private HBox hb_msg;
    @FXML
    private Text txt_msg;
    
    @FXML
    private VBox vb_rdv;
    @FXML
    private Text txt_totale;
    @FXML
    private DatePicker inpt_date;
    @FXML
    private TextField inpt_duree;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_cancel;
    @FXML
    private Text txt_frm_err;
    @FXML
    private VBox vb_info;
    @FXML
    private TextField edt_search;
    @FXML
    private Button btn_consult_rdv;
    @FXML
    private HBox hb_vet_cont;
    @FXML
    private HBox hb_rdv_cont;
    @FXML
    private Button btn_back;
    
    @FXML
    private TableView<Rdv_Veterinaire> tbl_rdv;
    @FXML
    private TableColumn<Rdv_Veterinaire, String> col_rdv_vet;
    @FXML
    private TableColumn<Rdv_Veterinaire, String> col_rdv_date;
    @FXML
    private TableColumn<Rdv_Veterinaire, String> col_rdv_prix;
    @FXML
    private Text txt_rdv_vet;
    @FXML
    private Text txt_rdv_date;
    @FXML
    private Text txt_rdv_duree;
    @FXML
    private Text txt_rdv_prix;
    @FXML
    private Text txt_rdv_etat;
    
    @FXML
    private Button btn_del_rdv;
    @FXML
    private Button btn_edt_rdv;
    @FXML
    private HBox hb_msg_rdv;
    @FXML
    private Text txt_msg_rdv;
    
    @FXML
    private VBox vb_rdv_edt;
    @FXML
    private Text txt_totale_edt;
    @FXML
    private DatePicker inpt_date_edt;
    @FXML
    private TextField inpt_duree_edt;
    @FXML
    private Button btn_ok_edt;
    @FXML
    private Button btn_cancel_edt;
    @FXML
    private Text txt_frm_err_edt;
    @FXML
    private VBox vb_info_rdv;
    
    
    private List<User> lst_vet;
    private List<User> lst_vet_search;
    
    private List<Rdv_Veterinaire> lst_rdv;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //INIT BTN MES RENDEZ VOUS
        btn_consult_rdv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initRdv();
                Animation.CloseUp(hb_vet_cont, 529 );
                Animation.OpenUp(hb_rdv_cont, 520 , 0);
                
            }
        });
        
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Animation.CloseUp(hb_rdv_cont, 520 );
               Animation.OpenUp(hb_vet_cont, 0 , 529 );
               ClearInfoVet();
            }
        });
        //INIT TABLE VET ET FORM REDV

        CrudUser serv = new CrudUser();
        lst_vet = new ArrayList<>();
        lst_vet_search = new ArrayList<>();
        try {
            lst_vet = serv.SelectAllVet();
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<User, String>("num_tel"));
        
        if(lst_vet.size() > 0){
            tbl_vet.setItems(FXCollections.observableArrayList(lst_vet));
            
            edt_search.textProperty().addListener((observable, oldValue, newValue) -> {
                SearchVet(newValue.toUpperCase());
            });
            
        }
        
        //INIT BOX RDV MSG
        vb_rdv.setScaleY(0);
        hb_msg.setScaleY(0);
        vb_info.setTranslateX(538);
        
        tbl_vet.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("id : "+tbl_vet.getSelectionModel().getSelectedItem().getId());
                txt_nom.setText(tbl_vet.getSelectionModel().getSelectedItem().getNom());
                txt_prenom.setText(tbl_vet.getSelectionModel().getSelectedItem().getPrenom());
                txt_mail.setText(tbl_vet.getSelectionModel().getSelectedItem().getEmail());
                txt_adress.setText(tbl_vet.getSelectionModel().getSelectedItem().getAddress());
                txt_tel.setText(tbl_vet.getSelectionModel().getSelectedItem().getNum_tel());
                txt_prix.setText(String.valueOf(tbl_vet.getSelectionModel().getSelectedItem().getPrix_unitaire()));
                Animation.translateBoxInfo(vb_info);
                User vet = tbl_vet.getSelectionModel().getSelectedItem();
                btn_reserv.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Animation.scaleY(vb_rdv);
                        Animation.closeY(btn_reserv);
                        ClearInfoVet();
                        btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Animation.closeY(vb_rdv);
                                Animation.scaleY(btn_reserv);
                                ClearFormRdv();
                            }
                        });
                        
                        btn_ok.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                
                            if( checkForm() ){
                                CrudRdv_Vet serv_rdv = new CrudRdv_Vet(Dbconnection.getInstance().getConnection());
                                LocalDate localDate = inpt_date.getValue();
                                Instant instant = Instant.from( localDate.atStartOfDay(ZoneId.systemDefault()));
                                Date date = Date.from(instant);
                                int somme = Integer.valueOf(inpt_duree.getText()) * vet.getPrix_unitaire();
                                Rdv_Veterinaire e = new Rdv_Veterinaire(LoginController.loggduser.getId() , vet.getId(), date,Integer.valueOf(inpt_duree.getText()) , somme, 0);
                                try {
                                    serv_rdv.Create(e);
                                    ClearFormRdv();
                                    Animation.scaleY(hb_msg);
                                    Animation.closeY(vb_rdv);
                                    Animation.scale(btn_reserv);
                                    sendMail("Rendez vous " , "Votre Demande Rendez vous pour le "+String.valueOf(e.getDate_rdv())+" a été envoyé avec succes . " , LoginController.loggduser.getEmail());
                                } catch (SQLException ex) {
                                    Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                
                            }
                                
                            }
                        });
                        
                        inpt_duree.textProperty().addListener((observable, oldValue, newValue) -> {
                            
                        if (!newValue.matches("\\d*")) {
                           inpt_duree.setText(newValue.replaceAll("[^\\d]", ""));                       
                        }                       
                        if(newValue.length() > 0 && newValue.matches("\\d*") ){
                        int nbr = Integer.valueOf(newValue);
                        int somme = nbr * vet.getPrix_unitaire();
                        txt_totale.setText(" "+String.valueOf(somme)+"  DNT");
                        }else{
                        txt_totale.setText("    0  DNT");    
                        }
                        
                        });
                        
                        
                        
                        
                    }
                });
           
                
            }
        });
    }    

    @FXML
    private void Menu(MouseEvent event) {
        
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
        
              
    }
    
    private void ClearFormRdv(){
        inpt_date.getEditor().clear();
        inpt_duree.clear();
        txt_totale.setText("    0 DNT");
    }
    
    private void ClearFormRdvEdt(){
        inpt_date_edt.getEditor().clear();
        inpt_duree_edt.clear();
        txt_totale_edt.setText("    0 DNT");
        
        Dbconnection cnx = Dbconnection.getInstance();
        CrudRdv_Vet serv = new CrudRdv_Vet(cnx.getConnection());
        lst_rdv = new ArrayList<>();

        try {
            lst_rdv = serv.SelectAllByClient(LoginController.loggduser.getId());
            tbl_rdv.getItems().clear();    
            tbl_rdv.setItems(FXCollections.observableArrayList(lst_rdv));
            
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void ClearInfoVet(){
        
        txt_nom.setText("");
        txt_prenom.setText("");
        txt_mail.setText("");
        txt_tel.setText("");
        txt_adress.setText("");
        txt_prix.setText("");
        Animation.closeY(hb_msg);
              
    }
    
    private void ClearInfoRdv(){
        
        txt_rdv_vet.setText("");
        txt_rdv_date.setText("");
        txt_rdv_duree.setText("");
        txt_rdv_prix.setText("");
        txt_rdv_etat.setText("");
        btn_del_rdv.setScaleY(0);
        btn_edt_rdv.setScaleY(0);
        Animation.closeY(hb_msg_rdv);
        
              
    }
    
    private boolean checkForm(){
        
        if(inpt_date.getValue() == null){
            txt_frm_err.setText("! Vous devez remplir date ");
            return false ;
        }
        
        if(inpt_duree.getText().length() == 0){
            txt_frm_err.setText("! Vous devez remplir duree ");
            return false;
        }
        
        return true;
    }
    
    private boolean checkFormEdt(){
        
        if(inpt_date_edt.getValue() == null){
            txt_frm_err.setText("! Vous devez remplir date ");
            return false ;
        }
        
        if(inpt_duree_edt.getText().length() == 0){
            txt_frm_err.setText("! Vous devez remplir duree ");
            return false;
        }
        
        return true;
    }
    
    
    private void SearchVet(String s ){
     
     lst_vet_search = new ArrayList<>();
     for (User u : lst_vet) {
      if(String.valueOf(u.getId()).toUpperCase().contains(s) || u.getNom().toUpperCase().contains(s) || u.getPrenom().toUpperCase().contains(s) || u.getEmail().toUpperCase().contains(s) || u.getNum_tel().toUpperCase().contains(s)|| u.getAddress().toUpperCase().contains(s)){
             lst_vet_search.add(u);
       } 
     }
     tbl_vet.setItems(FXCollections.observableArrayList(lst_vet_search));
    
    }
    
    private void initRdv(){
        
        hb_msg_rdv.setScaleY(0);
        vb_rdv_edt.setScaleY(0);
        btn_del_rdv.setScaleY(0);
        btn_edt_rdv.setScaleY(0);
        //INIT TABLE VET ET FORM REDV
        Dbconnection cnx = Dbconnection.getInstance();
        CrudRdv_Vet serv = new CrudRdv_Vet(cnx.getConnection());
        lst_rdv = new ArrayList<>();

        try {
            lst_rdv = serv.SelectAllByClient(LoginController.loggduser.getId());
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_rdv_vet.setCellValueFactory(new PropertyValueFactory<Rdv_Veterinaire, String>("id_vet"));
        col_rdv_date.setCellValueFactory(new PropertyValueFactory<Rdv_Veterinaire, String>("date_rdv"));
        col_rdv_prix.setCellValueFactory(new PropertyValueFactory<Rdv_Veterinaire, String>("prix"));
        
        if(lst_rdv.size() > 0){
         tbl_rdv.setItems(FXCollections.observableArrayList(lst_rdv));
          tbl_rdv.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vb_rdv_edt.setScaleY(0);
                txt_rdv_vet.setText("");
                txt_rdv_date.setText(String.valueOf(tbl_rdv.getSelectionModel().getSelectedItem().getDate_rdv()));
                txt_rdv_duree.setText(String.valueOf(tbl_rdv.getSelectionModel().getSelectedItem().getDuree_seance()));
                txt_rdv_prix.setText(String.valueOf(tbl_rdv.getSelectionModel().getSelectedItem().getPrix()));
                if(tbl_rdv.getSelectionModel().getSelectedItem().isEtat() == 0 ){
                txt_rdv_etat.setText("En Attente");
                btn_del_rdv.setScaleY(1);
                btn_edt_rdv.setScaleY(1);
                }else if(tbl_rdv.getSelectionModel().getSelectedItem().isEtat() == 1 ){
                txt_rdv_etat.setText("Accepté");
                btn_del_rdv.setScaleY(1);
                btn_edt_rdv.setScaleY(0);
                } else if(tbl_rdv.getSelectionModel().getSelectedItem().isEtat() == 2 ){
                txt_rdv_etat.setText("Refuser");
                btn_del_rdv.setScaleY(0);
                btn_edt_rdv.setScaleY(0);     
                
                }
                Rdv_Veterinaire r = tbl_rdv.getSelectionModel().getSelectedItem();
                btn_edt_rdv.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Animation.scale(vb_rdv_edt);
                        btn_del_rdv.setScaleY(0);
                        btn_edt_rdv.setScaleY(0);
                        
                        ///set rdv values
                    
                        inpt_date_edt.setValue(LocalDate.of(r.getDate_rdv().getYear(), r.getDate_rdv().getMonth(), r.getDate_rdv().getDay()));
                        inpt_duree_edt.setText(String.valueOf(r.getDuree_seance()));
                        txt_totale_edt.setText("  "+String.valueOf(r.getPrix())+" DNT");
                    }
                });
                inpt_duree_edt.textProperty().addListener((observable, oldValue, newValue) -> {
                            
                        if (!newValue.matches("\\d*")) {
                           inpt_duree_edt.setText(newValue.replaceAll("[^\\d]", ""));                       
                        }                       
                        if(newValue.length() > 0 && newValue.matches("\\d*") ){
                        int nbr = Integer.valueOf(newValue);
                        int somme = nbr * 21 ;
                        txt_totale_edt.setText(" "+String.valueOf(somme)+"  DNT");
                        }else{
                        txt_totale_edt.setText("    0  DNT");    
                        }
                        
                        });
                btn_ok_edt.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                
                            if( checkFormEdt() ){
                                System.out.println("in if ok ");
                                CrudRdv_Vet serv_rdv = new CrudRdv_Vet(Dbconnection.getInstance().getConnection());
                                LocalDate localDate = inpt_date_edt.getValue();
                                Instant instant = Instant.from( localDate.atStartOfDay(ZoneId.systemDefault()));
                                Date date = Date.from(instant);
                                int somme = Integer.valueOf(inpt_duree_edt.getText()) * 21 ;
                                Rdv_Veterinaire e = new Rdv_Veterinaire(r.getId() , LoginController.loggduser.getId() , r.getId_vet(), date,Integer.valueOf(inpt_duree_edt.getText()) , somme, r.isEtat());
                                try {
                                    serv_rdv.Update(e);
                                    ClearFormRdvEdt();
                                    Animation.scaleY(hb_msg_rdv);
                                    Animation.closeY(vb_rdv_edt);
                                    Animation.scale(btn_del_rdv);
                                    Animation.scale(btn_edt_rdv);
                                } catch (SQLException ex) {
                                    Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                
                            }
                                
                            }
                        });
                
                
                btn_del_rdv.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                               
                                CrudRdv_Vet serv_rdv = new CrudRdv_Vet(Dbconnection.getInstance().getConnection());
                                
                                try {
                                    serv_rdv.Delete(r);
                                    ClearFormRdvEdt();
                                    Animation.scaleY(hb_msg_rdv);
                                    Animation.closeY(vb_rdv_edt);
                                    Animation.scale(btn_del_rdv);
                                    Animation.scale(btn_edt_rdv);
                                    ClearInfoRdv();
                                    Animation.translateBoxInfo(vb_info_rdv);
                                    sendMail("Rendez vous " , "Votre Rendez vous pour le "+String.valueOf(r.getDate_rdv())+" a été annuler . ", LoginController.loggduser.getEmail() );
                                } catch (SQLException ex) {
                                    Logger.getLogger(RubriqueVeterinaireController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                
                            
                                
                            }
                        });
                
                
                btn_cancel_edt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Animation.closeY(vb_rdv_edt);
                        ClearFormRdvEdt();
                    }
                });
            }
            });
            
            
        }
    }
    
    
    
    public void sendMail(String tit , String cont , String mail) {

     
        try{
            
            String host ="smtp.gmail.com" ;
            String user = "mohamedjihed.kammoun@esprit.tn";
            String pass = "furiothunder";
            String to = mail;
            String from = "mohamedjihed.kammoun@esprit.tn";
            String subject = tit;
            String messageText = cont;
            System.out.println(messageText);
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           JOptionPane.showMessageDialog(null, "un mail vous a été envoyé contenant Plus de détail ");
        }catch(Exception ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "un probleme a été survenu lors de l'envoi du mail verifiez vos parametres de securité !");
        }
            
           
        }
    
}
