package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValid;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.type.*;

public class RegisterController {
    private final UserManager userManager = new UserManager();
    public TextField usernameID;
    public Label invalidUsernameID;
    public PasswordField passwordID;
    public Label invalidPasswordID;
    public TextField repeatPasswordID;
    public Label invalidRepeatPasswordID;
    public TextField emailID;
    public Label invalidEmailID;
    public TextField fullNameID;
    public Label invalidFullNameID;
    public TextField dateOfBirthID;
    public Label invalidDateOfBirth;
    public Button registerButton;

    @FXML
    public void initialize() {
        usernameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, username))
                invalidUsernameID.setText("");
            else
                invalidUsernameID.setText("Invalid username!");
        });
        passwordID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, password))
                invalidPasswordID.setText("");
            else
                invalidPasswordID.setText("Invalid password!");
            if(newValue.equals(repeatPasswordID.getText()))
                invalidRepeatPasswordID.setText("");
            else
                invalidRepeatPasswordID.setText("Passwords do not match!");
        });
        repeatPasswordID.textProperty().addListener((obs,oldValue,newValue)->{
            if(newValue.equals(passwordID.getText()))
                invalidRepeatPasswordID.setText("");
            else
                invalidRepeatPasswordID.setText("Passwords do not match!");
        });
        emailID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, email))
                invalidEmailID.setText("");
            else
                invalidEmailID.setText("Invalid email!");
        });
        fullNameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, fullname))
                invalidFullNameID.setText("");
            else
                invalidFullNameID.setText("Invalid full name!");
        });
        dateOfBirthID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, date))
                invalidDateOfBirth.setText("");
            else
                invalidDateOfBirth.setText("Invalid date!");
        });
        registerButton.setOnAction(event -> {
            this.registerClick();
        });
    }
    public void registerClick() {
        if (usernameID.getText().isEmpty()) invalidUsernameID.setText("Username is required!");
        if (passwordID.getText().isEmpty()) invalidPasswordID.setText("Password is required!");
        if (repeatPasswordID.getText().isEmpty()) invalidRepeatPasswordID.setText("Repeated password is required!");
        if (emailID.getText().isEmpty()) invalidEmailID.setText("Email is required!");
        if (fullNameID.getText().isEmpty()) invalidFullNameID.setText("Full name is required!");
        if (dateOfBirthID.getText().isEmpty()) invalidDateOfBirth.setText("Date of birth is required!");

        // if there are no errors, all invalid labels will be empty, and we can add the user
        if (invalidUsernameID.getText().isEmpty() &&
                invalidPasswordID.getText().isEmpty() &&
                invalidRepeatPasswordID.getText().isEmpty() &&
                invalidEmailID.getText().isEmpty() &&
                invalidFullNameID.getText().isEmpty() &&
                invalidDateOfBirth.getText().isEmpty()) {
            System.out.println("Everything is valid, going on Registration");

            User user = makeUser();
            try {
                userManager.add(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private User makeUser() {
        String[] dateValues = dateOfBirthID.getText().split("-");


        int year = Integer.parseInt(dateValues[2]);
        int month = Integer.parseInt(dateValues[1]); // Note: Month in Calendar is 0-based, so January is 0
        int day = Integer.parseInt(dateValues[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Adjust month to be 0-based
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date date = calendar.getTime();

        System.out.println("Date of added user: " + date);
        return new User(usernameID.getText(), passwordID.getText(), emailID.getText(), fullNameID.getText(), date, 2);
    }
}

