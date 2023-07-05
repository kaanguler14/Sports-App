package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class NationInfoController {

    @FXML
    private Button backButton;

    @FXML
    private CheckBox footballCheckButton;

    @FXML
    private CheckBox basketballCheckButton;


    @FXML
    private CheckBox runningCheckButton;

    @FXML
    private TextField dayTextField;
    @FXML
    private TextField monthTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private TableView<GameSearch> tableTableView;

    @FXML
    private TableColumn<GameSearch,String> matchtypeColumn;

    @FXML
    private TableColumn<GameSearch, Date> dateColumn;

    @FXML
    private TableColumn<GameSearch, Time> timeColumn;

    @FXML
    private TableColumn<GameSearch,String> teamsColumn;



    @FXML
    private TableColumn<GameSearch,String> refereeColumn;

    @FXML
    private TableColumn<GameSearch,String> channelNameColumn;


    @FXML
    private TableColumn<GameSearch,String> locationColumn;


    @FXML
    private TableColumn<GameSearch,String> phoneColumn;

    @FXML
    private Label totalLabel;

    @FXML
    private Button searchButton;



    public void setBackButton(ActionEvent event){
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


    ObservableList<GameSearch> gameSearchObservableList= FXCollections.observableArrayList();

    public void setSearchButton(ActionEvent event){

        String day=dayTextField.getText();
        String month=monthTextField.getText();
        String year=yearTextField.getText();
        String date="";
        if (!day.isBlank() && !month.isBlank() && !year.isBlank()){
            date=year+"-"+month+"-"+day;
        }


        String football="";
        String footballQuery="";

        String basketball="";
        String basketballQuery="";

        String running="";
        String runningQuery = "";

        boolean tf=false;
        String union="";
        String dateQ="";
        if (!date.isBlank())
            dateQ=" g.Date = '"+dateQ+"' ";
        if (footballCheckButton.isSelected()){


            football="football";
            footballQuery = " SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName  where g.name=\"football\"  GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone ";
            tf=true;


        }
        if (basketballCheckButton.isSelected()){
            if (tf){
                union=" union ";
            }
            basketball="basketball";
            basketballQuery= union+"  (SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName  where g.name=\"basketball\"  GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone) ";
            union="";
        }
        else
            tf=false;
        if (runningCheckButton.isSelected()){
            if (tf)
                union=" union ";
            running="running";
            runningQuery=union+" (SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName  where g.name=\"running\"  GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone) ";

        }


String teamViewQuery=footballQuery +basketballQuery+ runningQuery;






        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();


    //    String teamViewQuery = "SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName  where g.name=\"football\" GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone;";





        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(teamViewQuery);

            while (queryOutput.next()){


                int ıdQuery = queryOutput.getInt("GameID");
                String channelQuery = queryOutput.getString("ChannelName");
                Date dateQuery = queryOutput.getDate("Date");
                Time timesQuery = queryOutput.getTime("Time");
                String nameQuery = queryOutput.getString("name");
                String FnameQuery = queryOutput.getString("Fname");
                String countyNameQuery=queryOutput.getString("CountryNames");
                String locationQuery=queryOutput.getString("Location");
                String phoneQuery=queryOutput.getString("Phone");

                gameSearchObservableList.add(new GameSearch(nameQuery,dateQuery,timesQuery,countyNameQuery,FnameQuery,channelQuery,locationQuery,phoneQuery));
            }

            matchtypeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
            teamsColumn.setCellValueFactory(new PropertyValueFactory<>("CountryNames"));
            refereeColumn.setCellValueFactory(new PropertyValueFactory<>("Fname"));
            channelNameColumn.setCellValueFactory(new PropertyValueFactory<>("ChannelName"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));

            tableTableView.setItems(gameSearchObservableList);
            calculateCount();

        }
        catch (Exception e){

            e.printStackTrace();
        }
    }



    private void calculateCount(){


        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();


        String day=dayTextField.getText();
        String month=monthTextField.getText();
        String year=yearTextField.getText();
        String date="";
        if (!day.isBlank() && !month.isBlank() && !year.isBlank()){
            date=year+"-"+month+"-"+day;
        }


        String football="";
        String footballQuery="";

        String basketball="";
        String basketballQuery="";

        String running="";
        String runningQuery = "";

        boolean tf=false;
        String union="";
        String dateQ="";
        if (!date.isBlank())
            dateQ=" g.Date = '"+dateQ+"' ";
        if (footballCheckButton.isSelected()){


            football="football";
            footballQuery = " SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName  where g.name=\"football\"  GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone ";
            tf=true;


        }
        if (basketballCheckButton.isSelected()){
            if (tf){
                union=" union ";
            }
            basketball="basketball";
            basketballQuery= union+"  ( SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName  where g.name=\"basketball\"  GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone) ";
            union="";
        }
        else
            tf=false;
        if (runningCheckButton.isSelected()){
            if (tf)
                union=" union ";
            running="running";
            runningQuery=union+" (SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName  where g.name=\"running\"  GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone )";

        }


        String teamViewQuery=footballQuery +basketballQuery+ runningQuery;

        String countQuery="SELECT COUNT(*) AS total FROM ( "+ teamViewQuery+ " ) AS subquery ;";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(countQuery);

            if (queryOutput.next()) {
                int total = queryOutput.getInt("total");

                // Ortalama değeri etikete yazdır
                totalLabel.setText("Total Matches: " + total);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
