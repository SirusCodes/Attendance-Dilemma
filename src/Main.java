import backend.ClassDB;
import models.ClassModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ClassDB db = new ClassDB();
        ArrayList<ClassModel> list = db.read();
        list.forEach(System.out::println);

        db.insert(5,"IT","SE","S2",9,"2020-10-12");
    }
}
