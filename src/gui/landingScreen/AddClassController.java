package gui.landingScreen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClassController implements Initializable {
    @FXML
    private ComboBox<String> classComboBox, yearComboBox, branchComboBox;

    @FXML
    private CheckBox checkBoxLab;


    public void getClassDetails() {
        System.out.println(classComboBox.getValue() + " " + yearComboBox.getValue() + " " + branchComboBox.getValue() + " " + checkBoxLab.isSelected());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
