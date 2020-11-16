package gui.selectClassDialog;

import observableModels.GenericObservable;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectClassDialogController implements Initializable {
    @FXML
    private ComboBox<String> classComboBoxAddStudent;

    public void setClassComboBox(GenericObservable data, StringProperty selected) {
        classComboBoxAddStudent.itemsProperty().bind(data.listProperty());
        classComboBoxAddStudent.valueProperty().bindBidirectional(selected);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
