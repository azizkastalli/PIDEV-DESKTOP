/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author Worm-root
 */
public class Animation {
    
    public static void animFadeTrans(Node n , double width ){
        FadeTransition ft = new FadeTransition(Duration.millis(1000), n);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(800), n);
        translateTransition.setFromX(-width);
        translateTransition.setToX(n.getLayoutX());
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        
        ParallelTransition pl = new ParallelTransition();
        pl.getChildren().addAll(
                ft,
                translateTransition
        );
        pl.setCycleCount(1);
        pl.play();
    }
    
   public static  void scale(Node n ){      
       FadeTransition ft = new FadeTransition(Duration.millis(200), n);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        
       ScaleTransition scaleTransition = new ScaleTransition(); 
        scaleTransition.setDuration(Duration.millis(300)); 
        scaleTransition.setNode(n);
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setByY(1); 
        scaleTransition.setByX(1); 
        scaleTransition.setCycleCount(1); 
        scaleTransition.setAutoReverse(false);
        
        ParallelTransition pl = new ParallelTransition();
        pl.getChildren().addAll(
                ft,
                scaleTransition
        );
        pl.setCycleCount(1);
        pl.play();
        
       
   }
   
   public static void closeScale(Node n){
       ScaleTransition scaleTransition = new ScaleTransition(); 
        scaleTransition.setDuration(Duration.millis(500)); 
        scaleTransition.setNode(n);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setByY(0); 
        scaleTransition.setByX(0); 
        scaleTransition.setCycleCount(1); 
        scaleTransition.setAutoReverse(false);
        scaleTransition.play(); 
   }
   
   public static  void scaleY(Node n ){      
       FadeTransition ft = new FadeTransition(Duration.millis(600), n);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        
       ScaleTransition scaleTransition = new ScaleTransition(); 
        scaleTransition.setDuration(Duration.millis(300)); 
        scaleTransition.setNode(n);
        //scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setByY(1); 
        //scaleTransition.setByX(0); 
        scaleTransition.setCycleCount(1); 
        scaleTransition.setAutoReverse(false);
        
        ParallelTransition pl = new ParallelTransition();
        pl.getChildren().addAll(
                ft,
                scaleTransition
        );
        pl.setCycleCount(1);
        pl.play();
        
       
   }
   
   public static  void closeY(Node n ){      
       FadeTransition ft = new FadeTransition(Duration.millis(300), n);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        
       ScaleTransition scaleTransition = new ScaleTransition(); 
        scaleTransition.setDuration(Duration.millis(300)); 
        scaleTransition.setNode(n);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setByY(0); 
        scaleTransition.setByX(0); 
        scaleTransition.setCycleCount(1); 
        scaleTransition.setAutoReverse(false);
        
        ParallelTransition pl = new ParallelTransition();
        pl.getChildren().addAll(
                ft,
                scaleTransition
        );
        pl.setCycleCount(1);
        pl.play();
        
       
   }
   
   public static void FadeTransFast(Node n , double width ){
        FadeTransition ft = new FadeTransition(Duration.millis(1500), n);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(800), n);
        translateTransition.setFromX(-width);
        translateTransition.setToX(n.getLayoutX());
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        
        ParallelTransition pl = new ParallelTransition();
        pl.getChildren().addAll(
                ft,
                translateTransition
        );
        pl.setCycleCount(1);
        pl.play();
    }
   
   public static void OpenUp(Node n , double h , double f ){
       
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), n);
        translateTransition.setFromY(f);
        translateTransition.setToY(-h);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }
   
   public static void CloseUp(Node n , double h ){
       
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), n);
        translateTransition.setFromY(0);
        translateTransition.setToY(h);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }
   
   
   public static void translate(Node n){
       
       System.out.println("Y::"+n.layoutYProperty().getValue());
       TranslateTransition translateTransition = new TranslateTransition(Duration.millis(800), n);
        translateTransition.setFromY(n.layoutYProperty().getValue());
        translateTransition.setToY(n.layoutYProperty().getValue()-50);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
   }
   
   public static void translateBoxInfo(Node n){
       
       System.out.println("X::"+n.layoutXProperty().getValue());
       TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), n);
        translateTransition.setFromX(n.layoutYProperty().getValue() +538);
        translateTransition.setToX(n.layoutYProperty().getValue());
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
   }
    
}
