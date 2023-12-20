package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.controllers.DTO.LoginRequest;
import ba.unsa.etf.rpr.utils.PasswordPattern;
import ba.unsa.etf.rpr.utils.UsernamePattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LoginController {
    public Button loginId;
    public TextField usernameId;
    public PasswordField passwordId;
    public Label invaliduUsernameId;
    public Label invalidPasswordId;
    public ImageView backgroundImage;

    public void loginAction(ActionEvent actionEvent) throws IOException {
        if(usernameId.getText().isEmpty()) invaliduUsernameId.setText("Username is required!");
        if(passwordId.getText().isEmpty()) invalidPasswordId.setText("Password is required!");
    }

    public LoginController() {

    }

    @FXML
    public void initialize()
    {
        usernameId.setFocusTraversable(false);
        passwordId.setFocusTraversable(false);
        usernameId.textProperty().addListener((obs,oldValue,newValue)->{
            if(UsernamePattern.isValid(newValue)){
                invaliduUsernameId.setText("");
            }
            else invaliduUsernameId.setText("Invalid username!");
        });
        passwordId.textProperty().addListener((obs,oldValue,newValue)->{
            if(PasswordPattern.isValid(newValue)){
                invalidPasswordId.setText("");
            }
            else invalidPasswordId.setText("Invalid password!");
        });
    }

}