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

public class ChangePasswordController {

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private Label warningLabel;

    String firstname="";

    String lastname="";

    String username="";

    public void setBackButton(ActionEvent event){
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
    public void setChangeButton(ActionEvent event){
        updatePassword();
    }



    public void updatePassword(){

        boolean control=true;

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String newPassword=newPasswordTextField.getText();

        if (confirmPasswordTextField.getText().isBlank()==true || newPasswordTextField.getText().isBlank()==true){
            control=false;
            warningLabel.setText("Please fill all fields");
        }
        else if (!confirmPasswordTextField.getText().equals(newPasswordTextField.getText())){
            control=false;
            warningLabel.setText("Passwords don't match");

        }


        String updateQuery="update useraccounts set Password = '"+newPasswordTextField.getText()+"' where Firstname='"+firstname+"' and Lastname='"+lastname+"' and Username='"+username+"';";


        try {
            Statement statement = connectDB.createStatement();

            String verifyLogin = "select count(1) from useraccounts where Password='"+newPassword+"';";

            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1)==1){
                    // loginMessageLabel.setText("Welcome");
                    control=false;
                    warningLabel.setText("This password  allocated");
                }

            }

            if (control){
                warningLabel.setText("Password Changed Succesfully");
                statement.executeUpdate(updateQuery);

            }



        }
        catch (Exception e){
            e.printStackTrace();
        }



    }




    public void getInfo(String fn, String ln , String un){

        firstname=fn;
        lastname=ln;
        username=un;

    }
}
