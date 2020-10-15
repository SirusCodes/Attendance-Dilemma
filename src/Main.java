import backend.ClassDB;
import models.ClassModel;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ClassDB db = new ClassDB();
        ArrayList<ClassModel> list = db.read();

        list.forEach(System.out::println);
    }
}
