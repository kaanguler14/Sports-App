package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MatchesController implements Initializable {

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



    String  sportType;
    String queryType="";

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();


        String teamViewQuery = "SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone;";





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

    public void findMatchType(ActionEvent event){
        String SportType = sportType;


        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();


        String  teamViewQuery = "SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName WHERE g.name='"+SportType +"'GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone;";
        String  q="SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone FROM game g JOIN team t ON g.TeamID = t.TeamID JOIN referee r ON g.RefereeID = r.RefereeID JOIN event e ON g.EventName = e.EventName WHERE g.name ='"+sportType+"' GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone;\n";



        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(q);

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

        String countQuery= "SELECT COUNT(*) AS Count\n" +
                "FROM (\n" +
                "    SELECT g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, GROUP_CONCAT(DISTINCT t.CountryName) AS CountryNames, e.Location, e.Phone\n" +
                "    FROM game g\n" +
                "    JOIN team t ON g.TeamID = t.TeamID\n" +
                "    JOIN referee r ON g.RefereeID = r.RefereeID\n" +
                "    JOIN event e ON g.EventName = e.EventName\n" +
                "    GROUP BY g.GameID, g.ChannelName, g.Date, g.Time, g.name, r.Fname, e.Location, e.Phone\n" +
                ") AS CountTable;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(countQuery);

            if (queryOutput.next()) {
                int total = queryOutput.getInt("Count");

                // Ortalama değeri etikete yazdır
                totalLabel.setText("Total Matches: " + total);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void getInfo(String type)
    {

        sportType=type;


    }


}
