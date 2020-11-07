package gui.observableModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentRawModel {
    private StringProperty email, fname, lname;

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty fnameProperty() {
        return fname;
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public StudentRawModel(String email, String fname, String lname) {
        this.email = new SimpleStringProperty(email);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
    }

    public StudentRawModel() {
        this.email = new SimpleStringProperty("");
        this.fname = new SimpleStringProperty("");
        this.lname = new SimpleStringProperty("");
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getFname() {
        return fname.get();
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    @Override
    public String toString() {
        return "StudentRawModel{" +
                "email='" + getEmail() + '\'' +
                ", fname='" + getFname() + '\'' +
                ", lname='" + getLname() + '\'' +
                '}';
    }
}
