package backend;

import models.DateModel;

import java.sql.*;
import java.util.ArrayList;

public class DateDB {

    public DateDB() {
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

    public ArrayList<DateModel> read() {
        ArrayList<DateModel> list = new ArrayList<>();

        String query = "select * from date";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DateModel dateModel = new DateModel(
                        rs.getInt("dstudent_id"),
                        rs.getString("date"),
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

    public void insert(int studentID, String date, String attendance) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into date values (?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url,uname,pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,studentID);
        st.setString(2,date);
        st.setString(3,attendance);

        st.executeUpdate();

        st.close();
        con.close();
    }

}