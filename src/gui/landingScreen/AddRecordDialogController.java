package gui.landingScreen;

import gui.observableModel.GenericObservable;
import gui.observableModel.RecordDataObservable;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRecordDialogController implements Initializable {

    public ComboBox<String> addRecordClassComboBox;
    public Label fileAddress;
    public Button selectBtn;
    public TextField startTime;
    public TextField endTime;
    public TextField minDuration;

    public void showFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("Excel Files (*.xls)", "*.xls")
        );

        final File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            fileAddress.setText(file.getAbsolutePath());
            selectBtn.setText("Change");
        }
    }

    void setClassComboBox(GenericObservable data, StringProperty selected, RecordDataObservable rDataObservable) {
        addRecordClassComboBox.itemsProperty().bind(data.listProperty());
        addRecordClassComboBox.valueProperty().bindBidirectional(selected);
        startTime.textProperty().bindBidirectional(rDataObservable.startTimeProperty());
        endTime.textProperty().bindBidirectional(rDataObservable.endTimeProperty());
        minDuration.textProperty().bindBidirectional(rDataObservable.minDurationProperty(), new NumberStringConverter());
        fileAddress.textProperty().bindBidirectional(rDataObservable.fileAddressProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
