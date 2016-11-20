package lib;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpdateCommand extends SQLCommand {

	public UpdateCommand() {
		super("update");
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Boolean signUp(java.sql.Connection con,String email, String
	 * password, String firstName, String LastName, genderType gender, Date
	 * date, String userType) throws SQLException{ if ((email ==
	 * null)||(email.length() == 0)||(password == null)||(password.length() ==
	 * 0)|| (firstName == null)||(firstName.length() == 0)||(LastName ==
	 * null)||(LastName.length() == 0) ) return false; else{ SelectCommand
	 * helper = new SelectCommand(); // ArrayList<String> answer =
	 * helper.runLoginSearch(con, email, password); // if
	 * (answer.get(1).equals("success") ){ boolean exists= helper.userExists(con
	 * , email); if(exists) { // System.out.println(" \n  \n " + answer.get(1)
	 * +"the Problem is here!!!"); return false; } int curatorRequest = 1; if
	 * (userType.equals("Curator") ) curatorRequest = 2; User newUser = new
	 * User(1,email,password,firstName,LastName,gender,date,true,userType,
	 * curatorRequest); char wantPlay = 'Y';
	 * 
	 * Statement stmt = con.createStatement(); String insert =
	 * "INSERT INTO user (email, password, firstName, lastName, gender, birthday, wantToPlay,"
	 * + "userTypeID,curatorRequest) VALUES('"+email+"', '"+password+"', '"
	 * +firstName +"', '"+LastName+"', '"+gender+"', '"+
	 * date+"', '"+wantPlay+"', 1,"+curatorRequest+")"; // String insert2 =
	 * "INSERT INTO user (email, password, firstName, lastName, gender, birthday, wantToPlay,"
	 * // +
	 * "userTypeID,curatorRequest) VALUES('rosenbaum@eyal', '123456', 'eyal', 'rosenbaum', 'm', '1983-06-11', true, 1,1)"
	 * ; stmt.executeUpdate(insert);
	 * 
	 * PreparedStatement passwordStatement =con.
	 * prepareStatement("SELECT * from user WHERE  email = ? AND password = ?"
	 * ); passwordStatement.setString(1, email); passwordStatement.setString(2,
	 * password); ResultSet rs = passwordStatement.executeQuery(); if
	 * (!rs.next()){ return false; } else return true; }
	 * 
	 * }
	 */

	/**
	 * 
	 * @param con
	 *            - connection to the server
	 * @param email
	 *            - username
	 * @param password
	 *            - user password
	 * @param firstName
	 *            - user first name
	 * @param LastName
	 *            - user last name
	 * @param gender
	 *            - user's gender
	 * @param date
	 *            - date
	 * @param userType
	 *            - user's type
	 * @return true if sign up was successful, false otherwise
	 * @throws SQLException
	 */
	public Boolean signUp(java.sql.Connection con, String email, String password, String firstName, String LastName,
			genderType gender, Date date, String userType) throws SQLException {
		if ((email == null) || (email.length() == 0) || (password == null) || (password.length() == 0)
				|| (firstName == null) || (firstName.length() == 0) || (LastName == null) || (LastName.length() == 0))
			return false;

		SelectCommand helper = new SelectCommand();
		// ArrayList<String> answer = helper.runLoginSearch(con, email,
		// password);
		// if (answer.get(1) == "success")
		// return false;
		boolean exists = helper.userExists(con, email);
		if (exists)
			return false;

		int curatorRequest = 1;
		if (userType.equals("Curator"))
			curatorRequest = 2;
		User newUser = new User(1, email, password, firstName, LastName, gender, date, true, userType, curatorRequest);
		char wantPlay = 'Y';

		Statement stmt = con.createStatement();
		String insert = "INSERT INTO user (email, password, firstName, lastName, wantToPlay,userTypeID, curatorRequest)"
				+ "VALUES('" + email + "', '" + password + "', '" + firstName + "', '" + LastName + "', '" + wantPlay
				+ "', 1," + curatorRequest + ")";

		/*
		 * String insert =
		 * "INSERT INTO user (email, password, firstName, lastName, gender, birthday, wantToPlay,"
		 * + "userTypeID,curatorRequest) VALUES('"+email+"', '"+password+"', '"+
		 * firstName+"', '"+LastName+"', '"+gender+"', '"+
		 * date+"', '"+wantPlay+"', 1,"+curatorRequest+")";
		 */
		stmt.executeUpdate(insert);

		if (gender != null) {
			String updateGender = " UPDATE  user SET gender = '" + gender + "'  WHERE email = '" + email + "'";
			stmt.executeUpdate(updateGender);
		}

		else {
			String updateGender = " UPDATE  user SET gender = 'n'  WHERE email = '" + email + "'";
			stmt.executeUpdate(updateGender);
		}
		if (date != null) {
			String updateDate = " UPDATE  user SET birthday = '" + date + "'  WHERE email = '" + email + "'";
			stmt.executeUpdate(updateDate);
		}

		PreparedStatement passwordStatement = con
				.prepareStatement("SELECT * from user WHERE  email = ? AND password = ?");
		passwordStatement.setString(1, email);
		passwordStatement.setString(2, password);
		ResultSet rs = passwordStatement.executeQuery();
		if (!rs.next()) {
			return false;
		} else
			return true;

	}

/**
 * 
 * @param con - connection string
 * @param username - username of user who performs the search
 * @param searchType - type of search - by title, by year of publication or by author 
 * @param author - author field, may be null
 * @param title - title field, may be null
 * @param freeText - freetext field, may be null
 * @param fromYear - year of publication field, may be null
 * @param toYear - year of publication field, may be null
 * @return true if search details were entered correctly into database, false otherwise
 * @throws SQLException
 */
	public boolean addSearch(java.sql.Connection con, String username, TypeOfResource searchType, String author,
			String title, String freeText, int fromYear, int toYear) throws SQLException {
		if (username == null)
			return false;
		if ((author == null) && (title == null) && (freeText == null))
			return false;
		SelectCommand helper = new SelectCommand();
		UpdateCommand helper2 = new UpdateCommand();

		User helperUser = helper.returnUserByUsername(con, username);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		// SearchItem item = new SearchItem(date, searchType, author, title,
		// freeText, helperUser.getUserID(), fromYear, toYear);
		Statement stmt = con.createStatement();
		int oldTopID = helper.returnTopIDSearchTable(con);

		String insert = "INSERT INTO search (searchDate, typeOfResource, author, title, freeText, user_userID, fromYear,"
				+ "toYear) VALUES('" + sqlDate + "', '" + searchType + "', '" + author + "', '" + title + "', '"
				+ freeText + "', " + helperUser.getUserID() + ", " + fromYear + ", " + toYear + ")";
		String insert2 = "INSERT INTO search (searchDate, typeOfResource, author, title, freeText, user_userID, fromYear,"
				+ "toYear) VALUES('" + sqlDate + "', '" + searchType + "', '" + author + "', '" + title + "', '"
				+ freeText + "', " + helperUser.getUserID() + ", " + fromYear + ")";
		String insert3 = "INSERT INTO search (searchDate, typeOfResource, author, title, freeText, user_userID, fromYear,"
				+ "toYear) VALUES('" + sqlDate + "', '" + searchType + "', '" + author + "', '" + title + "', '"
				+ freeText + "', " + helperUser.getUserID() + ", " + toYear + ")";
		String insert4 = "INSERT INTO search (searchDate, typeOfResource, author, title, freeText, user_userID, fromYear,"
				+ "toYear) VALUES('" + sqlDate + "', '" + searchType + "', '" + author + "', '" + title + "', '"
				+ freeText + "', " + helperUser.getUserID() + ", )";
		// String insert2 =
		// "INSERT INTO user (email, password, firstName, lastName, gender,
		// birthday, wantToPlay,"
		// +
		// "userTypeID,curatorRequest) VALUES('rosenbaum@eyal', '123456',
		// 'eyal', 'rosenbaum', 'm', '1983-06-11', true, 1,1)";
		if ((toYear == 0) && (fromYear == 0))
			stmt.executeUpdate(insert4);
		else if (toYear == 0)
			stmt.executeUpdate(insert2);
		else if (fromYear == 0)
			stmt.executeUpdate(insert3);
		else
			stmt.executeUpdate(insert);

		int newTopID = helper.returnTopIDSearchTable(con);

		if (newTopID != oldTopID)
			return true;
		else
			return false;

	}

/**
 * This methods add a user from database to gamer's table upon request to play 
 * @param con - connection to server
 * @param userID - user to add to gamer's id
 * @throws SQLException
 */
	public void addGamer(java.sql.Connection con, int userID) throws SQLException {

		PreparedStatement stmt = con.prepareStatement("INSERT INTO libArabDB.userGameInfo (userGameID) VALUES (?)");
		stmt.setInt(1, userID);
		stmt.executeUpdate();
	}

/**
 * 
 * @param con - connection to server
 * @param username - username of user to update score
 * @param sc - score to update for user
 * @return true if score was updated for the user, false otherwise
 * @throws SQLException
 */
	public boolean updateScore(java.sql.Connection con, String username, int sc) throws SQLException {

		SelectCommand help = new SelectCommand();
		boolean exists = help.userExists(con, username);
		int userID;
		if (exists) {
			userID = help.returnUserByUsername(con, username).getUserID();
			if (!help.gamerExists(con, userID)) { // gamer doesn't exist
				addGamer(con, userID);
			}
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"UPDATE libArabDB.userGameInfo SET score=score+" + sc + " WHERE userGameID=" + userID + "");
			return true;
		}
		return false;
	}

/**
 * 
 * @param con - connection to server
 * @param bibID - bibliography to update's ID
 * @param username - username which the bibliography belongs to
 * @param newName - new name for bibliography to update
 * @return true if bibliography name was updated successfully or false otherwise
 * @throws SQLException
 */
	public boolean updateBibilogrphyName(java.sql.Connection con, int bibID, String username, String newName)
			throws SQLException {

		SelectCommand helper = new SelectCommand();
		int userID = helper.returnUserByUsername(con, username).getUserID();
		Statement stmt = con.createStatement();
		String updateD = "update libArabDB.bibilogrpahy " + "set bibliogrphycolName = '" + newName + "' "
				+ "where userID = '" + userID + "' and idbibliographyID = '" + bibID + "'";
		stmt.executeUpdate(updateD);
		return helper.isExistBibliography(con, bibID, username, newName);

	}

/**
 * 
 * @param con - connection to server
 * @param username - username of user who uploads the question
 * @param question - the question
 * @param correctAnswer - the correct answer to the question
 * @param wrongAnswer1 - wrong answer to the question
 * @param wrongAnswer2 - wrong answer to the question
 * @param wrongAnswer3 - wrong answer to the question
 * @param questionItemID - item which the question refers to id
 * @param authorName - author of the item
 * @param itemName - item which the question refers to name
 * @return true if the question was added successfully or false otherwise
 * @throws SQLException
 */
	public boolean addQuestion(java.sql.Connection con, String username, String question, String correctAnswer,
			String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String questionItemID, String authorName,
			String itemName , String Publisher , String CreationDate , String UrlImage) throws SQLException {
		if ((username == null) || (question == null) || (correctAnswer == null) || (wrongAnswer1 == null)
				|| (wrongAnswer2 == null) || (wrongAnswer3 == null) || (questionItemID == null))
			return false;
		else {
			Statement stmt = con.createStatement();
			SelectCommand helper = new SelectCommand();
			int userID = 0;
			User helperUser = helper.returnUserByUsername(con, username);
			if (helperUser != null)
				userID = helperUser.getUserID();
			else
				return false;
			String insertQuestion = "INSERT INTO libArabDB.question (addQuestionUserID, question, correctAnswer, wrongAnswer1,"
					+ "wrongAnswer2, wrongAnswer3, questionItemID, authorName, itemName , Publisher , CreationDate ,UrlImage) VALUES(" + userID + ", '"
					+ question + "', '" + correctAnswer + "', '" + wrongAnswer1 + "', '" + wrongAnswer2 + "', '"
					+ wrongAnswer3 + "', '" + questionItemID + "', '" + authorName + "', '" + itemName + "', '" + Publisher + "'"
							+ ", '" + CreationDate + "', '" + UrlImage + "')";

			stmt.executeUpdate(insertQuestion);

			return true;
		}
	}

/**
 * 
 * @param con - connection to server
 * @param email - email of user to approve as curator
 * @return true if user was changed correctly to curator, false otherwise
 * @throws SQLException
 */
	public boolean approveUser(java.sql.Connection con, String email) throws SQLException {

		PreparedStatement approve = null;
		try {
			approve = con.prepareStatement("UPDATE user SET userTypeID =2  WHERE email =?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		approve.setString(1, email);
		approve.executeUpdate();
		return true;
	}

/**
 * 
 * @param con - connection to server
 * @param mail - username of user to delte
 * @return true if user was deleted successfully from database
 * @throws SQLException
 */
	public boolean DeleteComand(java.sql.Connection con, String mail) throws SQLException {

		PreparedStatement d = con.prepareStatement("delete from libArabDB.user WHERE email=?");
		d.setString(1, mail);
		int rs = d.executeUpdate();
		return true;
	}

	/*
	 * public boolean addFavorite(java.sql.Connection con, String username,
	 * String bookID, TypeOfResource type, String pageLink, int pageNum, String
	 * desc, int bibID) throws SQLException {
	 * 
	 * SelectCommand helper = new SelectCommand(); int userID =
	 * helper.returnUserByUsername(con, username).getUserID(); Statement stmt =
	 * con.createStatement(); if (type == TypeOfResource.MAP || type ==
	 * TypeOfResource.SHEET) { pageNum = 1; } String insert =
	 * "INSERT INTO libArabDB.favorite (userID,bookID,type,pageLink,pageNumber,"
	 * + "description, bibliography_idbibliographyID) VALUES ('" + userID +
	 * "', '" + bookID + "', " + "'" + type + "', '" + pageLink + "', '" +
	 * pageNum + "', '" + desc + "', '" + bibID + "')";
	 * 
	 * stmt.executeUpdate(insert);
	 * 
	 * if (helper.favExists(con, username, bookID, pageNum, bibID)) return true;
	 * 
	 * return false;
	 * 
	 * }
	 * 
	 * 
	 * public boolean removeFavorite(java.sql.Connection con, String username,
	 * String bookID, int pageNum, int bibID) throws SQLException {
	 * 
	 * SelectCommand helper = new SelectCommand(); int userID =
	 * helper.returnUserByUsername(con, username).getUserID(); Statement stmt =
	 * con.createStatement(); String delete = "delete from libArabDB.favorite "
	 * + "where bookID = '" + bookID + "' and pageNumber='" + pageNum +
	 * "' and userID = '" + userID + "' and bibliography_idbibliographyID = '" +
	 * bibID + "'"; stmt.executeUpdate(delete);
	 * 
	 * return !(helper.favExists(con, username, bookID, pageNum, bibID)); }
	 */

/**
 * 
 * @param con - connection to server
 * @param username - user's username
 * @param bookID - item id  to add as favorite
 * @param type - type of resource to add to favorites
 * @param pageLink - link to relevant page of book
 * @param pageNum - number of page in book which is the favorite
 * @param desc - description of the favorite
 * @param bibID - bibliography ID to add the favorite to
 * @return true if favorite was added successfully, false otherwise
 * @throws SQLException
 */
	public boolean addFavorite(java.sql.Connection con, String username, String bookID, TypeOfResource type,
			String pageLink, int pageNum, String desc, int bibID, String title, String author, String publisher,
			String creationDate, String other, String source) throws SQLException {

		SelectCommand helper = new SelectCommand();
		int userID = helper.returnUserByUsername(con, username).getUserID();
		Statement stmt = con.createStatement();
		if (type == TypeOfResource.MAP || type == TypeOfResource.SHEET) {
			pageNum = 1;
		}
		String insert = "INSERT INTO libArabDB.favorite (userID,bookID,type,pageLink,pageNumber,"
				+ "description, bibliography_idbibliographyID, title, author, publisher, creationDate, other, source) VALUES ('" + userID + "', '" + bookID + "', " + "'"
				+ type + "', '" + pageLink + "', '" + pageNum + "', '" + desc + "', '" + bibID + "', '"+ title + "', '"+
				author+"', '"+publisher+"', '"+creationDate+"', '"+other+"', '"+source+"')";

		stmt.executeUpdate(insert);

		if (helper.favExists(con, username, bookID, pageNum, bibID))
			return true;

		return false;

	}

/**
 * 
 * @param con - connection to server
 * @param username - relevant user's username
 * @param bookID - item ID for the item to remove from favorites
 * @param pageNum - page number in the item to remove from favorites
 * @param bibID - bibliography of which to delete the favorite
 * @return true if favorite was successfully removed from the database
 * @throws SQLException
 */
	public boolean removeFavorite(java.sql.Connection con, String username, String bookID, int pageNum, int bibID)
			throws SQLException {

		SelectCommand helper = new SelectCommand();
		int userID = helper.returnUserByUsername(con, username).getUserID();
		Statement stmt = con.createStatement();
		String delete = "delete from libArabDB.favorite " + "where bookID = '" + bookID + "' and pageNumber='" + pageNum
				+ "' and userID = '" + userID + "' and bibliography_idbibliographyID = '" + bibID + "'";
		stmt.executeUpdate(delete);

		return !(helper.favExists(con, username, bookID, pageNum, bibID));

	}

/**
 * 
 * @param con - connection to server
 * @param username - username of user which the favorite belonged to
 * @param bibID - bibliography ID which the favorite to update belongs to
 * @param userDesc - description of the favorite to update
 * @return true if description was updated successfully for favorites, false otherwise
 * @throws SQLException
 */
	public boolean updateDesc(java.sql.Connection con, String username, int bibID, String userDesc)
			throws SQLException {

		SelectCommand helper = new SelectCommand();
		int userID = helper.returnUserByUsername(con, username).getUserID();
		Statement stmt = con.createStatement();
		String updateD = "update libArabDB.favorite " + "set description = '" + userDesc + "' " + "where userID = '"
				+ userID + "' and bibliography_idbibliographyID='" + bibID + "'";
		stmt.executeUpdate(updateD);
		return true;
	}
	// public boolean newSearch(java.sql.Connection con, String username, Date
	// birthday, genderType gender, String author, String title, String
	// userType){
	//
	// }

}