package backend;

import models.ClassModel;
import models.StudentModel;

import java.sql.*;
import java.util.ArrayList;

public class StudentDB {

    public StudentDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection con;
    final private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    final private String uname = "root";
    final private String pass = "1234";

    public ArrayList<StudentModel> read() {
        ArrayList<StudentModel> list = new ArrayList<>();

        String query = "select * from student";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                StudentModel studentModel = new StudentModel(
                        rs.getInt("student_id"),
                        rs.getInt("sclass_id"),
                        rs.getString("student_name")
                );
                list.add(studentModel);
            }

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public void insert(int studentID, int sclassID, String studentName) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into student values (?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url,uname,pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,studentID);
        st.setInt(2,sclassID);
        st.setString(3,studentName);

        st.executeUpdate();

        st.close();
        con.close();
    }

}
