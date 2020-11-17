package backend;

import models.DateModel;

import java.sql.*;
import java.util.ArrayList;

public class DateDB {

    final private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    final private String uname = Auth.UNAME;
    final private String pass = Auth.PASSWORD;
    private Connection con;
    public DateDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DateModel> read() {
        ArrayList<DateModel> list = new ArrayList<>();

        String query = "select * from date";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DateModel dateModel = new DateModel(
                        rs.getString("dstudent_id"),
                        rs.getDate("date"),
                        rs.getString("attendance")
                );
                list.add(dateModel);
            }

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public void insert(DateModel model) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into date values (?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url, uname, pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, model.getStudentId());
        st.setDate(2, model.getDate());
        st.setString(3, model.getAttendance());

        st.executeUpdate();

        st.close();
        con.close();
    }

}