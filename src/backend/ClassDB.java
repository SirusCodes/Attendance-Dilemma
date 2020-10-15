package backend;

import models.ClassModel;

import java.sql.*;
import java.util.ArrayList;

public class ClassDB {
    public ClassDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection con;
    private String userData;
    private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    private String uname = "root";
    private String pass = "1234";

    public ArrayList<ClassModel> read() {
        ArrayList<ClassModel> list = new ArrayList<>();

        String query = "select * from class";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                ClassModel classModel = new ClassModel(rs.getInt("class_id"),
                        rs.getString("branch"),
                        rs.getString("year"),
                        rs.getString("batch"),
                        rs.getInt("no_of_lecture"),
                        rs.getString("last_datetime_added")
                );
                list.add(classModel);
            }

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }


}
