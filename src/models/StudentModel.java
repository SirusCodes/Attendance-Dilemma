package models;

public class StudentModel {
    private int studentId,classId;
    private String studentName;

    public StudentModel(int studentId, int classId, String studentName) {
        this.studentId = studentId;
        this.classId = classId;
        this.studentName = studentName;
    }

    public StudentModel() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentId=" + studentId +
                ", classId=" + classId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
