package lib;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import com.mysql.jdbc.Connection;

public class ConnectivityTest {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, ParseException {
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://libarab.crbu6i1ygais.eu-central-1.rds.amazonaws.com:3306/libArabDB";
		String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
		java.sql.Connection con = null;
		Statement stmt = null;

		// Register JDBC Driver
		Class.forName("com.mysql.jdbc.Driver"); // register with DriverManager

		// Open Connection
		try {
			System.out.println("Connecting to database....");
			con = DriverManager.getConnection(url + unicode, "MasterDB",
					"Master!!");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		System.out.println("Creating database....");
		 UpdateCommand a = new UpdateCommand();
		 System.out.println(a.addQuestion(con, "dror@d", "qus", "ans1",
		 "ans2", "ans3", "ans4", "123","yosi","history"));
		SelectCommand b = new SelectCommand();
		// ArrayList<SearchItem> a = b.search(con, 0);
		// for (int i=0; i< a.size();i++)
		// System.out.println("name: "+a.get(i));
	//	ArrayList<String> a = b.returnTop10(con, "dror@d");
	//	System.out.println(a);


			
	}
}
