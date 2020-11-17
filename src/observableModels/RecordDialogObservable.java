package observableModels;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RecordDialogObservable {
    final StringProperty startTime, endTime, fileAddress;
    final DoubleProperty minDuration;

    public RecordDialogObservable(String startTime, String endTime, StringProperty fileAddress, int minDuration) {
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.fileAddress = fileAddress;
        this.minDuration = new SimpleDoubleProperty(minDuration);
    }

    public RecordDialogObservable() {
        this.startTime = new SimpleStringProperty();
        this.endTime = new SimpleStringProperty();
        this.minDuration = new SimpleDoubleProperty(75);
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

    public double getMinDuration() {
        return minDuration.get();
    }

    public DoubleProperty minDurationProperty() {
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
