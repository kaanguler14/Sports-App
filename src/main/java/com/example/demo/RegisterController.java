package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController {

    @FXML
    private Button closeButton;

    @FXML
    private Button backLoginButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField usernameTextField;


    private Parent root;

    private Stage stage;

    private Scene scene;

    boolean control=false;

    public void setBackLoginButton(ActionEvent event){
        try {
            root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }

    public void registerButtonOnAction(ActionEvent e){


        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            confirmPasswordLabel.setText("");
            registerUser();
        }
        else {
            confirmPasswordLabel.setText("Password Does Not Match");

        }


    }

    public void setCloseButton(ActionEvent e){

        Stage stage=(Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from useraccounts where username='"+usernameTextField.getText()+"' and password='"+setPasswordField.getText()+"';";

        String firstname = firstnameTextField.getText();
        String lastname=lastnameTextField.getText();
        String username=usernameTextField.getText();
        String password=setPasswordField.getText();

        String insertToRegister="";

        if (firstname.isBlank()==false&&lastname.isBlank()==false && username.isBlank()==false && password.isBlank()==false){
            String insertFields="INSERT INTO UserAccounts(Firstname,Lastname,Username,Password) VALUES ('";
            String insertValues=firstname+"','"+lastname+"','"+username+"','"+password+"')";
            insertToRegister=insertFields+insertValues;
            control=true;
        }
        else {
            registrationMessageLabel.setText("Please Check the Fields");
        }


        try {
            Statement statement = connectDB.createStatement();

            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1)==1){
                    // loginMessageLabel.setText("Welcome");
                    control=false;
                    registrationMessageLabel.setText("This username or password allocated");
                }

            }

            if (control){

                statement.executeUpdate(insertToRegister);
                registrationMessageLabel.setText("User has been registered successfully");
            }



        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

}
