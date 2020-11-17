package backend;

import models.StudentModel;

import java.sql.*;
import java.util.ArrayList;

public class StudentDB {

    final private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    final private String uname = Auth.UNAME;
    final private String pass = Auth.PASSWORD;
    private Connection con;
    public StudentDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<StudentModel> read(int classID) {
        ArrayList<StudentModel> list = new ArrayList<>();

        String query = "select student_id,sclass_id,student_name from student where s.sclass_id=" + classID;

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                StudentModel studentModel = new StudentModel(
                        rs.getString("student_id"),
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

    public void insert(StudentModel model) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into student values (?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url, uname, pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, model.getStudentId());
        st.setInt(2, model.getClassId());
        st.setString(3, model.getStudentName());

        st.executeUpdate();

        st.close();
        con.close();
    }

}
