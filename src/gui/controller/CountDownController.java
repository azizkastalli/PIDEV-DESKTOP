/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.fonts.Fonts;
import eu.hansolo.tilesfx.tools.Helper;
import java.util.Date;
import java.sql.Timestamp;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;


public class CountDownController  {
    private static final int            SECONDS_PER_DAY    = 86_400;
    private static final int            SECONDS_PER_HOUR   = 3600;
    private static final int            SECONDS_PER_MINUTE = 60;
    private Tile           flipDays;
    private Tile           flipHours;
    private Tile           flipMinutes;
    private Tile           flipSeconds;
    private Label          daysLabel;
    private Label          hoursLabel;
    private Label          minutesLabel;
    private Label          secondsLabel;
    private Duration       duration;
    private long           lastTimerCall;
    private Tile           minutes;
    private Tile           seconds;
    private AnimationTimer timer;

    public CountDownController() {}

     public void init(Timestamp date_debut) {

        flipDays     = createFlipTile("0", Helper.TIME_59_TO_00);
        flipHours    = createFlipTile("0", Helper.TIME_24_TO_0);
        flipMinutes  = createFlipTile("0", Helper.TIME_59_TO_00);
        flipSeconds  = createFlipTile("0", Helper.TIME_59_TO_00);

        daysLabel    = createLabel("DAYS");
        hoursLabel   = createLabel("HOURS");
        minutesLabel = createLabel("MINUTES");
        secondsLabel = createLabel("SECONDS");
    
        //current date
         Date date = new Date();
         Timestamp ts = new Timestamp(date.getTime());
            
                        long diff = date_debut.getTime() - ts.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
       
        double  heure_converted =1+ diffHours+ (diffDays*24) + (diffMinutes/60) + (diffSeconds/3600) ;       
        			System.out.println("hours converted : "+heure_converted);

        duration = Duration.hours(heure_converted);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                
                if (now > lastTimerCall + 1_000_000_000l) {
                    duration = duration.subtract(Duration.seconds(1));

                    int remainingSeconds = (int) duration.toSeconds();
                    int d = Math.abs(remainingSeconds / SECONDS_PER_DAY);
                    int h = (remainingSeconds % SECONDS_PER_DAY) / SECONDS_PER_HOUR;
                    int m = ((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;
                    int s = (((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) % SECONDS_PER_MINUTE);

                    if (d == 0 && h == 0 && m == 0 && s == 0) { timer.stop(); }

                    flipDays.setFlipText(String.format("%02d", d));
                    flipHours.setFlipText(String.format("%02d", h));
                    flipMinutes.setFlipText(String.format("%02d", m));
                    flipSeconds.setFlipText(String.format("%02d", s));

                    lastTimerCall = now;
                }
            }
        };
    }
     
     public void initForSession() {
      
         minutes  = createTile("MINUTES", "0");
         seconds  = createTile("SECONDS", "0");

        minutesLabel = createLabel("MINUTES");
        secondsLabel = createLabel("SECONDS");
    
        duration = Duration.hours(0.034);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                
                if (now > lastTimerCall + 1_000_000_000l) {
                    duration = duration.subtract(Duration.seconds(1));

                    int remainingSeconds = (int) duration.toSeconds();
                 
                    int m = ((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;
                    int s = (((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) % SECONDS_PER_MINUTE);

                    if (m == 0 && s == 0) { timer.stop(); }

                    minutes.setDescription(String.format("%02d", m));
                    seconds.setDescription(String.format("%02d", s));

                    lastTimerCall = now;
                }
            }
        };
    }

     public VBox setgui() {

        HBox middle = new HBox(daysLabel, hoursLabel, minutesLabel, secondsLabel);
             middle.setPrefWidth(246);
             middle.setPrefHeight(20);
    
        HBox lower = new HBox(12,flipDays, flipHours, flipMinutes, flipSeconds);
             lower.setPrefWidth(246);
             
        VBox pane = new VBox(middle, lower);
             pane.prefHeight(40);
             pane.prefWidth(246);
        
        pane.setBackground(new Background(new BackgroundFill(Tile.BACKGROUND.brighter(), CornerRadii.EMPTY, Insets.EMPTY)));
 
        timer.start();
        
        return pane;
    }
     
     public VBox setguiForSession() {

        HBox middle = new HBox(minutesLabel, secondsLabel);
             middle.setPrefWidth(246);
             middle.setPrefHeight(20);
    
        HBox lower = new HBox(12,minutes, seconds);
             lower.setPrefWidth(246);
             
        VBox pane = new VBox(middle, lower);
             pane.prefHeight(40);
             pane.prefWidth(246);
        
        pane.setBackground(new Background(new BackgroundFill(Tile.BACKGROUND.brighter(), CornerRadii.EMPTY, Insets.EMPTY)));
 
        timer.start();
        
        return pane;
    }

     
    private Tile createFlipTile(final String TEXT, final String... CHARACTERS) {
        return TileBuilder.create().skinType(SkinType.FLIP)
                          .prefSize(20, 20)
                          .flipTimeInMS(10)
                          .flipText(TEXT)
                          .characters(CHARACTERS)
                          .build();
    }
    
     private Tile createTile(final String TITLE, final String TEXT) {
        return TileBuilder.create().skinType(SkinType.CHARACTER)
                          .prefSize(200, 200)
                          .title(TITLE)
                          .titleAlignment(TextAlignment.CENTER)
                          .description(TEXT)
                          .build();
    }
    
    
    private Label createLabel(final String TEXT) {
        final Font  FONT  = Fonts.latoRegular(12);
        final Label LABEL = new Label(TEXT);
        LABEL.setFont(FONT);
        LABEL.setTextFill(Tile.FOREGROUND);
        LABEL.setAlignment(Pos.CENTER);
        LABEL.setPrefSize(60, 20);
        return LABEL;
    }


}