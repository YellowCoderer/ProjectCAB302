package com.example.sleepwell;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.sleepwell.database.SqliteAccountDAO;

public class HelloController implements Initializable {
    //implements database
    public HelloController() {
        SqliteAccountDAO accountDao = new SqliteAccountDAO();
    }

    @FXML
    private Label Menu, MenuClose;

    @FXML
    private ImageView Profile, ProfileClose;

    @FXML
    private AnchorPane LeftSlider, RightSlider;

    @FXML
    private Button  StatisticsButton, HomeButton, TimerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the slider's position off-screen
        LeftSlider.setTranslateX(-123);
        RightSlider.setTranslateX(117);

        // Event handler for opening the menu
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), LeftSlider);
            slide.setToX(0);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        // Event handler for closing the menu
        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), LeftSlider);
            slide.setToX(-123);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });


        // Event handler for opening the profile
        Profile.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), RightSlider);
            slide.setToX(0);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Profile.setVisible(false);
                ProfileClose.setVisible(true);
            });
        });

        // Event handler for closing the profile
        ProfileClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), RightSlider);
            slide.setToX(117);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Profile.setVisible(true);
                ProfileClose.setVisible(false);

            });
        });
        StatisticsButton.setOnMouseClicked(event -> {   //close the menu on button click
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), LeftSlider);
            slide.setToX(-123);
            slide.play();
        });
        TimerButton.setOnMouseClicked(event -> {    //close the menu on button click
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), LeftSlider);
            slide.setToX(-123);
            slide.play();
        });
        HomeButton.setOnMouseClicked(event -> {     //close the menu on button click
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), LeftSlider);
            slide.setToX(-123);
            slide.play();
        });
    }
}
