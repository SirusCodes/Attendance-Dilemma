package gui.landingScreen;

import backend.ClassDB;
import gui.addRecord.AddRecordScreenController;
import gui.addStudent.AddStudentController;
import gui.addStudent.SelectClassDialogController;
import gui.observableModel.GenericObservable;
import gui.observableModel.RecordDataObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.ClassModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class LandingScreenController implements Initializable {

    @FXML
    private Button addRecordBtn;

    @FXML
    public ComboBox<String> addRecordClassComboBox;
    public Label fileAddress;
    public Button selectBtn;

    private ArrayList<ClassModel> classModels = new ArrayList<>();
    private ArrayList<String> strClass = new ArrayList<>();
    private ArrayList<Integer> classIds = new ArrayList<>();
    private GenericObservable classList;
    private final StringProperty selectedClass = new SimpleStringProperty();

    public void addRecordClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add_record_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        final RecordDataObservable recordDataObservable = new RecordDataObservable();

        AddRecordDialogController controller = fxmlLoader.getController();
        controller.setClassComboBox(classList, selectedClass, recordDataObservable);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent())
            if (response.get().equals(ButtonType.NEXT)) {
                gotoAddRecordScreen(recordDataObservable.getFileAddress());
            }
    }

    public void addStudentClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../addStudent/select_class.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        SelectClassDialogController controller = fxmlLoader.getController();
        controller.setClassComboBox(classList, selectedClass);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select Class");
        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent())
            if (response.get().equals(ButtonType.NEXT))
                gotoAddStudentScreen(selectedClass.get());
    }

    private void gotoAddStudentScreen(String className) throws IOException {
        Stage stage = (Stage) addRecordBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../addStudent/add_student_screen.fxml"));
        Parent parent = fxmlLoader.load();

        final AddStudentController controller = fxmlLoader.getController();
        final ClassModel model = classModels.get(strClass.indexOf(className));
        controller.setClassModel(model);

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Add Record");
        stage.show();
    }

    @FXML
    public void addClassClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add_class_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        AddClassController controller = fxmlLoader.getController();
        controller.setClassList(classModels);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Add Class");

        final Optional<ButtonType> response = dialog.showAndWait();
        if (response.isPresent())
            if (response.get() == ButtonType.FINISH)
                controller.getClassDetails();
    }

    public void gotoAddRecordScreen(String file) throws IOException {
        Stage stage = (Stage) addRecordBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../addRecord/add_record_screen.fxml"));
        Parent parent = fxmlLoader.load();

        AddRecordScreenController controller = fxmlLoader.getController();
        controller.setFileAddress(file);

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Add Record");
        stage.show();
    }

    public void showRecordClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("show_record_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent())
            if (response.get().equals(ButtonType.NEXT))
                gotoShowRecordScreen();
    }

    public void gotoShowRecordScreen() {
        System.out.println("NextScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setClassDetails();
        classList = new GenericObservable(strClass, classIds);
    }

    private void setClassDetails() {
        ClassDB db = new ClassDB();
        classModels = db.read();
        for (ClassModel model : classModels) {
            String name = model.getLab() ?
                    String.format("%s%s%s %s", model.getYear(), model.getBranch(), model.getBatch(), "Lab") :
                    String.format("%s%s%s", model.getYear(), model.getBranch(), model.getBatch());
            strClass.add(name);
            classIds.add(model.getClassId());
        }
    }


}
