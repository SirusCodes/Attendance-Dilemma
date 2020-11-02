package gui.landingScreen;

import backend.ClassDB;
import gui.observableModel.GenericObservable;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.ClassModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRecordDialogController implements Initializable {

    public ComboBox<String> addRecordClassComboBox;
    public Label fileAddress;
    public Button selectBtn;

    public void showFileChooser(ActionEvent event) {
        System.out.println("File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("Excel Files (*.xlsx, *.xls)", "*.xlsx", "*.xls")
        );

        final File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            fileAddress.setText(file.getAbsolutePath());
            selectBtn.setText("Change");
        }
    }
    void setClassCombobox(GenericObservable data){
        addRecordClassComboBox.itemsProperty().bind(data.listProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
