package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamInformationController implements Initializable {

    @FXML
    private TableView<TeamSearchNation> tableTableView;

    @FXML
    private TableColumn<TeamSearchNation,Integer> numberTableColumn;

    @FXML
    private TableColumn<TeamSearchNation,Integer> ageTableColumn;

    @FXML
    private TableColumn<TeamSearchNation,String> firstnameTableColumn;

    @FXML
    private TableColumn<TeamSearchNation,String> midnameTableColumn;

    @FXML
    private TableColumn<TeamSearchNation,String> lastnameTableColumn;

    @FXML
    private TextField NationTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Label ageAvarageLabel;

    @FXML
    private Label totalPlayerLabel;




    String sportType;




    public void setSearchButton(ActionEvent e){

        search();
        calculateAvaage();
        calculateCount();
    }

    private void calculateAvaage(){
        String search = NationTextField.getText();
        String sportName=sportType;

        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();

        String avarageQuery= "select avg(Age) AS avg_age from athelete where Country='"+search+"' and TeamType='"+sportName+"' ";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(avarageQuery);

            if (queryOutput.next()) {
                double average = queryOutput.getDouble("avg_age");

                // Ortalama değeri etikete yazdır
                ageAvarageLabel.setText("Average Age: " + average);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void calculateCount(){
        String search = NationTextField.getText();
        String sportName=sportType;

        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();

        String avarageQuery= "select count(Age) AS total_player from athelete where Country='"+search+"' and TeamType='"+sportName+"' ";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(avarageQuery);

            if (queryOutput.next()) {
                int total = queryOutput.getInt("total_player");

                // Ortalama değeri etikete yazdır
                totalPlayerLabel.setText("Total Player: " + total);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void search(){

        String search = NationTextField.getText();
        String sportName=sportType;


        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();

        String query =  "select Number,Age,Country,Fname,Minit,Lname from athelete where Country='"+search+"' and TeamType='"+sportName+"' order by Fname desc; ";



        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);


            ObservableList<TeamSearchNation> athlete = FXCollections.observableArrayList();

            while (queryOutput.next()){

                int number = queryOutput.getInt("Number");
                int age=queryOutput.getInt("Age");
                String country = queryOutput.getString("Country");
                String firstname = queryOutput.getString("Fname");
                String midname = queryOutput.getString("Minit");
                String lastname = queryOutput.getString("Lname");

                TeamSearchNation teamSearchNation = new TeamSearchNation(number,age,country,firstname,midname,lastname);
                athlete.add(teamSearchNation);

            }

            numberTableColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
            ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
            firstnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Fname"));
            midnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Minit"));
            lastnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Lname"));

            tableTableView.setItems(athlete);

            numberTableColumn.setStyle("-fx-border-color: Blue");
            tableTableView.setStyle("-fx-border-color: Red");


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void getInfo(String type){
        sportType=type;
    }


    ObservableList<TeamSearchNation> teamSearchNationObservableList= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*
        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();

        String teamViewQuery = "select Number,Age,Fname,Minit,Lname from athelete where Country=\"China\" and TeamType=\"football\" ;";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(teamViewQuery);

            while (queryOutput.next()){

                Integer queryPlayerNumber = queryOutput.getInt("Number");
                Integer queryAge = queryOutput.getInt("Age");
                String queryFname = queryOutput.getString("Fname");
                String queryMinit = queryOutput.getString("Minit");
                String queryLname = queryOutput.getString("Lname");

                teamSearchNationObservableList.add(new TeamSearchNation(queryPlayerNumber,queryAge,queryFname,queryMinit,queryLname));
            }

            numberTableColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
            ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
            firstnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Fname"));
            midnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Minit"));
            lastnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Lname"));

            tableTableView.setItems(teamSearchNationObservableList);


        }
        catch (Exception e){

            e.printStackTrace();
        }*/

    }
}

