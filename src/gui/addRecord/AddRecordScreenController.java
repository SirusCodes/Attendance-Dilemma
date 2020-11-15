package gui.addRecord;

import gui.observableModel.RecordTableObservable;
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
import models.StudentRawModel;
import processes.GetStudentDuration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRecordScreenController implements Initializable {
    @FXML
    private Button backBtn;

    @FXML
    private TableView<RecordTableObservable> tableView;

    private TableColumn<RecordTableObservable, String> name, email, status;
    private TableColumn<RecordTableObservable, Integer> duration;

    ArrayList<RecordTableObservable> recordObservables = new ArrayList<>();

    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void saveBtnClicked(ActionEvent event) {
        System.out.println("File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx")
        );

        fileChooser.showSaveDialog(null);
    }

    public void setStudentList(String fileAddress, double minDuration) {
        recordObservables.clear();
        ReadRecord readRecord = new ReadRecord();
        GetStudentDuration getStudentDuration = new GetStudentDuration();
        ArrayList<StudentRawModel> list;
        list = readRecord.readFile(fileAddress);
        recordObservables = getStudentDuration.getDuration(list, "11:00", "12:00", minDuration);
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
