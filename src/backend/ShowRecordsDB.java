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

    public ArrayList<ShowRecordsModel> read() {
        ArrayList<ShowRecordsModel> list = new ArrayList<>();

        String query = "select * from class";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                ShowRecordsModel showRecordsModel = new ShowRecordsModel(
                        rs.getString("studentName"),
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
