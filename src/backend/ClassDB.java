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

    public void insert(int classId, String branch, String year, String batch, int noOfLectures, String dateTime) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into class values (?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url,uname,pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,classId);
        st.setString(2,branch);
        st.setString(3,year);
        st.setString(4,batch);
        st.setInt(5,noOfLectures);
        st.setString(6,dateTime);

        st.executeUpdate();

        st.close();
        con.close();
    }
}
