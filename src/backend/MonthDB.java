package backend;

import models.MonthModel;

import java.sql.*;
import java.util.ArrayList;

public class MonthDB {

    public MonthDB() {
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

    public ArrayList<MonthModel> read() {
        ArrayList<MonthModel> list = new ArrayList<>();

        String query = "select * from month";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                MonthModel monthModel = new MonthModel(
                        rs.getInt("mstudent_id"),
                        rs.getInt("month"),
                        rs.getInt("number_of_attended_lecture")
                );
                list.add(monthModel);
            }

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public void insert(MonthModel model) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into month values (?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url,uname,pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,model.getStudentId());
        st.setInt(2,model.getMonth());
        st.setInt(3,model.getNoOfLectures());

        st.executeUpdate();

        st.close();
        con.close();
    }

}
