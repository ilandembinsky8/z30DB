package lib;

import java.sql.*;
import java.util.Date;

import com.mysql.jdbc.Connection;


public class InsertQuery extends SQLCommand{

	private String tableName;
	
	public InsertQuery(String tableName) {
		super("insert");
		this.tableName = tableName;
		// TODO Auto-generated constructor stub
	}
	
	public Boolean SignUp(java.sql.Connection con,String email, String password, String firstName, String LastName, 
			genderType gender, Date date, boolean wantToPlay, int userTypeID, int curatorRequest) throws SQLException{
//		if ((email == null)||(email.length() == 0)||(password == null)||(password.length() == 0)||
//				(firstName == null)||(firstName.length() == 0)||(LastName == null)||(LastName.length() == 0)
//				)
//			return false;
		User newUser = new User(1,email,password,firstName,LastName,gender,date,wantToPlay,userTypeID,
				curatorRequest);
		char wantPlay;
		if (wantToPlay)
			wantPlay='Y';
		else wantPlay = 'N';
		Statement stmt = con.createStatement();
		String insert = "INSERT INTO user (email, password, firstName, lastName, gender, birthday, wantToPlay,"
				+ "userTypeID,curatorRequest) VALUES('"+email+"', '"+password+"', '"+firstName+"', '"+LastName+"', '"+gender+"', '"+
				date+"', '"+wantPlay+"', "+userTypeID+", "+curatorRequest+")";
		String insert2 = "INSERT INTO user (email, password, firstName, lastName, gender, birthday, wantToPlay,"
				+ "userTypeID,curatorRequest) VALUES(rosenbaum@eyal, 123456, eyal, rosenbaum, m, 1983-06-11, true, 1,1";
		stmt.executeUpdate(insert);
		
		PreparedStatement passwordStatement =con.prepareStatement("SELECT * from user WHERE  email = ? AND password = ?");
		passwordStatement.setString(1, email);
		passwordStatement.setString(2, password);
		ResultSet rs = passwordStatement.executeQuery();
		if (!rs.next()){
			return false;
		}
		else return true;

	}

}
