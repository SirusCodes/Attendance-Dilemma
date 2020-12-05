package gui.addClassDialog;

import backend.ClassDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import models.ClassModel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddClassDialogController implements Initializable {
    @FXML
    private VBox subBatchVBox;
    @FXML
    private ComboBox<String> classComboBox, yearComboBox, branchComboBox, subBatch;


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
                subBatch.getValue(),
                Date.valueOf(LocalDate.of(2020, 6,1)),
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxLab.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                subBatchVBox.setVisible(t1);
            }
        });
    }
}
