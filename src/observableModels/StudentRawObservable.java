package observableModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class StudentRawObservable {
    final private StringProperty email, fname, lname;

    public StudentRawObservable(String email, String fname, String lname) {
        this.email = new SimpleStringProperty(email);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
    }

    public StudentRawObservable() {
        this.email = new SimpleStringProperty("");
        this.fname = new SimpleStringProperty("");
        this.lname = new SimpleStringProperty("");
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty fnameProperty() {
        return fname;
    }

    public StringProperty lnameProperty() {
        return lname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRawObservable that = (StudentRawObservable) o;
        return email.get().equals(that.email.get()) &&
                fname.get().equals(that.fname.get()) &&
                lname.get().equals(that.lname.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.get(), fname.get(), lname.get());
    }
}
