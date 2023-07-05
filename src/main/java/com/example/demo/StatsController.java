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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatsController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField nationTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

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
    private Label oldLabel;

    @FXML
    private Label youngLabel;




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
public void setSearchButton(ActionEvent event){
    search();
}
    private void search(){

        int ageMin;
        int ageMax;
        String name;
        String nation;
        String type ;
        String nameQuery="";
        String nationQuery="";
        String typeQuery="";

        if (!minTextField.getText().isBlank())
            ageMin= Integer.parseInt(minTextField.getText());
        else
            ageMin=0;

        if (!maxTextField.getText().isBlank())
            ageMax=Integer.parseInt(maxTextField.getText());
        else
            ageMax=1000;

        if (!nameTextField.getText().isBlank()){
            name=nameTextField.getText();
            //nameQuery="and Fname=all(select Fname from athelete where Fname='"+name+"')";
            nameQuery="and Fname=all(select Fname from athelete where Fname like'"+name+"%')";
        }
        if (!nationTextField.getText().isBlank()){
            nation = nationTextField.getText();
            nationQuery="and Country=all(select Country from athelete where Country='"+nation+"')";
        }

        if (!typeTextField.getText().isBlank()){
            type = typeTextField.getText();
            typeQuery="and TeamType=all(select TeamType from athelete where TeamType='"+type+"')";
        }



        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();

        String query="select Number,Age ,Fname,Minit,Lname,Country from athelete where age =any (select age from athelete where age between "+ageMin+" and "+ageMax+")"+nameQuery+nationQuery+typeQuery+";";





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

            min();
            max();


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void min(){

        int ageMin;
        int ageMax;
        String name;
        String nation;
        String type ;
        String nameQuery="";
        String nationQuery="";
        String typeQuery="";

        if (!minTextField.getText().isBlank())
            ageMin= Integer.parseInt(minTextField.getText());
        else
            ageMin=0;

        if (!maxTextField.getText().isBlank())
            ageMax=Integer.parseInt(maxTextField.getText());
        else
            ageMax=1000;

        if (!nameTextField.getText().isBlank()){
            name=nameTextField.getText();
            //nameQuery="and Fname=all(select Fname from athelete where Fname='"+name+"')";
            nameQuery="and Fname=all(select Fname from athelete where Fname like'"+name+"%')";
        }
        if (!nationTextField.getText().isBlank()){
            nation = nationTextField.getText();
            nationQuery="and Country=all(select Country from athelete where Country='"+nation+"')";
        }

        if (!typeTextField.getText().isBlank()){
            type = typeTextField.getText();
            typeQuery="and TeamType=all(select TeamType from athelete where TeamType='"+type+"')";
        }

        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();

        String minQuery="select min(Age) as min from athelete where age =any (select age from athelete where age between "+ageMin+" and "+ageMax+")"+nameQuery+nationQuery+typeQuery+";";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(minQuery);

            if (queryOutput.next()) {
                double min = queryOutput.getDouble("min");

                // Ortalama değeri etikete yazdır
                youngLabel.setText("Youngest Age: " + min);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    public void max(){

        int ageMin;
        int ageMax;
        String name;
        String nation;
        String type ;
        String nameQuery="";
        String nationQuery="";
        String typeQuery="";

        if (!minTextField.getText().isBlank())
            ageMin= Integer.parseInt(minTextField.getText());
        else
            ageMin=0;

        if (!maxTextField.getText().isBlank())
            ageMax=Integer.parseInt(maxTextField.getText());
        else
            ageMax=1000;

        if (!nameTextField.getText().isBlank()){
            name=nameTextField.getText();
            //nameQuery="and Fname=all(select Fname from athelete where Fname='"+name+"')";
            nameQuery="and Fname=all(select Fname from athelete where Fname like'"+name+"%')";
        }
        if (!nationTextField.getText().isBlank()){
            nation = nationTextField.getText();
            nationQuery="and Country=all(select Country from athelete where Country='"+nation+"')";
        }

        if (!typeTextField.getText().isBlank()){
            type = typeTextField.getText();
            typeQuery="and TeamType=all(select TeamType from athelete where TeamType='"+type+"')";
        }

        DatabaseConnection connectNow = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();

        String maxQuery="select max(Age) as max from athelete where age =any (select age from athelete where age between "+ageMin+" and "+ageMax+")"+nameQuery+nationQuery+typeQuery+";";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(maxQuery);

            if (queryOutput.next()) {
                double max = queryOutput.getDouble("max");

                // Ortalama değeri etikete yazdır
                oldLabel.setText("Oldest Age: " + max);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



}
