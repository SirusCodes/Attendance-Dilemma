package gui.addRecord;

import backend.ClassDB;
import backend.DateDB;
import backend.StudentDB;
import io.ReadRecord;
import io.WriteToExcel;
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
import models.ClassModel;
import models.DateModel;
import models.StudentModel;
import models.StudentRawModel;
import observableModels.RecordTableObservable;
import processes.GetStudentDuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddRecordScreenController implements Initializable {
    ArrayList<StudentRawModel> list;
    ArrayList<RecordTableObservable> recordObservables = new ArrayList<>();
    @FXML
    private Button backBtn;
    @FXML
    private TableView<RecordTableObservable> tableView;
    private TableColumn<RecordTableObservable, String> name, email, status;
    private TableColumn<RecordTableObservable, Integer> duration;
    private Integer classId;
    String className;
    private ClassModel classModel;

    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        scene.getStylesheets().add(getClass().getResource("../../style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void setClassModel(ClassModel model) {
        this.classModel = model;
    }

    public void saveBtnClicked(ActionEvent event) {
        Date date = Date.valueOf(list.get(0).getDateTime().toLocalDate());

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files (*.xls)", "*.xls")
        );
        String name = "Record-" + className + "-" + date;
        fileChooser.setInitialFileName(name + ".xls");
        final File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            ArrayList<String> header = new ArrayList<>();
            header.add("Name");
            header.add("Status");
            header.add("Duration");

            ArrayList<ArrayList<String>> data = new ArrayList<>(Collections.singleton(header));
            recordObservables.stream().map(model -> Collections.singleton(model.toArrayList())).forEach(data::addAll);

            WriteToExcel write = new WriteToExcel();
            write.write(data, file, name);
            if (date.compareTo(classModel.getDateTime()) > 0)
                saveToDB(date);
        }
    }

    private void saveToDB(Date date) {
        DateDB db = new DateDB();
        ClassDB classDB = new ClassDB();
        classModel.setDateTime(date);
        int numOfLect = classModel.getNoOfLectures() + 1;
        classModel.setNoOfLectures(numOfLect);

        System.out.println(classModel);
        try {
            classDB.update(classModel);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        for (RecordTableObservable observable : recordObservables) {
            try {
                db.insert(new DateModel(
                        observable.getEmail(),
                        date,
                        observable.getStatus()
                ));
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void setStudentList(String fileAddress, double minDuration, String start, String end, String className) {
        this.className = className;
        recordObservables.clear();
        ReadRecord readRecord = new ReadRecord();
        GetStudentDuration getStudentDuration = new GetStudentDuration();
        list = readRecord.readFile(fileAddress);
        recordObservables = getStudentDuration.getDuration(list, start, end, minDuration);
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
                if (Objects.equals(recordObservable.getName().toLowerCase(), name.toLowerCase())) {
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
