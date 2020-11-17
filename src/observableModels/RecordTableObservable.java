package observableModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RecordTableObservable {
    private final StringProperty email, name, status;
    private final IntegerProperty duration;

    public RecordTableObservable(String email, String name, String status, Integer duration) {
        this.email = new SimpleStringProperty(email);
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status);
        this.duration = new SimpleIntegerProperty(duration);
    }

    public RecordTableObservable() {
        this.email = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.duration = new SimpleIntegerProperty();
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
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

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public int getDuration() {
        return duration.get();
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public IntegerProperty durationProperty() {
        return duration;
    }
}
