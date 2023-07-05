package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionController implements Initializable {

    @FXML
    Button squadInfoButton;

    @FXML
    private ImageView squadImageView;

    @FXML
    private ImageView calenderImageView;

    @FXML
    private Button matchDateButton;

    String sportType;

    String query;

  public void setSquadInfoButton(ActionEvent event){
      enterTeamInfo(event);
  }


  public void setMatchDateButton(ActionEvent event){
   try {
       String sportName=sportType;

       FXMLLoader loader = new FXMLLoader(getClass().getResource("matches.fxml"));
       Parent root = loader.load();

       MatchesController matchesController = loader.getController();
       matchesController.getInfo(sportName);

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

    public void enterTeamInfo(ActionEvent event){
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("teaminformation.fxml"));

            String sportName = sportType;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("teaminformation.fxml"));
            Parent root = loader.load();

            TeamInformationController teamInformationController=loader.getController();
            teamInformationController.getInfo(sportName);

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

    public void getInfo(String type){
      sportType=type;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File sFile =new File("Images/cl.png");
        Image fileImage = new Image(sFile.toURI().toString());
        calenderImageView.setImage(fileImage);

        File squadFile =new File("Images/squad.png");
        Image squadImage = new Image(squadFile.toURI().toString());
        squadImageView.setImage(squadImage);

    }
}
