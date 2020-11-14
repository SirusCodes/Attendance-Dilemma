package models;
import javax.swing.*;
public class Studentread {
    private String name, email ;

    public Studentread() {

    }
    public Studentread(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail(){return email;}
    public void setEmail(String Name){
    this.email=email;
    }

    @Override
    public String toString() {
        return "StudentRaw{" +
                "name='" + name + '\'' +
                "email="+email+
                '}';
    }
}

