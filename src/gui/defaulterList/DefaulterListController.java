package gui.defaulterList;

import backend.DefaulterDB;
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
import models.DefaulterModel;
import observableModels.DefaulterObservable;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DefaulterListController implements Initializable {
    public Button backBtn;
    public TableView<DefaulterObservable> tableView;

    private TableColumn<DefaulterObservable, String> name;
    private TableColumn<DefaulterObservable, Double> percentage;

    private ArrayList<DefaulterObservable> observables = new ArrayList<>();

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
        DefaulterDB db = new DefaulterDB();
        ArrayList<DefaulterModel> defaulterList = db.defaulterlist(classID, Date.valueOf(startDate), Date.valueOf(endDate));
        observables.clear();

        defaulterList.forEach(model -> {
            observables.add(new DefaulterObservable(model));
        });
        setTableView();
    }

    public void setTableView() {
        final ObservableList<DefaulterObservable> data = FXCollections.observableArrayList(observables);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        tableView.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name = new TableColumn<>("Name");
        percentage = new TableColumn<>("Percentage");

        tableView.getColumns().addAll(name, percentage);
    }
}
