package lib;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ExistQuery extends SQLCommand{

	
		private String requestedField;
		private HashMap<String, String> conditions = new HashMap();
		
		public ExistQuery(String tableName, HashMap<String, String> conditions) {
			super("exist");
			this.conditions = conditions;
		}

		public ArrayList<String> runLoginSearch(java.sql.Connection con, String username, String password) throws SQLException{
//			PreparedStatement usernameStatement =con.prepareStatement("SELECT * from user WHERE  email = ?");
//			usernameStatement.setString(1, username);
			PreparedStatement passwordStatement =con.prepareStatement("SELECT * from user WHERE  email = ? AND password = ?");
			passwordStatement.setString(1, username);
			passwordStatement.setString(2, password);
			ArrayList<String> replyList = new ArrayList<String>();
			String replyClient = "fail";
			String userNameReply ="";
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
			//in case no match was found
			if (!rs.next()){
				replyList.add("Incorrect Email or Password");
				replyList.add(replyClient);
				replyList.add("");
				return replyList;
			}
			else{
				User userA = new User(rs.getInt("userID"), rs.getString("email"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"), genderType.valueOf(rs.getString("gender")),
						rs.getDate("birthday"), rs.getBoolean("wantToPlay"),rs.getInt("userTypeID"),
						rs.getInt("curatorRequest"));
				userNameReply = userA.getFirstName()+" "+userA.getLastName();
				replyClient = "success";
				if (userA.getUserTypeID() == 1) {	
					if (userA.getCuratorRequest() == 2){
						replyList.add("Curator not approved User");
						replyList.add(replyClient);
						replyList.add(userNameReply);
						return replyList;
					}
					else {
						replyList.add("Regular user");
						replyList.add(replyClient);
						replyList.add(userNameReply);
						return replyList;
					}
				}
				else if (userA.getUserTypeID() == 2){
					replyList.add("Curator approved User");
					replyList.add(replyClient);
					replyList.add(userNameReply);
					return replyList;
				}
				else if (userA.getUserTypeID() ==3 ){
					replyList.add("Admin");
					replyList.add(replyClient);
					replyList.add(userNameReply);
					return replyList;
				}
			}
			return null;
		
		}
		
		public String getRequestedField() {
			return requestedField;
		}

		public void setRequestedField(String requestedField) {
			this.requestedField = requestedField;
		}


		public HashMap<String, String> getConditions() {
			return conditions;
		}

		public void setConditions(HashMap<String, String> conditions) {
			this.conditions = conditions;
		}
		

	}


