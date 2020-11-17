package observableModels;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class RecordObservable {
    StringProperty name;
    ListProperty<String> attendance;

    public RecordObservable(String name, ArrayList<String> attendance) {
        this.name = new SimpleStringProperty(name);
        this.attendance = new SimpleListProperty<>(FXCollections.observableList(attendance));
    }

    public RecordObservable() {
        this.name = new SimpleStringProperty();
        this.attendance = new SimpleListProperty<>();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableList<String> getAttendance() {
        return attendance.get();
    }

    public ListProperty<String> attendanceProperty() {
        return attendance;
    }

    public void setAttendance(ObservableList<String> attendance) {
        this.attendance.set(attendance);
    }

    @Override
    public String toString() {
        return "RecordObservable{" +
                "name=" + getName() +
                ", attendance=" + getAttendance() +
                '}';
    }
}
