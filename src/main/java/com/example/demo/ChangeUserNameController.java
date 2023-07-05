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

public class ChangeUserNameController {

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private TextField newUsernameTextField;

    @FXML
    private TextField confirmUsernameTextField;

    @FXML
    private Label warningLabel;



    String firstname="";

    String lastname="";

    String password="";

    public void setBackButton(ActionEvent event){
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

    public void setChangeButton(ActionEvent event){
        updateUser();
    }

    public void getInfo(String fn, String ln , String pw){

        firstname=fn;
        lastname=ln;
        password=pw;

    }

    public void updateUser(){

        boolean control=true;

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String newUsername=newUsernameTextField.getText();

        if (confirmUsernameTextField.getText().isBlank()==true || newUsernameTextField.getText().isBlank()==true){
            control=false;
            warningLabel.setText("Please fill all fields");
        }
        else if (!confirmUsernameTextField.getText().equals(newUsernameTextField.getText())){
            control=false;
            warningLabel.setText("Usernames don't match");

        }


        String updateQuery="update useraccounts set Username = '"+newUsername+"' where Firstname='"+firstname+"' and Lastname='"+lastname+"' and Password='"+password+"';";

        String query="UPDATE useraccounts SET Username = CASE WHEN Firstname = '"+firstname+"' and Lastname='"+lastname+"' and Password='"+password+"' THEN '"+newUsername+"' ELSE Username END;";

        try {
            Statement statement = connectDB.createStatement();

            String verifyLogin = "select count(1) from useraccounts where username='"+newUsername+"';";

            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1)==1){
                    // loginMessageLabel.setText("Welcome");
                    control=false;
                    warningLabel.setText("This username  allocated");
                }

            }

            if (control)
                statement.executeUpdate(query);


        }
        catch (Exception e){
            e.printStackTrace();
        }



    }



}
