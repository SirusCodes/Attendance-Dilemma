package gui.showRecord;

import backend.ShowRecordsDB;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import models.ShowRecordsModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ShowRecordController implements Initializable {
    public Button backBtn;

    private ObservableList<ObservableList<Object>> observables = FXCollections.observableList(new ArrayList<>());
    public TableView tableView;
    private TableColumn<ObservableList, String> name;
    Set<LocalDate> dateSet = new HashSet<>();
    HashMap<String, ArrayList<String>> map = new HashMap<>();


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

        for (ShowRecordsModel showRecordsModel : recordList) {
            ArrayList<String> pData = map.getOrDefault(showRecordsModel.getStudentName(), new ArrayList<>());
            LocalDate date = showRecordsModel.getDate().toLocalDate();
            dateSet.add(date);
            pData.add(showRecordsModel.getAttendance());
            map.putIfAbsent(showRecordsModel.getStudentName(), pData);
        }

        setTableView();
    }

    public void setTableView() {
        name = new TableColumn<>("Name");
        name.setCellValueFactory(params -> new SimpleStringProperty(params.getValue().get(0).toString()));
        tableView.getColumns().add(name);

        Object[] list = dateSet.toArray();
        for (int i = 0; i < list.length; i++) {
            TableColumn<ObservableList, String> col = new TableColumn<>(list[i].toString());
            final int I = i + 1;
            col.setCellValueFactory(params -> new SimpleStringProperty(params.getValue().get(I).toString()));
            tableView.getColumns().add(col);
        }

        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            ObservableList<Object> row = FXCollections.observableArrayList();
            row.add(entry.getKey());
            row.addAll(entry.getValue());
            observables.add(row);
            System.out.println(row.toString());
        }

        tableView.setItems(observables);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
