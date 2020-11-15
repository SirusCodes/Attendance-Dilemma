package gui.addRecord;

import io.ReadRecord;
import io.ReadStudentDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.StudentRawModel;
import processes.GetStudentDuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddRecordScreenController {
    @FXML
    private Button backBtn;

    private String fileAddress;

    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void saveBtnClicked(ActionEvent event) {
        System.out.println("File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx")
        );

        fileChooser.showSaveDialog(null);
    }

    public void setFileAddress(String fileAddress){
        this.fileAddress = fileAddress;
        ReadRecord readRecord = new ReadRecord();
        GetStudentDuration getStudentDuration = new GetStudentDuration();
        ArrayList<StudentRawModel> list;
        list = readRecord.readFile(fileAddress);
        getStudentDuration.getDuration(list,"11:00","12:00");
    }
}
