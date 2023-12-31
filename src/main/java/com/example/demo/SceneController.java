package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void switchToLogin(ActionEvent event) throws IOException {

         root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));

         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }

    public void switchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
