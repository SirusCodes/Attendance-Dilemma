package models;

import java.sql.Date;
import java.util.Objects;

public class ClassModel {
    private int classId, noOfLectures;
    private String branch, year, batch, subBatch;
    private Date dateTime;
    private Boolean lab;

    public ClassModel(int classId, String branch, String year, String batch, String subBatch, int noOfLectures, Date dateTime, Boolean lab) {
        this.classId = classId;
        this.noOfLectures = noOfLectures;
        this.branch = branch;
        this.year = year;
        this.batch = batch;
        this.subBatch = subBatch;
        this.dateTime = dateTime;
        this.lab = lab;
    }

    public ClassModel(int noOfLectures, String branch, String year, String batch, String subBatch, Date dateTime, Boolean lab) {
        this.noOfLectures = noOfLectures;
        this.branch = branch;
        this.year = year;
        this.batch = batch;
        this.subBatch = subBatch;
        this.dateTime = dateTime;
        this.lab = lab;
    }

    public ClassModel() {
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getNoOfLectures() {
        return noOfLectures;
    }

    public void setNoOfLectures(int noOfLectures) {
        this.noOfLectures = noOfLectures;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSubBatch() {
        return subBatch;
    }

    public void setSubBatch(String subBatch) {
        this.subBatch = subBatch;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getLab() {
        return lab;
    }

    public void setLab(Boolean lab) {
        this.lab = lab;
    }

    @Override
    public String toString() {
        return "ClassModel{" +
                "classId=" + classId +
                ", noOfLectures=" + noOfLectures +
                ", branch='" + branch + '\'' +
                ", year='" + year + '\'' +
                ", batch='" + batch + '\'' +
                ", subBatch='" + subBatch + '\'' +
                ", dateTime=" + dateTime +
                ", lab=" + lab +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassModel that = (ClassModel) o;
        return classId == that.classId &&
                noOfLectures == that.noOfLectures &&
                branch.equals(that.branch) &&
                year.equals(that.year) &&
                batch.equals(that.batch) &&
                dateTime.equals(that.dateTime) &&
                lab.equals(that.lab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, noOfLectures, branch, year, batch, dateTime, lab);
    }
}
