package gui.showRecordDialog;

import backend.ClassDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import models.ClassModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowRecordDialogController implements Initializable {
    public ComboBox<String> classComboBox;
    private ArrayList<ClassModel> classModels = new ArrayList<>();

    private ArrayList<String> strClass = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setClassDetails();
        ObservableList<String> classList = FXCollections.observableArrayList(strClass);
        classComboBox.getItems().addAll(classList);
    }

    private void setClassDetails() {
        ClassDB db = new ClassDB();
        classModels = db.read();
        for (ClassModel model : classModels) {
            String name = model.getLab() ?
                    String.format("%s%s%s %s", model.getYear(), model.getBranch(), model.getBatch(), "Lab") :
                    String.format("%s%s%s", model.getYear(), model.getBranch(), model.getBatch());
            strClass.add(name);
        }
    }
}
