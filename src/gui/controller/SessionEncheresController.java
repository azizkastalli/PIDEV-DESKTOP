/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Encheres;
import entites.Journal;
import entites.Participantsencheres;
import entites.Session;
import entites.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.CrudEncheres;
import services.CrudSession;
import services.ServiceJournal;
import services.ServiceParticipantEncheres;

/**
 * FXML Controller class
 *
 * @author azizkastalli
 */
public class SessionEncheresController implements Initializable {
    TextArea text;
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    Encheres E = new Encheres();
    Journal J = new Journal();
    
    @FXML
    private VBox parent;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;
    @FXML
    private Label veterinaire;
    @FXML
    private Label evenements;
    @FXML
    private Label espace;
    @FXML
    private TextArea textarea;
    @FXML
    private TextField textfield;
    @FXML
    private Button send;
    @FXML
    private Pane countDown;
    
    private ServiceJournal journal = new ServiceJournal();
    private CrudSession session = new CrudSession();
    private static Timer timer = new Timer();

    public SessionEncheresController()
    {}

    public void Init(int id_encheres) {
      CrudEncheres CE = new CrudEncheres();
      E.setId_encheres(id_encheres);
      System.out.println(E.getId_encheres());
        try {
            E=CE.Select(E);
        } catch (SQLException ex) {
            Logger.getLogger(DetailEncheresController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.connectAction();
       
        ServiceJournal serviceJournal = new ServiceJournal();
        CrudSession serviceSession = new CrudSession();
        Session session = new Session();
        Journal journal = new Journal();
        //session.setId(E.getId_encheres());
        User user = new User();
        session.setId(152);
        user.setId(1);
       
        
         //verifier si le client participe ou non à cette session 
        ServiceParticipantEncheres serviceParticipants = new ServiceParticipantEncheres();        
        try {
            if(!serviceParticipants.verifierExistanceSession(user, session))
            {
                textfield.setDisable(true);
                send.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionEncheresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       //verifier si quelqu'un dans cette session à deja fait une mise si oui init countdown
        //----------------------------------------------------------------------------------
        try {
            if(serviceJournal.ExistJournal(session))
            {//init countdown with last journal time update
               journal=serviceJournal.Select(session);
               CountDownController countdown = new CountDownController();
        
                 //current date
         Date date = new Date();
         Date date2 = new Date();
         date2.setHours(journal.getDate_mise().getHours());
         date2.setMinutes(journal.getDate_mise().getMinutes());
         date2.setSeconds(journal.getDate_mise().getSeconds());
         
         Timestamp currTime = new Timestamp(date.getTime());
         long diff = (long) (120000 - ( currTime.getTime() - date2.getTime()));
            System.out.println("c :" + diff);   
               
            countdown.initForSessionVerification(journal.getDate_mise());
               VBox vb = countdown.setguiForSession();
               countDown.getChildren().add(vb);
               
               timer.schedule(new TimerTask() {
                  @Override
                    public void run() {
                      try {
                          //close the deal
                          serviceSession.FinishSession(session);
                          textfield.setDisable(true);
                          send.setVisible(false);
                      } catch (SQLException ex) {
                          Logger.getLogger(SessionEncheresController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      System.out.println("wfet sayé ");
                    }
                    }, diff);
                 }
         //-----------------------------------------------------------------------------------
                
                send.setOnAction((event) -> {
                          
              try{
                    double mise =Double.parseDouble(textfield.getText());
                    if(serviceSession.VerifierMise(session,mise))
                    {
                    
                    CountDownController countdown = new CountDownController();
                    countdown.initForSession(0.034);
                    VBox vb = countdown.setguiForSession();
                    countDown.getChildren().add(vb);
                       
                    String message = textfield.getText();
                    // if pour verifier la mise (controle de saisie)
                    this.sendAction(message);
                    
                    Date d = new Date();
                    Time t = new Time(d.getTime());
                    
                    J.setDate_mise(t);
                    J.setId_client("1");
                    System.out.println(E.getId_encheres());
                    J.setId_session(Integer.toString(E.getId_encheres()));
                    J.setMise(Double.parseDouble(message));
                    
                try {
                    serviceJournal.Create(J);
                } catch (SQLException ex) {
                    Logger.getLogger(SessionEncheresController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                  else
                    {
                    //alert 
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                               alert.setTitle("notification"); 
                               alert.setHeaderText("erreur");
                               alert.setContentText("entrer une mise superiure ");
                               alert.showAndWait();  
                    }
               }
                       catch(Exception e)
                        {
                      //alert 
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                               alert.setTitle("notification"); 
                               alert.setHeaderText("erreur");
                               alert.setContentText("entrer un entier ou un reel");
                               alert.showAndWait(); 
                        }    
                });
        } catch (SQLException ex) {
            Logger.getLogger(SessionEncheresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
        textarea.textProperty().addListener(
            (ObservableValue<? extends String> obs, String old, String niu) -> {
            Platform.runLater(() -> {
                CountDownController countdown = new CountDownController();
                countdown.initForSession(0.034);
                VBox vb1 = countdown.setguiForSession();
                countDown.getChildren().remove(0);
                countDown.getChildren().add(vb1);
              
                timer.cancel();
                timer.purge();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                  @Override
                    public void run() {
                        //close the deal 
                         try {
                          //close the deal
                          serviceSession.FinishSession(session);
                          send.setVisible(false);
                      } catch (SQLException ex) {
                          Logger.getLogger(SessionEncheresController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      System.out.println("wfet sayé ");
                    }
                    }, 120000);
               
            }
          );
        });
        
    }    


    @FXML
    private void Menu(MouseEvent event) {
          MenuController menu = new MenuController();
        menu.GestionMenu(event);   
    }
    
    
    
    
    

    //--------------------------//
    
    private void ListenThread(TextArea text) 
    {
         Thread IncomingReader = new Thread(new IncomingReader(text));
         IncomingReader.start();
         
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
        System.out.println(data + " is now offline "); 
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            System.out.println("Could not send Disconnect message ");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            System.out.println("Disconnected. ");
            sock.close();
        } catch(IOException ex) {
            System.out.println("Failed to disconnect. ");
        }
        isConnected = false;
        //tf_username.setEditable(true);
    }
    
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    { TextArea text;
      
    
        private IncomingReader(TextArea text) {
                        this.text=text;
        }
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     { // ici tu peux creer une pane , la charger et la mettre dans une autre pane au lieu
                        //de textarea !!
                         text.setText(text.getText() + "\n" +data[0] + ": " + data[1]);
                         
                     } 
                     else if (data[2].equals(connect))
                     {
                       // ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(IOException ex) { }
        }
    }

    //--------------------------//                   


    public void connectAction() {                                          
        if (isConnected == false) 
        {Scanner sc = new Scanner(System.in);
            System.out.println("username : ");
            username=sc.nextLine();
            try 
            {
                sock = new Socket(address, port);
              
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
               
                isConnected = true; 
            } 
            catch (IOException ex) 
            {
                System.out.println("Cannot Connect! Try Again ");
            }
            
            ListenThread(textarea);
            
        } else if (isConnected == true) 
        {
                            System.out.println("You are already connected. ");
        }
    }                                         

    private void disconnectAction() {                                             
        sendDisconnect();
        Disconnect();
    }                                            

    private void anonymousConnection() {                                            
        if (isConnected == false) 
        {
            String anon="anon";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                
                writer.println(anon + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                System.out.println("Cannot Connect! Try Again. ");
                
            }
            
           // ListenThread();
            
        } else if (isConnected == true) 
        {
                System.out.println("You are already connected. ");
        }        
    }                                           

    public void sendAction(String message) {                                       

     if(true) {
            try {
               writer.println(username + ":" + message + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                System.out.println("Message was not sent. ");
            }
            
        }

       
    }
    
}
