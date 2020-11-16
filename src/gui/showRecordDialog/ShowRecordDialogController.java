package gui.showRecordDialog;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import observableModels.GenericObservable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ShowRecordDialogController implements Initializable {
    public ComboBox<String> classComboBox;
    public DatePicker startDate, endDate;


    public void setProperties(GenericObservable data, StringProperty selected, ObjectProperty<LocalDate> startDate, ObjectProperty<LocalDate> endDate) {
        classComboBox.itemsProperty().bind(data.listProperty());
        classComboBox.valueProperty().bindBidirectional(selected);

        this.startDate.valueProperty().bindBidirectional(startDate);
        this.endDate.valueProperty().bindBidirectional(endDate);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
