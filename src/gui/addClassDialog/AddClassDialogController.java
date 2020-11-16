package gui.addClassDialog;

import backend.ClassDB;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import models.ClassModel;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddClassDialogController {
    @FXML
    private ComboBox<String> classComboBox, yearComboBox, branchComboBox;

    @FXML
    private CheckBox checkBoxLab;

    private ArrayList<ClassModel> classModels = new ArrayList<>();

    public void setClassList(ArrayList<ClassModel> models) {
        classModels = models;
    }

    public Boolean getClassDetails() {
        ClassDB db = new ClassDB();
        ClassModel model = new ClassModel(
                0,
                branchComboBox.getValue(),
                yearComboBox.getValue(),
                classComboBox.getValue(),
                Date.valueOf(LocalDate.now()),
                checkBoxLab.isSelected()
        );
        if (!classModels.contains(model))
            try {
                db.insert(model);
                return true;
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }

        return false;
    }
}
