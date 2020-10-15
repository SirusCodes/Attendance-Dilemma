package models;

public class MonthModel {
    private int studentId,noOfLectures,month;

    public MonthModel(int studentId, int noOfLectures, int month) {
        this.studentId = studentId;
        this.noOfLectures = noOfLectures;
        this.month = month;
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

    public void setNoOfLectures(int noOfLectures) {
        this.noOfLectures = noOfLectures;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "MonthModel{" +
                "studentId=" + studentId +
                ", noOfLectures=" + noOfLectures +
                ", month=" + month +
                '}';
    }
}
