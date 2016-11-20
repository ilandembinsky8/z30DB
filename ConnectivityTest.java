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
//		 UpdateCommand a = new UpdateCommand();
//		 System.out.println(a.addQuestion(con, "dror@d", "qus", "ans1",
//		 "ans2", "ans3", "ans4", "123","yosi","history"));
		SelectCommand b = new SelectCommand();
		// ArrayList<SearchItem> a = b.search(con, 0);
		// for (int i=0; i< a.size();i++)
		// System.out.println("name: "+a.get(i));
	//	ArrayList<String> a = b.returnTop10(con, "dror@d");
	//	System.out.println(a);
//	ArrayList<Favorite> myfavs=b.getfavOfBib(con, "cat@root", TypeOfResource.BOOK, 0);
//		for ( Favorite fv:myfavs)
//			System.out.println(fv);
//            
//		boolean addthis=a.addFavorite(con, "cat@root", "book233", TypeOfResource.BOOK, "my@book", 4, "mydesc", 0);
//		System.out.println("priiiiiiiiiint this");
//		System.out.println(addthis);
//		Date searchDate = new Date();
//		ArrayList<SearchNum> answer = b.getSearchNumber(con, searchDate);
		
		
//		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
//		String input = "1983-06-11";
//		java.util.Date date = ft.parse(input);
//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
	UpdateCommand a = new UpdateCommand();
	SelectCommand SC=new SelectCommand();
//		a.addFavorite(con, "dror@d" , "NNL_ALEPH000400204", TypeOfResource.BOOK, "http://iiif.nli.org.il/IIIF/FL31351509/full/full/0/default.jpg",
//				18, "This is the Story of Prince Of Bel-Air", 0, "بلغة يونيكود", "بلغة يونيكود", "بلغة يونيكود", "2015-10-10", "bla bla","the source");
		ArrayList<Favorite> answers = b.getfavOfBib(con,"cat@root",  TypeOfResource.BOOK, 787);
		
		for (Favorite g: answers){
			System.out.println(g.getBookID()+", "+ g.getDescription()+",  "+g.getSource());
		}
			System.out.println("Diiiiiiid question was added?");
		//	boolean addQ = a.addQuestion(con, "diababeer3@gmail.com", "questuion", "answer1", "answer2", "answer3", "answer4", "123", "author", "history", "Mr who", "10/03/1995","aaaaa");
		//	System.out.println(addQ);
		
			ArrayList<Item> items =	SC.returnItemsOfQuestions(con);
			for (Item myI : items)
			{
				System.out.println(myI);
			}
	}
}
