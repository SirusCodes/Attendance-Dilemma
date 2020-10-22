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
    final private String uname = Auth.UNAME;
    final private String pass = Auth.PASSWORD;

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
                        rs.getString("last_datetime_added"),
                        rs.getBoolean("lab")
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

    public void update(ClassModel model) throws ClassNotFoundException, SQLException {
        String query = "update class set no_of_lecture="+model.getNoOfLectures()+" last_datetime_added="+model.getDateTime()+" where class_id="+model.getClassId();

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url,uname,pass);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        st.close();
        con.close();

    }

    public void insert(ClassModel model) throws SQLException, ClassNotFoundException {
        //int classId,noOfLectures;
        //String branch,year,batch,dateTime;

        String query = "insert into class values (?,?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(url,uname,pass);

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,model.getClassId());
        st.setString(2,model.getBranch());
        st.setString(3,model.getYear());
        st.setString(4,model.getBatch());
        st.setInt(5,model.getNoOfLectures());
        st.setString(6,model.getDateTime());
        st.setBoolean(7,model.getLab());

        st.executeUpdate();

        st.close();
        con.close();
    }
}
