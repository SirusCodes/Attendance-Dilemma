package models;

public class MonthModel {
    private int studentId,noOfLectures,month;

    public MonthModel(int studentId, int month, int noOfLectures) {
        this.studentId = studentId;
        this.month = month;
        this.noOfLectures = noOfLectures;
    }

    public MonthModel() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getNoOfLectures() {
        return noOfLectures;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setNoOfLectures(int noOfLectures) {
        this.noOfLectures = noOfLectures;
    }

    @Override
    public String toString() {
        return "MonthModel{" +
                "studentId=" + studentId +
                ", month=" + month +
                ", noOfLectures=" + noOfLectures +
                '}';
    }
}
