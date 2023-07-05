package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){

        String databaseName="sportapp";
        String databaseUser="root";
        String databasePassword ="Araban02";
        String url ="jdbc:mysql://127.0.0.1:3306/"+databaseName;

        try {

        databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch (Exception e){
            e.printStackTrace();
        }

return databaseLink;
    }

}
