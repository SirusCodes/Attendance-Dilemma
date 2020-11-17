package gui.addRecord;

import backend.DateDB;
import backend.StudentDB;
import observableModels.RecordTableObservable;
import io.ReadRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.DateModel;
import models.StudentModel;
import models.StudentRawModel;
import processes.GetStudentDuration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddRecordScreenController implements Initializable {
    @FXML
    private Button backBtn;

    @FXML
    private TableView<RecordTableObservable> tableView;

    private TableColumn<RecordTableObservable, String> name, email, status;
    private TableColumn<RecordTableObservable, Integer> duration;
    ArrayList<StudentRawModel> list;
    private Integer classId;

    ArrayList<RecordTableObservable> recordObservables = new ArrayList<>();

    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void saveBtnClicked(ActionEvent event) {
        DateDB db = new DateDB();

        for (RecordTableObservable observable : recordObservables) {
            try {
                db.insert(new DateModel(
                        observable.getEmail(),
                        Date.valueOf(list.get(0).getDateTime().toLocalDate()),
                        observable.getStatus()
                ));
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files (*.xls)", "*.xls")
        );

        fileChooser.showSaveDialog(null);
    }

    public void setStudentList(String fileAddress, double minDuration) {
        recordObservables.clear();
        ReadRecord readRecord = new ReadRecord();
        GetStudentDuration getStudentDuration = new GetStudentDuration();
        list = readRecord.readFile(fileAddress);
        recordObservables = getStudentDuration.getDuration(list, "11:00", "12:00", minDuration);
        analysisListData();
    }

    private void analysisListData() {
        StudentDB studentDB = new StudentDB();
        ArrayList<StudentModel> studentList = studentDB.read(classId);
        ArrayList<RecordTableObservable> absentStudents = new ArrayList<>();

        for (StudentModel model : studentList) {
            final String name = model.getStudentName();
            boolean found = false;
            for (RecordTableObservable recordObservable : recordObservables) {
                if (Objects.equals(recordObservable.getName(), name)) {
                    found = true;
                    recordObservable.setEmail(model.getStudentId());
                }
            }

            if (!found) {
                RecordTableObservable modelObservable = new RecordTableObservable(
                        model.getStudentId(),
                        model.getStudentName(),
                        "A",
                        0
                );
                absentStudents.add(modelObservable);
            }
        }

        recordObservables.addAll(absentStudents);
        setTableView();
    }

    public void setTableView() {
        final ObservableList<RecordTableObservable> data = FXCollections.observableArrayList(recordObservables);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tableView.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name = new TableColumn<>("Name");
        email = new TableColumn<>("Email");
        duration = new TableColumn<>("Duration");
        status = new TableColumn<>("Status");

        tableView.getColumns().addAll(name, email, duration, status);
    }
}
