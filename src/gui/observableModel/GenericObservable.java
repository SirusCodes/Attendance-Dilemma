package gui.observableModel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class GenericObservable {
    private ListProperty<Integer> id;
    private ListProperty<String> list;

    public ListProperty<Integer> idProperty() {
        return id;
    }

    public ListProperty<String> listProperty() {
        return list;
    }

    public GenericObservable(ArrayList<String> list, ArrayList<Integer> id) {
        this.list = new SimpleListProperty<>(FXCollections.observableList(list));
        this.id = new SimpleListProperty(FXCollections.observableList(id));
    }

    public ArrayList<String> getList() {

        return new ArrayList<>(list.get());
    }

    public void setList(ArrayList<String> list) {
        this.list.addAll(list);
    }

    public ArrayList<Integer> getId() {
        return new ArrayList<>(id.get());
    }

    public void setId(ArrayList<Integer> id) {
        this.id.addAll(id);
    }
}
