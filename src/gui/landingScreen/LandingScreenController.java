package gui.landingScreen;

import backend.ClassDB;
import gui.observableModel.GenericObservable;
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


    public void addRecordClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add_record_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        AddRecordDialogController controller = fxmlLoader.getController();
        controller.setClassCombobox(classList);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent())
            if (response.get().equals(ButtonType.NEXT))
                gotoAddRecordScreen();
    }

    public void addStudentClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../addStudent/select_class.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select Class");
        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent())
            if (response.get().equals(ButtonType.NEXT))
                gotoAddStudentScreen();
    }

    private void gotoAddStudentScreen() throws IOException {
        Stage stage = (Stage) addRecordBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../addStudent/add_student_screen.fxml"));

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
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Add Class");

        final Optional<ButtonType> response = dialog.showAndWait();
        if (response.isPresent())
            if (response.get() == ButtonType.FINISH)
                controller.getClassDetails();
    }

    public void gotoAddRecordScreen() throws IOException {
        Stage stage = (Stage) addRecordBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../addRecord/add_record_screen.fxml"));

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
        classList = new GenericObservable(strClass,classIds );
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
