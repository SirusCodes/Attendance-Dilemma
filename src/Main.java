import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/attendancedilemma";
        String uname = "root";
        String pass = "1234";
        String userData;

        String query = "select * from class";

        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection(url,uname,pass);

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            userData = rs.getInt(1) + " " + rs.getString(2);
            System.out.println(userData);
        }

        st.close();
        con.close();
    }
}
