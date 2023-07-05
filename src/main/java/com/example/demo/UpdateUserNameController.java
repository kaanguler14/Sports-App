package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateUserNameController {

@FXML
private Button backButton;

@FXML
private Button searchButton;

@FXML
private TextField firstnameTextField;

@FXML
private TextField lastnameTextField;

@FXML
private TextField passwordTextField;

@FXML
private Label matchLabel;


public void setBackButton(ActionEvent event){
    try {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("help.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    catch (Exception e){
        e.printStackTrace();
        e.getCause();

    }
}


public void setSearchButton(ActionEvent event){

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    String verifyLogin = "select count(1) from useraccounts where Firstname='"+firstnameTextField.getText()+"' and Lastname='"+lastnameTextField.getText()+"' and Password='"+passwordTextField.getText()+"'";

    try {

        Statement statement = connectDB.createStatement();

        ResultSet queryResult = statement.executeQuery(verifyLogin);

        while (queryResult.next()){
            if (queryResult.getInt(1)==1){

                changeUserName(event);
                // loginMessageLabel.setText("Welcome");
                // createAccountForm();
            }
            else {
                matchLabel.setText("It is not found");
            }
        }

    }
    catch (Exception e){
        e.printStackTrace();
    }


}

public void changeUserName(ActionEvent event){
    try {
    //    Parent root = FXMLLoader.load(HelloApplication.class.getResource("changeusername.fxml"));

        String firstname=firstnameTextField.getText();
        String lastname=lastnameTextField.getText();
        String password=passwordTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeusername.fxml"));
        Parent root = loader.load();

        ChangeUserNameController changeUserNameController = loader.getController();
        changeUserNameController.getInfo(firstname,lastname,password);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    catch (Exception e){
        e.printStackTrace();
        e.getCause();

    }
}





}
