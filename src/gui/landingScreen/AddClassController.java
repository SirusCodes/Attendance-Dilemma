package gui.landingScreen;

import backend.ClassDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import models.ClassModel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddClassController implements Initializable {
    @FXML
    private ComboBox<String> classComboBox, yearComboBox, branchComboBox;

    @FXML
    private CheckBox checkBoxLab;


    public void getClassDetails() {
        ClassDB db = new ClassDB();
        ClassModel model = new ClassModel(
                0,
                branchComboBox.getValue(),
                yearComboBox.getValue(),
                classComboBox.getValue(),
                Date.valueOf(LocalDate.now()),
                checkBoxLab.isSelected()
        );
        try {
            db.insert(model);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
