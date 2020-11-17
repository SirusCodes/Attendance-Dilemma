package observableModels;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.DefaulterModel;

public class DefaulterObservable {
    private final StringProperty name;
    private final DoubleProperty percentage;

    public DefaulterObservable(String name, Double percentage) {
        this.name = new SimpleStringProperty(name);
        this.percentage = new SimpleDoubleProperty(percentage);
    }

    public DefaulterObservable() {
        this.name = new SimpleStringProperty();
        this.percentage = new SimpleDoubleProperty();
    }

    public DefaulterObservable(DefaulterModel model) {
        this.name = new SimpleStringProperty(model.getName());
        this.percentage = new SimpleDoubleProperty(model.getPercentage());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPercentage() {
        return percentage.get();
    }

    public void setPercentage(double percentage) {
        this.percentage.set(percentage);
    }

    public DoubleProperty percentageProperty() {
        return percentage;
    }
}
