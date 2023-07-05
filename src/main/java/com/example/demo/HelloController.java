package com.example.demo;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField UsernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private
    Button helpButton;

    public void setHelpButton(ActionEvent event){

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

    //    help();
    }



    public void loginButtonMessage(ActionEvent event){

        if (UsernameTextField.getText().isBlank()==false && passwordPasswordField.getText().isBlank()==false){

            validateLogin(event);

        }
        else {
            loginMessageLabel.setText("Please enter username and password");
        }


    }



    public void setCancelButton(ActionEvent e){

        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setRegisterButton(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("register.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }

        //createAccountForm();
    }

    public void validateLogin(ActionEvent event){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String verifyLogin = "select count(1) from useraccounts where username='"+UsernameTextField.getText()+"' and password='"+passwordPasswordField.getText()+"';";

        try {

            Statement statement = connectDB.createStatement();

            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1)==1){

                    EnterMenu(event);
                    // loginMessageLabel.setText("Welcome");
                   // createAccountForm();
                }
                else {
                    loginMessageLabel.setText("Invalid Login.Please try again");
                }
            }

        }
        catch (Exception e){
        e.printStackTrace();
        }

    }

    public void createAccountForm(){
        try {
            Parent root =FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage=new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,520,523));
            registerStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }

    public void EnterMenu(ActionEvent event){
        try {
            //  Parent root = FXMLLoader.load(getClass().getResource("option.fxml"));



            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();

            MenuController menuController = loader.getController();
            menuController.getInfo(UsernameTextField.getText(),passwordPasswordField.getText());

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

    public void help(){
        try {
            Parent root =FXMLLoader.load(getClass().getResource("help.fxml"));
            Stage registerStage=new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,604,318));
            registerStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }



}