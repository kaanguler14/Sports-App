package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuController {

    @FXML
    Button footballButton;

    @FXML
    Button basketballButton;

    @FXML
    Button runningButton;

    @FXML
    Label sportLabel;

    @FXML
    Button deleteButton;

    @FXML
    Button statsButton;

    @FXML
    Button logoutButton;

    @FXML
    Button matchesButton;

    @FXML
    private Button backButton;

    @FXML
    private Button nationInfoButton;

    String sportType;


    String username;
    String password;


    public void setNationInfoButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("nationinfo.fxml"));

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

    public void setBackButton(ActionEvent event) {
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

    public void setMatchesButton(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("matches.fxml"));

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

    public void setLogoutButton(ActionEvent event){
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

    public void setStatsButton(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(HelloApplication.class.getResource("stats.fxml"));

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


    public void setDeleteButton(ActionEvent event){
        try {
            //  Parent root = FXMLLoader.load(getClass().getResource("option.fxml"));

            String userName=username;
            String passWord=password;


            FXMLLoader loader = new FXMLLoader(getClass().getResource("warning.fxml"));
            Parent root = loader.load();

            WarningController warningController = loader.getController();
            warningController.getInfo(userName,passWord);

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

    public void setFootballButton(ActionEvent event){
        sportType="football";
        enterOptions(event);

    }

    public void setBasketballButton(ActionEvent event){
        sportType="basketball";
        enterOptions(event);
    }

    public void setRunningButton(ActionEvent event){
        sportType="running";
        enterOptions(event);
    }

    public void enterOptions(ActionEvent event){
        try {
          //  Parent root = FXMLLoader.load(getClass().getResource("option.fxml"));

            String sportName=sportType;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("option.fxml"));
            Parent root = loader.load();

            OptionController optionController = loader.getController();
            optionController.getInfo(sportName);

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

    public void getInfo( String un, String pw){

        username=un;

        password=pw;

    }



}
