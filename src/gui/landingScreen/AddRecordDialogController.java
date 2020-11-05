package gui.landingScreen;

import gui.observableModel.GenericObservable;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
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

    void setClassComboBox(GenericObservable data, StringProperty selected) {
        addRecordClassComboBox.itemsProperty().bind(data.listProperty());
        addRecordClassComboBox.valueProperty().bindBidirectional(selected);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
