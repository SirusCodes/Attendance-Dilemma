package gui.addStudent;

import backend.StudentDB;
import gui.addStudentDialog.AddStudentDialogController;
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
import observableModels.StudentRawObservable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
    @FXML
    public TableView<StudentRawObservable> studentTableView;
    TableColumn<StudentRawObservable, String> fname;
    TableColumn<StudentRawObservable, String> lname;
    TableColumn<StudentRawObservable, String> email;
    @FXML
    private Button backBtn;
    private ArrayList<StudentRawObservable> studentRawObservables = new ArrayList<>();
    private final ArrayList<StudentRawObservable> studentListDB = new ArrayList<>();
    private Integer classID;

    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        scene.getStylesheets().add(getClass().getResource("../../style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void setTableView() {
        final ObservableList<StudentRawObservable> data = FXCollections.observableArrayList(studentRawObservables);

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
                studentRawObservables = studentDetails.getStudentData(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setTableView();
        }
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
        getStudentFromDatabase();
        setTableView();
    }

    public void addStudentClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../addStudentDialog/add_student_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();
        dialogPane.getStylesheets().add(getClass().getResource("../../style.css").toExternalForm());

        StudentRawObservable model = new StudentRawObservable();
        AddStudentDialogController controller = fxmlLoader.getController();
        controller.setFieldBinding(model);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();
        if (response.isPresent() && response.get() == ButtonType.FINISH) {
            studentRawObservables.add(0, model);
            setTableView();
        }
    }

    public void saveBtnClicked(ActionEvent event) {
        StudentDB db = new StudentDB();
        for (StudentRawObservable studentRawObservable : studentRawObservables) {
            System.out.println(studentListDB.contains(studentRawObservable));
            if (!studentListDB.contains(studentRawObservable)) {
                StudentModel studentModel = new StudentModel(
                        studentRawObservable.getEmail(),
                        classID,
                        studentRawObservable.getFname() + " " + studentRawObservable.getLname()
                );
                try {
                    db.insert(studentModel);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        getStudentFromDatabase();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fname = new TableColumn<>("First Name");
        lname = new TableColumn<>("Last Name");
        email = new TableColumn<>("Email");

        studentTableView.getColumns().addAll(fname, lname, email);
    }

    private void getStudentFromDatabase() {
        StudentDB db = new StudentDB();
        ArrayList<StudentModel> studentModels = db.read(classID);
        System.out.println(classID);

        for (StudentModel model : studentModels) {
            String[] name = model.getStudentName().split(" ");
            studentListDB.add(new StudentRawObservable(model.getStudentId(), name[0], name[1]));
        }
        studentRawObservables = new ArrayList<>(studentListDB);
    }
}
