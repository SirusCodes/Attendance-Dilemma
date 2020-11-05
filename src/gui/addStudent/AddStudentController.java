package gui.addStudent;

import io.ReadStudentDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import gui.observableModel.StudentRawModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AddStudentController {
    @FXML
    public TableView<StudentRawModel> studentTableView;
    @FXML
    private Button backBtn;
    private String className;

    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void setTableView(ArrayList<StudentRawModel> studentRawModels){
        TableColumn<StudentRawModel, String> fname = new TableColumn<>("First Name");
        TableColumn<StudentRawModel, String> lname = new TableColumn<>("Last Name");
        TableColumn<StudentRawModel, String> email = new TableColumn<>("Email");

        studentTableView.getColumns().addAll(fname,lname,email);
        final ObservableList<StudentRawModel> data = FXCollections.observableArrayList(studentRawModels);

        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        studentTableView.setItems(data);
    }

    public void showFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files (*.xls)", "*.xls")
        );

        final File file = fileChooser.showOpenDialog(null);
        ReadStudentDetails studentDetails = new ReadStudentDetails();
        ArrayList<StudentRawModel> studentRawModels = new ArrayList<>();
        try {
           studentRawModels= studentDetails.getStudentData(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
       setTableView(studentRawModels);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void addStudentClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../addStudent/add_student_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();
    }

    public void saveBtnClicked(ActionEvent event) {

    }
}
