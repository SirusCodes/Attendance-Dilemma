package backend;

import java.sql.*;

public class ClassDB {
   public ClassDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection con;
    private String userData;
    private String url = "jdbc:mysql://localhost:3306/attendancedilemma";
    private String uname = "root";
    private String pass = "1234";

    public void read()  {
        String query = "select * from class";

        try {
            con = DriverManager.getConnection(url, uname, pass);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                userData = rs.getInt(1) + " " + rs.getString(2);
                System.out.println(userData);
            }

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
