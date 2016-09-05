package lib;

import java.sql.*;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ExistQuery extends SQLCommand{

	
		private String requestedField;
		private String tableName;
		private HashMap<String, String> conditions = new HashMap();
		
		public ExistQuery(String tableName, HashMap<String, String> conditions) {
			super("exist");
			this.tableName = tableName;
			this.conditions = conditions;
		}

		public String runLoginSearch(java.sql.Connection con, String username, String password) throws SQLException{
//			PreparedStatement usernameStatement =con.prepareStatement("SELECT * from user WHERE  email = ?");
//			usernameStatement.setString(1, username);
			PreparedStatement passwordStatement =con.prepareStatement("SELECT * from user WHERE  email = ? AND password = ?");
			passwordStatement.setString(1, username);
			passwordStatement.setString(2, password);
//			ResultSet rs = usernameStatement.executeQuery();
//			if (!rs.next())
//				return 1;
//			else {
//				rs = passwordStatement.executeQuery();
//				if (!rs.next())
//					return 2;
//				else 
//					return 3;
//			}
			ResultSet rs = passwordStatement.executeQuery();
			if (!rs.next())
				return "Incorrect Email or Password";
			else{
				User userA = new User(rs.getInt("userID"), rs.getString("email"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"), genderType.valueOf(rs.getString("gender")),
						rs.getDate("birthday"), rs.getBoolean("wantToPlay"),rs.getInt("userTypeID"),
						rs.getInt("curatorRequest"));
				if (userA.getUserTypeID() == 1) {	
					if (userA.getCuratorRequest() == 2)
						return "Curator not approved User";
					else return "Regular user";
				}
				else if (userA.getUserTypeID() == 2)
					return "Curator approved User";
			}
			return "Success";
		
		}
		
		public String getRequestedField() {
			return requestedField;
		}

		public void setRequestedField(String requestedField) {
			this.requestedField = requestedField;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public HashMap<String, String> getConditions() {
			return conditions;
		}

		public void setConditions(HashMap<String, String> conditions) {
			this.conditions = conditions;
		}
		

	}


