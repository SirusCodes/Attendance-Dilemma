package models;

public class StudentModel {
    private int classId;
    private String studentName,studentId;

    public StudentModel(String studentId, int classId, String studentName) {
        this.studentId = studentId;
        this.classId = classId;
        this.studentName = studentName;
    }

    public StudentModel() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
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
