package com.example.sleepwell.controller;

import com.example.sleepwell.MenuBar;
import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.database.UserSession;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SettingsController {
    @FXML
    private JFXTextField firstNameTextField, lastNameTextField, emailTextField, usernameTextField, phoneTextField;
    @FXML
    private Text errorMessage;
    @FXML
    private AnchorPane leftSlider, rightSlider, parentPane;
    @FXML
    private Label menu, menuClose;
    @FXML
    private Circle accountImage, profile, profileClose;
    @FXML
    private Slider brightnessSlider;

    private File selectedImageFile; // Member variable to hold selected file

    SqliteAccountDAO accountDao = new SqliteAccountDAO();
    UserSession session = UserSession.getInstance();

    public void initialize() {
        // Load user data once from the database
        Accounts userAccount = accountDao.getAccount(session.userId());

        // Setting text fields with user data
        firstNameTextField.setText(userAccount.getFirstName());
        lastNameTextField.setText(userAccount.getLastName());
        emailTextField.setText(userAccount.getEmail());
        usernameTextField.setText(userAccount.getUsername());
        phoneTextField.setText(userAccount.getPhone());

        // Setting user image in account and profile icons
        MenuBar.setAvatarImage(userAccount.getImage(), accountImage);
        MenuBar.setAvatarImage(userAccount.getImage(), profile);
        MenuBar.setAvatarImage(userAccount.getImage(), profileClose);

        // Initialise UI components related to menu navigation
        MenuBar.moveSlider(leftSlider, rightSlider, menu, menuClose, profile, profileClose);

        // Initialise brightness settings from session
        double chosenBrightness = UserSession.getBrightness();
        brightnessSlider.setValue(chosenBrightness);
        MenuBar.adjustBrightness(parentPane, chosenBrightness);
    }

    @FXML
    private void onUpdateProfile(ActionEvent event) {
        // Collect data from text fields
        String username = usernameTextField.getText();
        String firstname = firstNameTextField.getText();
        String lastname = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        Accounts userAccount = accountDao.getAccount(session.userId());

        // Validate input before updating profile
        if (username.isEmpty() || email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty()) {
            errorMessage.setText("*There is a missing field!*");
        } else {
            // Update the account in the database if all fields are filled
            Accounts newAccount = new Accounts(username, firstname, lastname, email, phone, userAccount.getPassword(), userAccount.getImage());
            accountDao.updateAccount(userAccount.getId(), newAccount);

            // Check if an image has been selected and copy it
            if (selectedImageFile != null) {
                try {
                    Path destinationPath = Paths.get("src/main/resources/com/example/sleepwell/images/", selectedImageFile.getName());
                    Files.copy(selectedImageFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    accountDao.updateImage(userAccount.getId(), selectedImageFile.getName());
                    errorMessage.setText("Profile and image updated successfully!");
                } catch (IOException e) {
                    errorMessage.setText("Failed to update image.");
                }
            } else {
                errorMessage.setText("Profile updated successfully!");
            }
        }
    }

    // Allow users to select and image when they press on their profile icon
    public void onAccountImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG image", "*.png"),
                new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                new FileChooser.ExtensionFilter("All images", "*.jpg", "*.png")
        );
        selectedImageFile = fileChooser.showOpenDialog(stage);

        // Check if a file was actually selected by the user and set Image
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            accountImage.setFill(new ImagePattern(image));
            System.out.println("Image selected, ready for update.");
        } else {
            System.out.println("No image has been selected.");
        }
    }

    public void onChangeBrightness(ActionEvent event) {
        double chosenBrightness = brightnessSlider.getValue();
        MenuBar.adjustBrightness(parentPane, chosenBrightness);
        UserSession.setBrightness(chosenBrightness);
    }

    //Redirect user to login page
    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
        UserSession.setBrightness(0.0); // Reset back to default brightness
    }

    public void onHome(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "hello-view.fxml", 600, 400);
    }
}
