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
    final private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    final private String uname = "root";
    final private String pass = "1234";

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

    public void insert(ClassModel model) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into class values (?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url,uname,pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,model.getClassId());
        st.setString(2,model.getBranch());
        st.setString(3,model.getYear());
        st.setString(4,model.getBatch());
        st.setInt(5,model.getNoOfLectures());
        st.setString(6,model.getDateTime());

        st.executeUpdate();

        st.close();
        con.close();
    }
}
