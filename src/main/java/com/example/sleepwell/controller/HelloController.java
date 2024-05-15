package com.example.sleepwell.controller;

import com.example.sleepwell.MenuBar;
import com.example.sleepwell.database.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label Menu, MenuClose;

    @FXML
    private ImageView Profile, ProfileClose;

    @FXML
    private AnchorPane LeftSlider, RightSlider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuBar.moveSlider(LeftSlider, RightSlider, Menu, MenuClose, Profile, ProfileClose);
    }

    //Redirect user to login page
    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
    }

    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }
    public void onTimer(ActionEvent event) throws IOException{
        MenuBar.changeScene(event, "timer.fxml", 600, 400);
    }
    public void onStatistics(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "Statistics.fxml", 600, 400);
    }
}