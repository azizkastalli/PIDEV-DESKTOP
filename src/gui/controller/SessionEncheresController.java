/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import entites.Encheres;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    public SessionEncheresController()
    {}

    public void Init(int id_encheres) {
      CrudEncheres CE = new CrudEncheres();
      E.setId_encheres(id_encheres);
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
        
           CountDownController countdown = new CountDownController();
           countdown.initForSession();
           VBox vb = countdown.setguiForSession();   
           countDown.getChildren().add(vb);
           
        textarea.textProperty().addListener(
            (ObservableValue<? extends String> obs, String old, String niu) -> {
            Platform.runLater(() -> {
                countdown.initForSession();
                VBox vb1 = countdown.setguiForSession();
                countDown.getChildren().remove(vb);
                countDown.getChildren().add(vb1);
            }
          );
        });
    }    


    @FXML
    private void Menu(MouseEvent event) {
          MenuController menu = new MenuController();
        menu.GestionMenu(event);   
    }

    @FXML
    private void SendMise(ActionEvent event) {
      String message = textfield.getText();
      this.sendAction(message);
      // if pour verifier la mise (controle de saisie)
      
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
