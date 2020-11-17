package models;

import java.sql.Date;

public class ShowRecordsModel {
    private String studentName, attendance;
    private Date date;

    public ShowRecordsModel() {
    }

    public ShowRecordsModel(String studentName, Date date, String attendance) {
        this.studentName = studentName;
        this.attendance = attendance;
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShowRecordsModel{" +
                "studentName='" + studentName + '\'' +
                ", attendance='" + attendance + '\'' +
                ", date=" + date +
                '}';
    }
}
