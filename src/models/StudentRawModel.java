package models;

import java.time.LocalDateTime;

public class StudentRawModel {
    private String name, status;
    private LocalDateTime dateTime;

    public StudentRawModel(String name, String status, LocalDateTime dateTime) {
        this.name = name;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "StudentRawModel{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
