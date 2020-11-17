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

        String query = "select s.student_name,(count(d.dstudent_id)*100/c.no_of_lecture) as percentage from date d,student s,class c where d.date between '" + startDate +
                "' and '" + endDate + "' and s.sclass_id=" + classID + " and s.student_id=d.dstudent_id and c.class_id=s.sclass_id and attendance like 'A' group by s.student_name having percentage>25;";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                DefaulterModel defaulterModel = new DefaulterModel(
                        rs.getString("student_name"),
                        100 - rs.getDouble("percentage")
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