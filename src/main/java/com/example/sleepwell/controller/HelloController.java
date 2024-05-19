package com.example.sleepwell.controller;

import com.example.sleepwell.initialization.MenuBar;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.initialization.UserPreferences;
import com.example.sleepwell.initialization.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label menu, menuClose;

    @FXML
    private Circle profile, profileClose;

    @FXML
    private AnchorPane leftSlider, rightSlider, parentPane;

    SqliteAccountDAO accountDao = new SqliteAccountDAO();
    UserSession session = UserSession.getInstance();
    private String userImage = accountDao.getAccount(session.getUserId()).getImage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuBar.moveSlider(leftSlider, rightSlider, menu, menuClose, profile, profileClose);

        double chosenbrightness = UserSession.getBrightness();
        UserPreferences.adjustBrightness(parentPane, chosenbrightness);

        UserPreferences.setAvatarImage(userImage, profile);
        UserPreferences.setAvatarImage(userImage, profileClose);
    }

    //Redirect user to login page
    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
        UserSession.setBrightness(0.0); // Reset back to default brightness
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