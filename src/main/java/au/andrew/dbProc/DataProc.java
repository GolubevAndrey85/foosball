package au.andrew.dbProc;


import java.sql.*;

public class DataProc {
    private static final String url = "jdbc:mysql://localhost:3306/foosball"; //192.168.18.245
    private static final String user = "root";
    private static final String password = "root";//"my5UsOexGn";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    private Connection getCon(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void conClose(){
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getData(String query){
        try {
            con = getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void putData(String query){
        try {
            con = getCon();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            preparedStmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
