package gui.selectClassDialog;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import observableModels.GenericObservable;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectClassDialogController implements Initializable {
    @FXML
    private ComboBox<String> selectClassComboBox;

    public void setClassComboBox(GenericObservable data, StringProperty selected) {
        selectClassComboBox.itemsProperty().bind(data.listProperty());
        selectClassComboBox.valueProperty().bindBidirectional(selected);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
