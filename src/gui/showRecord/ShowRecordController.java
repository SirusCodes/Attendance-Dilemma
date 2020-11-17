package gui.showRecord;

import backend.ShowRecordsDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.ShowRecordsModel;
import observableModels.RecordObservable;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ShowRecordController implements Initializable {
    public Button backBtn;

    private final ArrayList<RecordObservable> observables = new ArrayList<>();
    public TableView<RecordObservable> tableView;
    private TableColumn<RecordObservable, String> name;
    private TableColumn<RecordObservable, Double> percentage;
    private List<TableColumn<RecordObservable, LocalDate>> attendances;


    public void backBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../landingScreen/landing_screen.fxml"));

        Scene scene = new Scene(parent, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Attendance Dilemma");
        stage.show();
    }

    public void saveBtnClicked(ActionEvent event) {
    }

    public void setData(int classID, LocalDate startDate, LocalDate endDate) {
        ShowRecordsDB db = new ShowRecordsDB();
        ArrayList<ShowRecordsModel> recordList = db.read(Date.valueOf(startDate), Date.valueOf(endDate), classID);
        observables.clear();

        HashMap<String, ArrayList<HashMap<LocalDate, String>>> map = new HashMap<>();
        for (ShowRecordsModel showRecordsModel : recordList) {
            ArrayList<HashMap<LocalDate, String>> pData = map.getOrDefault(showRecordsModel.getStudentName(), new ArrayList<>());
            HashMap<LocalDate, String> data = new HashMap<>();
            data.put(showRecordsModel.getDate().toLocalDate(), showRecordsModel.getAttendance());
            pData.add(data);
            map.put(showRecordsModel.getStudentName(), pData);
        }

        map.forEach((key, value) -> System.out.println(key + " " + value));
//        setTableView();
    }

    public void setTableView() {
        name = new TableColumn<>("Name");
        percentage = new TableColumn<>("Percentage");

        final ObservableList<RecordObservable> data = FXCollections.observableArrayList(observables);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        tableView.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
