package lib;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import com.mysql.jdbc.Connection;

public class ConnectivityTest {
	public static void main(String [] args) throws ClassNotFoundException, SQLException, ParseException
	{
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://libarab.crbu6i1ygais.eu-central-1.rds.amazonaws.com:3306/libArabDB" ;
	//	jdbc:mysql://myRDS.cuc1hj6ibtko.eu-west-1.rds.amazonaws.com:3306/myDB?" +
       //     "user=MyUSER&password=MyPASSWORD"
		java.sql.Connection con=null;
		Statement stmt = null;
		
		// Register JDBC Driver
		Class.forName("com.mysql.jdbc.Driver"); //register with DriverManager
		
		
		
		//Open Connection
		try{
		System.out.println("Connecting to database....");
		con = DriverManager.getConnection(url, "MasterDB", "Master!!");
		}
		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		HashMap<String, String> conditions = new HashMap();
		conditions.put("username", "mothana");
		conditions.put("password", "123456");
		//Execute a query
		System.out.println("Creating database....");
		ExistQuery Q = new ExistQuery("user",conditions);
		String reply ="";
		String name = "";
		
		ReturnAllUsers r = new ReturnAllUsers();
		ReturnAllCurators g = new ReturnAllCurators();
		
		ArrayList<String> answer= Q.runLoginSearch(con, "mothana@amaria", "123456");
		ArrayList<String> answerB= Q.runLoginSearch(con, "eyal@rosenbaum", "123456");
		ArrayList<String> answerC= Q.runLoginSearch(con, "c@c", "a");
		ArrayList<String> answerD= Q.runLoginSearch(con, "c@a", "a");
		ArrayList<User> answerE = r.returnAll(con);
		ArrayList<User> answerF = g.returnAll(con);
		
		System.out.println(answer);
		System.out.println(answerB);
		System.out.println(answerC);
		System.out.println(answerD);
		System.out.println("All of the users:");
		for (User user:answerE){
			System.out.println(user.getFirstName()+" "+user.getLastName());
		}
		System.out.println("All of the curators:");
		for (User user:answerF){
			System.out.println(user.getFirstName()+" "+user.getLastName());
		}
		
		
//		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
//		String input = "1983-06-11";
//		lib.genderType gen = genderType.m;
//		java.util.Date date = ft.parse(input);
//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//		InsertQuery A = new InsertQuery("user");
//		boolean answer2 = A.SignUp(con,"charlie", "a", "b", "c", gen,sqlDate , true, 1, 1);
//		System.out.println("the answer to the signup function is "+answer2);
//		stmt= con.createStatement();
//
//		User mothana = null;
//		
//		ResultSet rs = stmt.executeQuery("select * from user");
//		if (!rs.next())
//			System.out.println("result set empty!!!");
//		else
//			while (rs.next()){
//				System.out.println("1");
//			mothana = new User(rs.getInt("userID"), rs.getString("email"), rs.getString("password"),
//					rs.getString("firstName"), rs.getString("lastName"), null,
//					rs.getDate("birthday"), rs.getBoolean("wantToPlay"),rs.getInt("userTypeID"),
//					rs.getInt("curatorRequest"));
//			System.out.println(rs.getInt("userID")+rs.getString("email"));
//		}
//		
//		
//		rs.close();
//		stmt.close();
// 
//		if (mothana != null){
//		System.out.println(mothana.getEmail()+" "+mothana.getFirstName()+" "+ mothana.getLastName()+" "+
//				mothana.getUserID());
//		}
//		else System.out.println("finished");
	}
}
