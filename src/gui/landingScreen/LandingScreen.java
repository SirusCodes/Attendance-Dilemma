package gui.landingScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("landing_screen.fxml"));

        primaryStage.setResizable(false);
        primaryStage.setTitle("Attendance Dilemma");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();
    }
}


