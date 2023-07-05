package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {
    @FXML
    private Button backButton;

    @FXML
    private Button forgetUsernameButton;

    @FXML
    private Button forgetPasswordButton;

    public void setBackButton(ActionEvent event){
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


    public void setForgetPasswordButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("updatepassword.fxml"));

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
    public void setForgetUsernameButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("updateusername.fxml"));

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
