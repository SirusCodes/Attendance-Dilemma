package models;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Date;

public class StudentRaw {
    private String name, userAction;
    private LocalDateTime timestamp;

    public StudentRaw() {

    }

    public StudentRaw(String name, String userAction, LocalDateTime timestamp) {
        this.name = name;
        this.userAction = userAction;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "StudentRaw{" +
                "name='" + name + '\'' +
                ", userAction='" + userAction + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
