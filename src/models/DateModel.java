package models;

public class DateModel {
    private int studentId;
    private String date;
    private char attendance;

    public DateModel(int studentId, String date, char attendance) {
        this.studentId = studentId;
        this.date = date;
        this.attendance = attendance;
    }

    public DateModel() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public char getAttendance() {
        return attendance;
    }

    public void setAttendance(char attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "studentId=" + studentId +
                ", date='" + date + '\'' +
                ", attendance=" + attendance +
                '}';
    }
}
