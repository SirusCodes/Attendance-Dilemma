package backend;

import models.ShowRecordsModel;

import java.sql.*;
import java.util.ArrayList;

public class ShowRecordsDB {
    public ShowRecordsDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection con;
    final private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    final private String uname = Auth.UNAME;
    final private String pass = Auth.PASSWORD;

    public ArrayList<ShowRecordsModel> read(Date date1,Date date2,int classID) {
        ArrayList<ShowRecordsModel> list = new ArrayList<>();

        String query = "select s.student_name,d.date,d.attendance from student s,date d where d.date between "+date1+" and "+date2+" and d.dstudent_id=s.student_id and s.sclass_id="+classID+" order by d.date ASC,s.student_id ASC";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                ShowRecordsModel showRecordsModel = new ShowRecordsModel(
                        rs.getString("student_name"),
                        rs.getDate("date"),
                        rs.getString("attendance")
                );
                list.add(showRecordsModel);
            }

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

}
