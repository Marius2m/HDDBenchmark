package database;

import java.awt.GridLayout;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;


@SuppressWarnings("serial")
class ResultsFrame extends JFrame {

    public ResultsFrame(ResultSet myRS) throws IOException, SQLException {
        while (myRS.next()) {
            addEntry(myRS);
        }

        setTitle("Benchmark Results");
        setLayout(new GridLayout(20, 2, 20, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    void addEntry(ResultSet myRS) throws SQLException {
        add(new JLabel("" + myRS.getString("Nickname")));
        add(new JLabel("" + myRS.getString("Laptop_Desktop_Model")));
    }
}


public class Driver {

    //Adds new record in table, takes database's table's columns as parameters
    public static void insertRow(String Nickname, String Drive_Type, String Laptop_Desktop_Model, Double Score, Connection myConn) throws SQLException {
        PreparedStatement insertRow = myConn.prepareStatement("insert into benchmark_hdd (Nickname, Drive_Type, Laptop_Desktop_Model, Score) values (?, ?, ?, ?)");
        insertRow.setString(1, Nickname);
        insertRow.setString(2, Drive_Type);
        insertRow.setString(3, Laptop_Desktop_Model);
        insertRow.setDouble(4, Score);

        insertRow.executeUpdate();
    }

    public static void main(String[] args) {
        try {
            //1. Get a connection to the database
            Connection myConn = DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net/sql11174282", "sql11174282", "B4u3INRmc4");

            //2. Create a statement
            Statement myStat = myConn.createStatement();

				/*String sql = "insert into employee "
							+"values " 
							+ "(13, 'Tom Hanks', 65, 'tomhanks@gmail.com', 1000000);";
				String deleteRow = "delete from employee "
								  + "where id = 13;";
				//myStat.executeUpdate(sql);
				//myStat.executeUpdate(deleteRow);*/

            //3. Execute SQL query

            insertRow("John", "SSD", "Lenovo Viper+", 79.5, myConn);

            ResultSet myRS = myStat.executeQuery("select * from benchmark_hdd");
            new ResultsFrame(myRS);
            //4. Process result set
            while (myRS.next()) {
                System.out.println(myRS.getString("UserName") + "  " + myRS.getString("LaptopModel") + "," + myRS.getString("Score"));
            }


            //5. Prepared statements

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
