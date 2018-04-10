/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import com.jfoenix.controls.JFXButton;
import static gui.controller.LoginController.loggduser;
import entites.Commande;
import entites.Produit;
import entites.Lignecommande;
import static gui.controller.LoginController.loggduser;
import static gui.controller.StoreController.p;
import services.CrudCommande;
import services.CrudProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import static javax.management.Query.value;
import javax.swing.JOptionPane;
import services.CrudLignecommande;
import static gui.controller.MescommandesController.C;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.FontMetrics;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class MacommandeController implements Initializable {

    List<Commande> lst_com;
    CrudCommande serviceCommande = new CrudCommande();
    CrudLignecommande servicelCommande = new CrudLignecommande();
    CrudProduit servP = new CrudProduit();
    Produit p;
    private TextField nbrArtcile;
    @FXML
    private ScrollPane anchorGridPane;
    private final GridPane GP = new GridPane();
    private int j=0;
    private int i=0;
    private int k=0;
    private int l=0;
    private int scroll=0;   
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
    private Label espace;
    @FXML
    private Label passercm;
    @FXML
    private Label lprixtot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
        
        
        

        //init
        ArrayList <Lignecommande> lst_LC = new ArrayList<>();
        ArrayList<Pane> ListePane = new ArrayList<>();
        double lprix=0;
         //setting up the GridPane
        GP.setPadding(new Insets(10,10,10,10));
        GP.setHgap(5);
        GP.setVgap(5);
        
        //select DATA from DB
        CrudLignecommande myTool = new CrudLignecommande();
            lst_LC=myTool.SelectAll(C.getId());
        
        //put DATA
        for(Lignecommande E : lst_LC)
        {   
            lprix=lprix+E.getQte()*servP.findById(E.getId_produit()).getPrix_nouv();
            //image set
            ImageView IV= new ImageView();
            Image I = new Image("/utils/assets/"+servP.findById(E.getId_produit()).getNom_image());
            IV.setImage(I);
            IV.setFitWidth(230);
            IV.setFitHeight(177);
            IV.setLayoutX(6);;
            
            //pane set
            Pane P = new Pane();
            P.setId(Integer.toString(E.getId()));
            P.setPrefWidth(246);
            P.setPrefHeight(277);
            P.setStyle("-fx-background-color: #4f86dd");
            //label set 
            Label L= new Label(servP.findById(E.getId_produit()).getLabel());
            System.out.println(servP.findById(E.getId_produit()).getLabel());
              L.setLayoutY(185);
              L.setLayoutX(70);
              L.setStyle("-fx-font-size:20");
              //L.setStyle("-fx-font-weight:bold");
              //L.setStyle("-fx-text-fill: black");
              L.setPrefHeight(25);
              L.setPrefWidth(246);
             Label L2= new Label(Integer.toString(E.getQte()));
              L2.setLayoutY(185);
              L2.setLayoutX(50);
              L2.setStyle("-fx-font-size:20");
              //L.setStyle("-fx-font-weight:bold");
              //L.setStyle("-fx-text-fill: black");
              L2.setPrefHeight(25);
              L2.setPrefWidth(246);
                          
           
                   
            P.getChildren().addAll(IV,L,L2);            
            ListePane.add(P);
            GP.add(P,j,i);            

            j++;
            if(j>2)
            {i++;
            j=0;}
           
           }
        
          anchorGridPane.setContent(GP);
          lprixtot.setText("Prix total: "+lprix+"DNT");
         
      
              

 
   
    
    }
    

    private void Tostore(ActionEvent event) throws ParseException, SQLException {
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/store.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }
    @FXML
    private void Menu(MouseEvent event) {
        MenuController menu = new MenuController();
        menu.GestionMenu(event);   
    }
    @FXML
    private void genererrecu(MouseEvent event) {
         PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }
   
    @FXML
    private void Mescommandes(MouseEvent event) {
         try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/Mescommandes.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
               
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }
    public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
  public  double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
  public double toPPI(double inch) {            
	        return inch * 72d;            
}
    
    public class BillPrintable implements Printable {
    
   
    
    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      ArrayList <Lignecommande> lst_LC = new ArrayList<>();
      CrudLignecommande myTool = new CrudLignecommande();
            lst_LC=myTool.SelectAll(C.getId());
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

        //    int idPosition=0;
        //    int productPosition=idPosition + idLength + 2;
        //    int pricePosition=productPosition + prodLength +10;
        //    int qtyPosition=pricePosition + priceLength + 2;
        //    int amtPosition=qtyPosition + qtyLength + 2;
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
            ///////////////// Product names Get ///////////
                
            ///////////////// Product names Get ///////////
                
            
            ///////////////// Product price Get ///////////
                
                double lprix=0;
            ///////////////// Product price Get ///////////
                
             g2d.setFont(new Font("Zanimaux",Font.PLAIN,9));
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("      Recu du commande               ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
      
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" nom produits                 prix_t ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
            for(Lignecommande E : lst_LC)
        {
            lprix=lprix+E.getQte()*servP.findById(E.getId_produit()).getPrix_nouv();
            g2d.drawString(" "+servP.findById(E.getId_produit()).getLabel()+"                  "+E.getQte()*servP.findById(E.getId_produit()).getPrix_nouv()+"  ",10,y);y+=yShift;}
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total     : "+lprix+"               ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("    MERCI D'AVOIR ACHETER            ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
                   
           
             
           
            
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
  
   }

}

