package database;

import java.sql.SQLException;

public class Client {

	public static void main(String[] args) throws SQLException {
		new BenchMarkDatabase();
		BenchMarkDatabase.insertRow("Nadina", "SSD", "Asus Zenbook", 65.55);
		System.out.println("Record added..");
		BenchMarkDatabase.printRows();
		System.out.println("Records printed..");
	}

}
