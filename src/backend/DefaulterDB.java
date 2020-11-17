package backend;

import models.DefaulterModel;

import java.sql.*;
import java.util.ArrayList;


public class DefaulterDB {

    final private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    final private String uname = Auth.UNAME;
    final private String pass = Auth.PASSWORD;
    private Connection con;
    public DefaulterDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DefaulterModel> defaulterlist(int classID, Date startDate, Date endDate) {
        ArrayList<DefaulterModel> list = new ArrayList<>();

        String query = "select s.student_name,(count(s.student_name)*100/c.no_of_lecture) as percentage from student s,date d,class c where d.date between " + startDate + " and " + endDate + " and c.class_id=" + classID + " and attendance like 'P' group by student_name having percentage<=75";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DefaulterModel defaulterModel = new DefaulterModel(
                        rs.getString("student_name"),
                        rs.getDouble("percentage")
                );
                list.add(defaulterModel);
            }

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}