package database;

import java.sql.*;

public class BenchMarkDatabase {

    static Connection myConn;
    static Statement myStmt;

    public BenchMarkDatabase() throws SQLException {
        BenchMarkDatabase.myConn = DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net/sql11174282", "sql11174282", "B4u3INRmc4");
        BenchMarkDatabase.myStmt = BenchMarkDatabase.myConn.createStatement();
    }

    //Adds new record in table, takes database's table's columns as parameters
    public static void insertRow(String Nickname, String Drive_Type, String Laptop_Desktop_Model, Double Score) throws SQLException {
        PreparedStatement prStmt = myConn.prepareStatement("insert into benchmark_hdd (Nickname, Drive_Type, Laptop_Desktop_Model, Score) values (?, ?, ?, ?)");
        prStmt.setString(1, Nickname);
        prStmt.setString(2, Drive_Type);
        prStmt.setString(3, Laptop_Desktop_Model);
        prStmt.setDouble(4, Score);

        prStmt.executeUpdate();
    }

    public static void printRows() throws SQLException {
        String sql = "select * from benchmark_hdd";
        ResultSet myRS = myStmt.executeQuery(sql);
        while (myRS.next()) {
            System.out.println(myRS.getInt(1) + " " + myRS.getString(2) + " " + myRS.getString(3) + " " + myRS.getString(4) + " " + myRS.getDouble(5));
        }
    }

    //Displays database content into a JFrame as a JTable

    public static void displayDatabase() throws SQLException {
        new DatabaseTable().DisplayTable(myStmt);
    }

}
