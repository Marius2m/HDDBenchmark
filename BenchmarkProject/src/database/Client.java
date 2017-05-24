import java.sql.SQLException;

public class Client {

	public static void main(String[] args) throws SQLException {
		new BenchMarkDatabase();
		BenchMarkDatabase.insertRow("Stefanov", "Google Chromebook 2017 Nice Edition", "SSD", "Windows 10", 899.359);
		BenchMarkDatabase.displayDatabase();
		

	}

}
