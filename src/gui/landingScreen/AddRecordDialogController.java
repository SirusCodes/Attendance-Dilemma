package gui.landingScreen;

import backend.ClassDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import models.ClassModel;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRecordDialogController implements Initializable {

    public ComboBox<String> addRecordClassComboBox;
    public Label fileAddress;
    public Button selectBtn;

    private ArrayList<ClassModel> classModels = new ArrayList<>();
    private ArrayList<String> strClass = new ArrayList<>();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setClassDetails();
        ObservableList<String> classList = FXCollections.observableArrayList(strClass);
        addRecordClassComboBox.getItems().addAll(classList);
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
