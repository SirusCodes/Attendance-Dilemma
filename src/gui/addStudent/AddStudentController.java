package gui.addStudent;

import backend.StudentDB;
import gui.observableModel.StudentRawModel;
import io.ReadStudentDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.ClassModel;
import models.StudentModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
    @FXML
    public TableView<StudentRawModel> studentTableView;
    @FXML
    private Button backBtn;
    private ClassModel classModel = new ClassModel();

    private ArrayList<StudentRawModel> studentRawModels = new ArrayList<>();

    TableColumn<StudentRawModel, String> fname;
    TableColumn<StudentRawModel, String> lname;
    TableColumn<StudentRawModel, String> email;

    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void setTableView(ArrayList<StudentRawModel> studentRawModels) {
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
        if (file != null) {
            ReadStudentDetails studentDetails = new ReadStudentDetails();
            try {
                studentRawModels = studentDetails.getStudentData(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setTableView(studentRawModels);
        }
    }

    public void setClassModel(ClassModel model) {
        this.classModel = model;
    }

    public void addStudentClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../addStudent/add_student_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        StudentRawModel model = new StudentRawModel();
        AddStudentDialogController controller = fxmlLoader.getController();
        controller.setFieldBinding(model);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();
        if (response.isPresent() && response.get() == ButtonType.FINISH) {
            studentRawModels.add(0, model);
            setTableView(studentRawModels);
        }
    }

    public void saveBtnClicked(ActionEvent event) {
        StudentDB db = new StudentDB();
        for (StudentRawModel studentRawModel : studentRawModels) {
            StudentModel studentModel = new StudentModel(
                    studentRawModel.getEmail(),
                    classModel.getClassId(),
                    studentRawModel.getFname() + " " + studentRawModel.getLname()
            );
            try {
                db.insert(studentModel);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fname = new TableColumn<>("First Name");
        lname = new TableColumn<>("Last Name");
        email = new TableColumn<>("Email");

        studentTableView.getColumns().addAll(fname, lname, email);
    }
}
