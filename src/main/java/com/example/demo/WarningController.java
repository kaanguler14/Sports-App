package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class WarningController {

    String firstname;
    String lastname;
    String username;
    String password;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    public void setYesButton(ActionEvent event){
    delete();

        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));

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

    public void setNoButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("menu.fxml"));

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

    private void delete(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String deleteQuery = "DELETE FROM useraccounts where Username='"+username+"' and Password='"+password+"';";

        try {
            Statement statement = connectDB.createStatement();

            statement.executeUpdate(deleteQuery);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getInfo(String un, String pw){


        username=un;
        password=pw;

    }

}
