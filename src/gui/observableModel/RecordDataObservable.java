package gui.observableModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RecordDataObservable {
    final StringProperty startTime, endTime, fileAddress;
    final IntegerProperty minDuration;

    public RecordDataObservable(String startTime, String endTime, StringProperty fileAddress, int minDuration) {
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.fileAddress = fileAddress;
        this.minDuration = new SimpleIntegerProperty(minDuration);
    }

    public RecordDataObservable() {
        this.startTime = new SimpleStringProperty();
        this.endTime = new SimpleStringProperty();
        this.minDuration = new SimpleIntegerProperty(75);
        this.fileAddress = new SimpleStringProperty("Select the file location --->");
    }

    public String getFileAddress() {
        return fileAddress.get();
    }

    public StringProperty fileAddressProperty() {
        return fileAddress;
    }

    public String getStartTime() {
        return startTime.get();
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public String getEndTime() {
        return endTime.get();
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public int getMinDuration() {
        return minDuration.get();
    }

    public IntegerProperty minDurationProperty() {
        return minDuration;
    }

    @Override
    public String toString() {
        return "RecordDataObservable{" +
                "startTime=" + getStartTime() +
                ", endTime=" + getEndTime() +
                ", minDuration=" + getMinDuration() +
                '}';
    }
}
