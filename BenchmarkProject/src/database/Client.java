import java.sql.SQLException;

public class Client {

	public static void main(String[] args) throws SQLException {
		new BenchMarkDatabase();
		BenchMarkDatabase.insertRow("Popescu", "HDD", "Asus Brutus", 88.9);
		BenchMarkDatabase.displayDatabase();
		//updates:dynamic table search + column header click-sort
		

	}

}
