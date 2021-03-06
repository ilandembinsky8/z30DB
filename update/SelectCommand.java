package lib;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SelectCommand extends SQLCommand {

	private ArrayList<String> column;
	private String tableName;
	private ArrayList<String> joins;
	private ArrayList<String> where;
	private String query = null;

//  Default constructor for SelectCommand
	public SelectCommand() {
		super("select");
		this.column = null;
		this.tableName = null;
		this.joins = null;
		this.where = null;
	}

//  Constructor for SelectCommand class
//  parameters: column -- maybe delete????
	public SelectCommand(ArrayList<String> column, String tableName,
			ArrayList<String> joins, ArrayList<String> where) {
		super("select");
		this.column = column;
		this.tableName = tableName;
		this.joins = joins;
		this.where = where;
	}

/**
 * 
 * @param con - connection to server
 * @param email - user's email which is also username
 * @return true if user exists in database, otherwise return false
 * @throws SQLException
 */

	public boolean userExists(java.sql.Connection con, String email)
			throws SQLException {
		PreparedStatement userExist = con
				.prepareStatement("select * from libArabDB.user Where email = ? ");
		userExist.setString(1, email);
		ResultSet rs = userExist.executeQuery();
		if (!rs.next())
			return false;
		else {
			return true;
		}
	}

/**
 * @param con - connection to server
 * @param GamerID - user's id in database
 * @return true - if user exists in gamers table in database, otherwise return false
 * @throws SQLException
 */
	public boolean gamerExists(java.sql.Connection con, int GamerID)
			throws SQLException {
		PreparedStatement gamerExist = con
				.prepareStatement("select * from libArabDB.userGameInfo Where userGameID = ?");
		gamerExist.setInt(1, GamerID);
		ResultSet rs = gamerExist.executeQuery();
		if (!rs.next())
			return false;
		else {
			return true;
		}
	}
/**
 * @param con - connection to server
 * @param username - relvant User's username
 * @return User - relevant user if the username exist in databse, otherwise return null
 * @throws SQLException
 */
	public User returnUserByUsername(java.sql.Connection con, String username)
			throws SQLException {
		PreparedStatement passwordStatement = con
				.prepareStatement("select * from libArabDB.user inner join libArabDB.usertype"
						+ " on user.userTypeID=usertype.userTypeID where email = ?");
		passwordStatement.setString(1, username);
		User result;
		ResultSet rs = passwordStatement.executeQuery();
		// in case no match was found
		if (!rs.next())
			return null;
		else {
			result = new User(rs.getInt("userID"), rs.getString("email"),
					rs.getString("password"), rs.getString("firstName"),
					rs.getString("lastName"), genderType.valueOf(rs
							.getString("gender")), rs.getDate("birthday"),
					rs.getBoolean("wantToPlay"), rs.getString("type"),
					rs.getInt("curatorRequest"));

		}
		return result;
	}

/**
* @param con - connection to server
* @param n - number
* @return ArrayList of users. if n= 0 return all users, otherwise return first n users from database
* @throws SQLException
*/
	public ArrayList<User> returnAllUsers(java.sql.Connection con, int n)
			throws SQLException {

		ArrayList<User> all = new ArrayList<User>();
		java.sql.Statement allStatement = con.createStatement();
		this.query = "select * from libArabDB.user inner join libArabDB.usertype on user.userTypeID=libArabDB.usertype.userTypeID "
				+ "where libArabDB.user.birthday not in ('0000-00-00')";

		ResultSet rs = allStatement.executeQuery(this.query);
		if (n == 0) {
			while (rs.next()) {
				User user = new User(rs.getInt("userID"),
						rs.getString("email"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"),
						genderType.valueOf(rs.getString("gender")),
						rs.getDate("birthday"), rs.getBoolean("wantToPlay"),
						rs.getString("type"), rs.getInt("curatorRequest"));
				all.add(user);
			}
		}

		else {
			while (rs.next() & n != 0) {
				User user = new User(rs.getInt("userID"),
						rs.getString("email"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"),
						genderType.valueOf(rs.getString("gender")),
						rs.getDate("birthday"), rs.getBoolean("wantToPlay"),
						rs.getString("type"), rs.getInt("curatorRequest"));
				all.add(user);
				n--;

			}
		}
		return all;
	}

/**
 * 
 * @param con - connection to server
 * @param bibID - bibliography id
 * @param username  - user with bibliography's username
 * @param newName - bibliography name
 * @return true - if the bibliographt exists in database, or false otherwise
 * @throws SQLException
 */
	public boolean isExistBibliography(java.sql.Connection con, int bibID,
			String username, String newName) throws SQLException {

		// SelectCommand helper = new SelectCommand();
		int userID = returnUserByUsername(con, username).getUserID();
		Statement stmt = con.createStatement();
		this.query = "select * from libArabDB.bibilogrpahy "
				+ "where bibliogrphycolName = '" + newName + "' "
				+ "and userID = '" + userID + "' and idbibliographyID = '"
				+ bibID + "'";
		ResultSet rs = stmt.executeQuery(this.query);
		if (!rs.next())
			return false;
		else
			return true;

	}
	
/**
 * 
 * @param con - connection to server
 * @return a list of all users who have an open unapproved request to become curators, or, if there are no such users returns null
 * @throws SQLException
 */

	public ArrayList<User> returnAllCuratorsPending(java.sql.Connection con)
			throws SQLException {
		ArrayList<User> curatorPending = new ArrayList<User>();
		java.sql.Statement allStatement = con.createStatement();

		String s = "select * from libArabDB.user inner join libArabDB.usertype on user.userTypeID="
				+ "libArabDB.user.userTypeID where libArabDB.user.birthday  not in ('0000-00-00') and"
				+ " libArabDB.user.userTypeID = 1 and libArabDB.user.curatorRequest = 2";

		ResultSet rs = allStatement.executeQuery(s);

		while (rs.next()) {
			User user = new User(rs.getInt("userID"), rs.getString("email"),
					rs.getString("password"), rs.getString("firstName"),
					rs.getString("lastName"), genderType.valueOf(rs
							.getString("gender")), rs.getDate("birthday"),
					true, rs.getString("type"), rs.getInt("curatorRequest"));
			curatorPending.add(user);
		}
		return curatorPending;
	}
	
/**
 * 
 * @param con - connection to server
 * @return all users which are curators from database, null if there are no curators in databse
 * @throws SQLException
 */

	public ArrayList<User> returnAllCurators(java.sql.Connection con)
			throws SQLException {

		ArrayList<User> curator = new ArrayList<User>();
		java.sql.Statement allStatement = con.createStatement();
		// String s ="SELECT * from user WHERE userTypeID = 2";
		String s = "select * from libArabDB.user inner join libArabDB.usertype on user.userTypeID="
				+ "libArabDB.user.userTypeID where libArabDB.user.birthday  not in ('0000-00-00') and"
				+ " libArabDB.user.userTypeID = 2";
		ResultSet rs = allStatement.executeQuery(s);

		while (rs.next()) {
			User user = new User(rs.getInt("userID"), rs.getString("email"),
					rs.getString("password"), rs.getString("firstName"),
					rs.getString("lastName"), genderType.valueOf(rs
							.getString("gender")), rs.getDate("birthday"),
					rs.getBoolean("wantToPlay"), rs.getString("type"),
					rs.getInt("curatorRequest"));
			// System.out.println(user.getEmail());
			// if(user.getUserTypeID() == 2){
			curator.add(user);
		}
		return curator;
	}

/**
 * 
 * @param con - connection to server
 * @param username - username of user who tries to login
 * @param password - password of user who tries to login
 * @return list of strings - success or fail, name of user if exists and password was true, type of user if successful login
 * @throws SQLException
 */
	public ArrayList<String> runLoginSearch(java.sql.Connection con,
			String username, String password) throws SQLException {
		PreparedStatement passwordStatement = con
				.prepareStatement("select * from libArabDB.user inner join libArabDB.usertype"
						+ " on user.userTypeID=usertype.userTypeID where email = ? AND password = ?");
		passwordStatement.setString(1, username);
		passwordStatement.setString(2, password);
		ArrayList<String> replyList = new ArrayList<String>();
		String replyClient = "fail";
		String userNameReply = "";
		
		ResultSet rs = passwordStatement.executeQuery();
		// in case no match was found
		if (!rs.next()) {
			replyList.add("Incorrect Email or Password");
			replyList.add(replyClient);
			replyList.add("");
			return replyList;
		} else {
			User userA = new User(rs.getInt("userID"), rs.getString("email"),
					rs.getString("password"), rs.getString("firstName"),
					rs.getString("lastName"), genderType.valueOf(rs
							.getString("gender")), rs.getDate("birthday"),
					rs.getBoolean("wantToPlay"), rs.getString("type"),
					rs.getInt("curatorRequest"));
			userNameReply = userA.getFirstName() + " " + userA.getLastName();
			replyClient = "success";
			if (userA.getUserType().equals("Regular")) {
				if (userA.getCuratorRequest() == 2) {
					replyList.add("Curator not approved User");
					replyList.add(replyClient);
					replyList.add(userNameReply);
					return replyList;
				} else {
					replyList.add("Regular user");
					replyList.add(replyClient);
					replyList.add(userNameReply);
					return replyList;
				}
			} else if (userA.getUserType().equals("Curator")) {
				replyList.add("Curator approved User");
				replyList.add(replyClient);
				replyList.add(userNameReply);
				return replyList;
			} else if (userA.getUserType().equals("Admin")) {
				replyList.add("Admin");
				replyList.add(replyClient);
				replyList.add(userNameReply);
				return replyList;
			}
		}
		return null;

	}

/**
 * 
 * @param con - connection to server
 * @param numRecord - number of records to return
 * @return a list of searches that were entered by users
 * @throws SQLException
 */
	public ArrayList<SearchItem> search(java.sql.Connection con, int numRecord)
			throws SQLException {

		ArrayList<SearchItem> all = new ArrayList<SearchItem>();
		java.sql.Statement allStatement = con.createStatement();
		this.query = "SELECT * FROM libArabDB.user b  inner join libArabDB.search a on"
				+ " b.userID = a.user_userID inner join libArabDB.usertype c on b.userTypeID = c.userTypeID";
		ResultSet rs = allStatement.executeQuery(this.query);
		if (numRecord == 0) {
			while (rs.next()) {
				SearchItem search = new SearchItem(rs.getInt("basicSearchID"),
						rs.getDate("searchDate"), TypeOfResource.valueOf(rs
								.getString("typeOfResource")),
						rs.getString("author"), rs.getString("title"),
						rs.getString("freeText"), rs.getInt("user_userID"),
						rs.getInt("fromYear"), rs.getInt("toYear"));
				all.add(search);
				
			}
		} else {
			while (rs.next() & numRecord != 0) {
				SearchItem search = new SearchItem(rs.getInt("basicSearchID"),
						rs.getDate("searchDate"), TypeOfResource.valueOf(rs
								.getString("typeOfResource")),
						rs.getString("author"), rs.getString("title"),
						rs.getString("freeText"), rs.getInt("user_userID"),
						rs.getInt("fromYear"), rs.getInt("toYear"));
				all.add(search);
				--numRecord;
			}
		}
		return all;
	}
	
/**
 * This method return value is determined by the id of the searches made, but data may not be accurate due to the table
 * being previously dropped
 * @param con - connection to server
 * @return how many searches by users were made
 * @throws SQLException
 */

	public int returnTopIDSearchTable(java.sql.Connection con)
			throws SQLException {

		int result = 0;

		java.sql.Statement allStatement = con.createStatement();
		this.query = "select max(basicSearchID) from libArabDB.search";
		ResultSet rs = allStatement.executeQuery(this.query);
		while (rs.next())
			return rs.getInt("max(basicSearchID)");
		return result;

	}
	
/**
 * 
 * @param con - connection to server
 * @param questionItemID - relevant item ID
 * @return a list of questions which are related to relevant item from library
 * @throws SQLException
 */

	public ArrayList<Question> returnQuestionsByItem(java.sql.Connection con,
			String questionItemID) throws SQLException {
		ArrayList<Question> result = new ArrayList<Question>();
		java.sql.Statement statement = con.createStatement();
		this.query = "SELECT * FROM libArabDB.question where question.questionItemID = "
				+ "\""+questionItemID+"\"";
		ResultSet rs = statement.executeQuery(this.query);
		while (rs.next()) {
			Question newQuestion = new Question(rs.getInt("addQuestionUserID"),
					rs.getString("question"), rs.getString("correctAnswer"),
					rs.getString("wrongAnswer1"), rs.getString("wrongAnswer2"),
					rs.getString("wrongAnswer3"),
					rs.getString("questionItemID"), rs.getString("authorName"),
					rs.getString("itemName"));
			result.add(newQuestion);
		}
		return result;
	}

/**
 * 
 * @param con - connection to server
 * @return list of items which have questions in quiz related to them
 * @throws SQLException
 */
	public ArrayList<Item> returnItemsOfQuestions(java.sql.Connection con)
			throws SQLException {

		ArrayList<Item> result = new ArrayList<Item>();
		java.sql.Statement statement = con.createStatement();
		this.query = "SELECT * FROM libArabDB.question group by itemName";
		ResultSet rs = statement.executeQuery(this.query);
		while (rs.next()) {
			result.add(new Item(rs.getString("itemName"),rs.getString("questionItemID"),
					rs.getString("authorName"), rs.getString("Publisher"), rs.getString("CreationDate"),
							rs.getString("UrlImage")));
		}
		return result;
	}

/**
 * 
 * @param con - connection to server
 * @param email - username email
 * @return a list of the top 10 gamers with score and their details as well as the requesting user's details
 * @throws SQLException
 */
	public ArrayList<String> returnTop10(java.sql.Connection con, String email)
			throws SQLException {
		// array list that we should to return
		ArrayList<String> replyList = new ArrayList<String>();
		// first of all return the score of user who call to the return top 10
		PreparedStatement ps = con.prepareStatement("SET @rank=0");
		ps.executeQuery();
		PreparedStatement userScore = con
				.prepareStatement("Select rank,userGameID, score, userID, email,"
						+ " password,firstName, lastName, gender, birthday, wantToPlay,userTypeID, curatorRequest "
						+ "FROM (Select @rank:=@rank+1 AS rank,userGameID, score, userID, email, password,firstName, lastName,"
						+ " gender, birthday, wantToPlay,userTypeID, curatorRequest FROM libArabDB.userGameInfo"
						+ " INNER JOIN libArabDB.user ON libArabDB.userGameInfo.userGameID = libArabDB.user.userID"
						+ " ORDER BY score DESC) as a where email =?");
		userScore.setString(1, email);
		ResultSet r = userScore.executeQuery();
		String s1, s2, s3;
		int rank = 0;
		if (r.next()) {
			rank = r.getInt("rank");
			GameInfo g = new GameInfo(r.getInt("userGameID"), r.getInt("score"));
			User user = new User(r.getInt("userID"), r.getString("email"),
					r.getString("password"), r.getString("firstName"),
					r.getString("lastName"), genderType.valueOf(r
							.getString("gender")), r.getDate("birthday"),
					r.getBoolean("wantToPlay"), // rs.getString("type"),
					r.getInt("curatorRequest"));
			s1 = "" + rank;
			s2 = user.getFirstName();
			s3 = "" + g.getScore();
			// System.out.println(" " + rank + " " + user.getFirstName() + " "
			// +g.getScore());
			replyList.add(s1);
			replyList.add(s2);
			replyList.add(s3);
		}

		// return the first top 10 scores in list array , arranged top-down.

		PreparedStatement top = con
				.prepareStatement("select * FROM libArabDB.userGameInfo"
						+ " INNER JOIN libArabDB.user ON libArabDB.userGameInfo.userGameID = libArabDB.user.userID"
						+ " ORDER BY score DESC LIMIT 10");

		ResultSet rs = top.executeQuery();
		rank = 0;
		while (rs.next()) {
			GameInfo g = new GameInfo(rs.getInt("userGameID"),
					rs.getInt("score"));
			User user = new User(rs.getInt("userID"), rs.getString("email"),
					rs.getString("password"), rs.getString("firstName"),
					rs.getString("lastName"), genderType.valueOf(rs
							.getString("gender")), rs.getDate("birthday"),
					rs.getBoolean("wantToPlay"), // rs.getString("type"),
					rs.getInt("curatorRequest"));
			++rank;
			s1 = "" + rank;
			s2 = user.getFirstName();
			s3 = "" + g.getScore();
			// System.out.println(" " + rank + " " + user.getFirstName() + " "
			// +g.getScore());
			replyList.add(s1);
			replyList.add(s2);
			replyList.add(s3);
		}

		return replyList;
	}

///**
// * 
// * @param con
// * @param email - user's username
// * @return
// * @throws SQLException
// */
//	public ArrayList<Favorite> getFavorites(java.sql.Connection con,
//			String email) throws SQLException {
//		// array list that we should to return
//		ArrayList<Favorite> replyList = new ArrayList<Favorite>();
//		PreparedStatement userfavorite = con
//				.prepareStatement("select * FROM libArabDB.favorite "
//						+ "INNER JOIN libArabDB.user ON libArabDB.favorite.userID = libArabDB.user.userID "
//						+ "WHERE libArabDB.user.email = ?");
//		userfavorite.setString(1, email);
//		ResultSet r = userfavorite.executeQuery();
//		while (r.next()) {
//			Favorite favorite = new Favorite(r.getInt("FavoriteID"),
//					r.getString("BookID"), r.getString("PageLink"),
//					r.getString("Description"), r.getInt("PageNumber"));
//			replyList.add(favorite);
//			System.out.println("" + favorite.getBookID() + " "
//					+ favorite.getDescription());
//		}
//		return replyList;
//	}

/**
 * 
 * @param con - connection to server
 * @param fromYear - search all items from year
 * @param toYear - to year
 * @param numOfAuthors  - number of authors to return
 * @return list of authors by publication year range and number of authors. 
 * @throws SQLException
 */
	public ArrayList<String> returnAuthorsList(java.sql.Connection con,
			int fromYear, int toYear, int numOfAuthors) throws SQLException {

		ArrayList<String> returnAuthorsBySearch = new ArrayList<String>();
		java.sql.Statement returnAuthorsState = con.createStatement();

		if (fromYear == 0) {
			if (toYear == 0) /** return all years **/
			{
				if (numOfAuthors == 0) {
					PreparedStatement returnAuthorsStatement = con
							.prepareStatement("select search.author , count(*) as numOfBooks "
									+ " from libArabDB.search "
									+ "group by author "
									+ "order by numOfBooks DESC");
					ResultSet rs = returnAuthorsStatement.executeQuery();

					while (rs.next()) {
						String author = rs.getString("author");
						String numOfBooks = rs.getString("numOfBooks");
						returnAuthorsBySearch.add(author);
						returnAuthorsBySearch.add(numOfBooks);
					}
					return returnAuthorsBySearch;
				} else {
					while (numOfAuthors != 0) {
						PreparedStatement returnAuthorsStatement = con
								.prepareStatement("select search.author , count(*) as numOfBooks "
										+ " from libArabDB.search "
										+ "group by author "
										+ "order by numOfBooks DESC");
						ResultSet rs = returnAuthorsStatement.executeQuery();

						while (rs.next() & numOfAuthors != 0) {
							String author = rs.getString("author");
							String numOfBooks = rs.getString("numOfBooks");
							returnAuthorsBySearch.add(author);
							returnAuthorsBySearch.add(numOfBooks);
							numOfAuthors--;
						}
						return returnAuthorsBySearch;

					}

				}
			} else {
				/** fromYear is zero , toYear is x (less than x ) **/
				if (numOfAuthors == 0) {
					PreparedStatement returnAuthorsStatement = con
							.prepareStatement("select search.author , count(*) as numOfBooks "
									+ " from libArabDB.search "
									+ "where extract(year from searchDate) <= ?   "
									+ "group by author "
									+ "order by numOfBooks DESC");

					returnAuthorsStatement.setInt(1, toYear);

					ResultSet rs = returnAuthorsStatement.executeQuery();

					while (rs.next()) {
						String author = rs.getString("author");
						String numOfBooks = rs.getString("numOfBooks");
						returnAuthorsBySearch.add(author);
						returnAuthorsBySearch.add(numOfBooks);
					}
					return returnAuthorsBySearch;
				} else {
					while (numOfAuthors != 0) {
						PreparedStatement returnAuthorsStatement = con
								.prepareStatement("select search.author , count(*) as numOfBooks "
										+ " from libArabDB.search "
										+ "where extract(year from searchDate) <= ?   "
										+ "group by author "
										+ "order by numOfBooks DESC");

						returnAuthorsStatement.setInt(1, toYear);
						ResultSet rs = returnAuthorsStatement.executeQuery();

						while (rs.next() & numOfAuthors != 0) {
							String author = rs.getString("author");
							String numOfBooks = rs.getString("numOfBooks");
							returnAuthorsBySearch.add(author);
							returnAuthorsBySearch.add(numOfBooks);
							numOfAuthors--;
						}
						return returnAuthorsBySearch;

					}

				}
			}
		}
		if (toYear == 0) {
			/** from year x to inf **/
			if (numOfAuthors == 0) {
				PreparedStatement returnAuthorsStatement = con
						.prepareStatement("select search.author , count(*) as numOfBooks "
								+ " from libArabDB.search "
								+ "where extract(year from searchDate) >= ?   "
								+ "group by author "
								+ "order by numOfBooks DESC");

				returnAuthorsStatement.setInt(1, fromYear);

				ResultSet rs = returnAuthorsStatement.executeQuery();

				while (rs.next()) {
					String author = rs.getString("author");
					String numOfBooks = rs.getString("numOfBooks");
					returnAuthorsBySearch.add(author);
					returnAuthorsBySearch.add(numOfBooks);
				}
				return returnAuthorsBySearch;
			} else {
				while (numOfAuthors != 0) {
					PreparedStatement returnAuthorsStatement = con
							.prepareStatement("select search.author , count(*) as numOfBooks "
									+ " from libArabDB.search "
									+ "where extract(year from searchDate) >= ?   "
									+ "group by author "
									+ "order by numOfBooks DESC");

					returnAuthorsStatement.setInt(1, fromYear);
					ResultSet rs = returnAuthorsStatement.executeQuery();

					while (rs.next() & numOfAuthors != 0) {
						String author = rs.getString("author");
						String numOfBooks = rs.getString("numOfBooks");
						returnAuthorsBySearch.add(author);
						returnAuthorsBySearch.add(numOfBooks);
						numOfAuthors--;
					}
					return returnAuthorsBySearch;

				}

			}
		} else {
			/** from year and to year defined **/
			if (numOfAuthors == 0) {
				PreparedStatement returnAuthorsStatement = con
						.prepareStatement("select search.author , count(*) as numOfBooks "
								+ " from libArabDB.search "
								+ "where extract(year from searchDate) between ? AND ?  "
								+ "group by author "
								+ "order by numOfBooks DESC");

				returnAuthorsStatement.setInt(1, fromYear);
				returnAuthorsStatement.setInt(2, toYear);
				ResultSet rs = returnAuthorsStatement.executeQuery();

				while (rs.next()) {
					String author = rs.getString("author");
					String numOfBooks = rs.getString("numOfBooks");
					returnAuthorsBySearch.add(author);
					returnAuthorsBySearch.add(numOfBooks);
				}
				return returnAuthorsBySearch;
			} else {
				while (numOfAuthors != 0) {
					PreparedStatement returnAuthorsStatement = con
							.prepareStatement("select search.author , count(*) as numOfBooks "
									+ " from libArabDB.search "
									+ " where extract(year from searchDate) between ? AND ? "
									+ "group by author "
									+ "order by numOfBooks DESC");

					returnAuthorsStatement.setInt(1, fromYear);
					returnAuthorsStatement.setInt(2, toYear);
					ResultSet rs = returnAuthorsStatement.executeQuery();

					while (rs.next() & numOfAuthors != 0) {
						String author = rs.getString("author");
						String numOfBooks = rs.getString("numOfBooks");
						returnAuthorsBySearch.add(author);
						returnAuthorsBySearch.add(numOfBooks);
						numOfAuthors--;
					}
					return returnAuthorsBySearch;

				}

			}
		}
		return returnAuthorsBySearch;
	}

/**
 * 
 * @param con - connection to server
 * @param fromYear
 * @param toYear
 * @return list of numbers of resources by category based on year publication range
 * @throws SQLException
 */
	public ArrayList<String> returnResourceList(java.sql.Connection con,
			int fromYear, int toYear) throws SQLException {

		ArrayList<String> returnCategoriesBySearch = new ArrayList<String>();
		// java.sql.Statement returnCategoriesState = con.createStatement();

		if (fromYear == 0) {
			if (toYear == 0) /** return all years **/
			{
				PreparedStatement returnCategoryStatment = con
						.prepareStatement("SELECT search.typeOfResource , count(*) as numOfResources"
								+ " FROM libArabDB.search "
								+ " group by typeOfResource"
								+ " order by numOfResources ");
				ResultSet rs = returnCategoryStatment.executeQuery();
				while (rs.next()) {
					String Category = rs.getString("typeOfResource");
					String numOfResources = rs.getString("numOfResources");
					returnCategoriesBySearch.add(Category);
					returnCategoriesBySearch.add(numOfResources);
				}
				return returnCategoriesBySearch;
			} else {
				/** fromYear is zero , toYear is x (less than x ) **/
				PreparedStatement returnCategoryStatment = con
						.prepareStatement("select search.typeOfResource , count(*) as numOfResources "
								+ " from libArabDB.search "
								+ "where extract(year from searchDate) <= ?   "
								+ " group by typeOfResource "
								+ "order by numOfResources DESC");

				returnCategoryStatment.setInt(1, toYear);

				ResultSet rs = returnCategoryStatment.executeQuery();

				while (rs.next()) {
					String Category = rs.getString("typeOfResource");
					String numOfResources = rs.getString("numOfResources");
					returnCategoriesBySearch.add(Category);
					returnCategoriesBySearch.add(numOfResources);
				}
				return returnCategoriesBySearch;

			}
		}
		if (toYear == 0) {
			/** from year x to inf **/
			PreparedStatement returnCategoryStatment = con
					.prepareStatement("select search.typeOfResource , count(*) as numOfResources "
							+ " from libArabDB.search "
							+ "where extract(year from searchDate) >= ?   "
							+ "group by typeOfResource "
							+ "order by numOfResources DESC");

			returnCategoryStatment.setInt(1, fromYear);

			ResultSet rs = returnCategoryStatment.executeQuery();

			while (rs.next()) {
				String Category = rs.getString("typeOfResource");
				String numOfResources = rs.getString("numOfResources");
				returnCategoriesBySearch.add(Category);
				returnCategoriesBySearch.add(numOfResources);
			}
			return returnCategoriesBySearch;

		} else {
			/** from year and to year defined **/

			PreparedStatement returnCategoryStatment = con
					.prepareStatement("SELECT search.typeOfResource , count(*) as numOfResources"
							+ " FROM libArabDB.search"
							+ " where extract(year from searchDate) Between ? AND ? "
							+ " group by typeOfResource"
							+ " order by numOfResources");

			returnCategoryStatment.setInt(1, fromYear);
			returnCategoryStatment.setInt(2, toYear);
			ResultSet rs = returnCategoryStatment.executeQuery();

			while (rs.next()) {
				String Category = rs.getString("typeOfResource");
				String numOfResources = rs.getString("numOfResources");
				returnCategoriesBySearch.add(Category);
				returnCategoriesBySearch.add(numOfResources);
			}
			return returnCategoriesBySearch;
		}

	}

/*	public ArrayList<Favorite> getBib(java.sql.Connection con, String userID,
			int BibId) throws SQLException {
		ArrayList<Favorite> allFavOfBib = new ArrayList<Favorite>();
		SelectCommand sc = new SelectCommand();

		Integer thisUserID = sc.returnUserByUsername(con, userID).getUserID();

		PreparedStatement getBibStatement = con
				.prepareStatement("select favoriteID, bookID , type , pageLink , pageNumber , description"
						+ " from libArabDB.favorite"
						+ " where libArabDB.favorite.userID = ? AND bibliography_idbibliographyID = ? ");
		getBibStatement.setInt(1, thisUserID);
		getBibStatement.setInt(2, BibId);

		ResultSet rs = getBibStatement.executeQuery();

		while (rs.next()) {

			Favorite favResult = new Favorite(rs.getInt("favoriteID"),
					rs.getString("bookID"), rs.getString("pageLink"),
					rs.getString("description"), rs.getInt("pageNumber"));
			allFavOfBib.add(favResult);
		}
		return allFavOfBib;

	}*/
	
/**
 * 
 * @param con - connection to server
 * @param fromYear - year to search from
 * @param toYear - year to search up to
 * @param numOfTitles - number of titles to return
 * @return list which contains number of searches per title by publication year range and number of titles to return in a descending order
 * @throws SQLException
 */
	public ArrayList<String> returnTitleList(java.sql.Connection con, int fromYear, int toYear , int numOfTitles) throws SQLException 
	{

			ArrayList<String> returnTitlesBySearch = new ArrayList<String>();
		//	java.sql.Statement returnAuthorsState = con.createStatement();

			if (fromYear == 0) {
				if (toYear == 0) /** return all years **/
				{
					if (numOfTitles == 0) {
						PreparedStatement returnTitleStatement = con
								.prepareStatement("select search.title , count(*) as numOfSperTitle "
										+ " from libArabDB.search " + "group by title " + "order by numOfSperTitle DESC");
						ResultSet rs = returnTitleStatement.executeQuery();

						while (rs.next()) {
							String title = rs.getString("title");
							String numOfTitle = rs.getString("numOfSperTitle");
							returnTitlesBySearch.add(title);
							returnTitlesBySearch.add(numOfTitle);
						}
						return returnTitlesBySearch;
					} else {
						while (numOfTitles != 0) {
							PreparedStatement returnTitleStatement = con
									.prepareStatement("select search.title , count(*) as numOfSperTitle "
											+ " from libArabDB.search " + "group by title " + "order by numOfSperTitle DESC");
							ResultSet rs = returnTitleStatement.executeQuery();

							while (rs.next() & numOfTitles != 0) {
								String title = rs.getString("title");
								String numOfTitle = rs.getString("numOfSperTitle");
								returnTitlesBySearch.add(title);
								returnTitlesBySearch.add(numOfTitle);
								numOfTitles--;
							}
							return returnTitlesBySearch;

						}

					}
				} else { /** fromYear is zero , toYear is x (less than x ) **/
					if (numOfTitles == 0) {
						PreparedStatement returnTitleStatement = con
								.prepareStatement("select search.title , count(*) as numOfSperTitle "
										+ " from libArabDB.search " + "where extract(year from searchDate) <= ?   "
										+ "group by title " + "order by numOfSperTitle DESC");

						returnTitleStatement.setInt(1, toYear);

						ResultSet rs = returnTitleStatement.executeQuery();

						while (rs.next()) {
							String title = rs.getString("title");
							String numOfTitle= rs.getString("numOfSperTitle");
							returnTitlesBySearch.add(title);
							returnTitlesBySearch.add(numOfTitle);
						}
						return returnTitlesBySearch;
					} else {
						while (numOfTitles != 0) {
							PreparedStatement returnTitleStatement = con
									.prepareStatement("select search.title , count(*) as numOfSperTitle "
											+ " from libArabDB.search " + "where extract(year from searchDate) <= ?   "
											+ "group by title " + "order by numOfSperTitle DESC");

							returnTitleStatement.setInt(1, toYear);
							ResultSet rs = returnTitleStatement.executeQuery();

							while (rs.next() & numOfTitles != 0) {
								String title = rs.getString("title");
								String numOfTitle = rs.getString("numOfSperTitle");
								returnTitlesBySearch.add(title);
								returnTitlesBySearch.add(numOfTitle);
								numOfTitles--;
							}
							return returnTitlesBySearch;

						}

					}
				}
			}
			if (toYear == 0) { /** from year x to inf **/
				if (numOfTitles == 0) {
					PreparedStatement returnTitlesStatement = con
							.prepareStatement("select search.title , count(*) as numOfSperTitle " + " from libArabDB.search "
									+ "where extract(year from searchDate) >= ?   " + "group by title "
									+ "order by numOfSperTitle DESC");

					returnTitlesStatement.setInt(1, fromYear);

					ResultSet rs = returnTitlesStatement.executeQuery();

					while (rs.next()) {
						String title = rs.getString("title");
						String numOfTitle = rs.getString("numOfSperTitle");
						returnTitlesBySearch.add(title);
						returnTitlesBySearch.add(numOfTitle);
					}
					return returnTitlesBySearch;
				} else {
					while (numOfTitles != 0) {
						PreparedStatement returnTitlesStatement = con
								.prepareStatement("select search.title , count(*) as numOfSperTitle "
										+ " from libArabDB.search " + "where extract(year from searchDate) >= ?   "
										+ "group by title " + "order by numOfSperTitle DESC");

						returnTitlesStatement.setInt(1, fromYear);
						ResultSet rs = returnTitlesStatement.executeQuery();

						while (rs.next() & numOfTitles != 0) {
							String title = rs.getString("title");
							String numOfTitle = rs.getString("numOfSperTitle");
							returnTitlesBySearch.add(title);
							returnTitlesBySearch.add(numOfTitle);
							numOfTitles--;
						}
						return returnTitlesBySearch;

					}

				}
			} else { /** from year and to year defined **/
				if (numOfTitles == 0) {
					PreparedStatement returnTitlesStatement = con
							.prepareStatement("select search.title , count(*) as numOfSperTitle " + " from libArabDB.search "
									+ "where extract(year from searchDate) between ? AND ?  " + "group by title "
									+ "order by numOfSperTitle DESC");

					returnTitlesStatement.setInt(1, fromYear);
					returnTitlesStatement.setInt(2, toYear);
					ResultSet rs = returnTitlesStatement.executeQuery();

					while (rs.next()) {
						String title = rs.getString("title");
						String numOfTitle = rs.getString("numOfSperTitle");
						returnTitlesBySearch.add(title);
						returnTitlesBySearch.add(numOfTitle);
					}
					return returnTitlesBySearch;
				} else {
					while (numOfTitles != 0) {
						PreparedStatement returnTitlesStatement = con.prepareStatement(
								"select search.title , count(*) as numOfSperTitle " + " from libArabDB.search "
										+ " where extract(year from searchDate) between ? AND ? " + "group by title "
										+ "order by numOfSperTitle DESC");

						returnTitlesStatement.setInt(1, fromYear);
						returnTitlesStatement.setInt(2, toYear);
						ResultSet rs = returnTitlesStatement.executeQuery();

						while (rs.next() & numOfTitles != 0) {
							String title = rs.getString("title");
							String numOfTitle = rs.getString("numOfSperTitle");
							returnTitlesBySearch.add(title);
							returnTitlesBySearch.add(numOfTitle);
							numOfTitles--;
						}
						return returnTitlesBySearch;

					}

				}
			}
			return returnTitlesBySearch;
		
		
		
		
	}
	
	/*public boolean favExists(java.sql.Connection con, String username, String bookID, int pageNum, int bibID)
	throws SQLException {
SelectCommand helper = new SelectCommand();
int userID = helper.returnUserByUsername(con, username).getUserID();
if (userID == -1)
	return false;

PreparedStatement userfavorite = con.prepareStatement(
		"select * FROM libArabDB.favorite "
				+ "WHERE userID=? and bookID=?"
				+ "AND pageNumber =? and bibliography_idbibliographyID=?");
userfavorite.setString(1, username);
userfavorite.setString(2, bookID);
userfavorite.setInt(3, pageNum);
userfavorite.setInt(4, bibID);
ResultSet rs = userfavorite.executeQuery();
if (!rs.next())
	return true;

return false; 

}*/
	
/**
 * 
 * @param con - connection to server
 * @param username - username of the user which owns the bibliography
 * @param bookID - favorite's bookId to search
 * @param pageNum - favorite's page number in the book
 * @param bibID - bibliography to search in ID
 * @return true if favorite exist in the bibliography, or false otherwise
 * @throws SQLException
 */
	public boolean favExists(java.sql.Connection con, String username, String bookID, int pageNum, int bibID)
			throws SQLException {
		SelectCommand helper = new SelectCommand();
		int userID = helper.returnUserByUsername(con, username).getUserID();
		if (userID == -1)
			return false;

		PreparedStatement userfavorite = con.prepareStatement("select * FROM libArabDB.favorite "
				+ "WHERE userID=? and bookID=?" + "AND pageNumber =? and bibliography_idbibliographyID=?");
		userfavorite.setInt(1, userID);
		userfavorite.setString(2, bookID);
		userfavorite.setInt(3, pageNum);
		userfavorite.setInt(4, bibID);
		ResultSet rs = userfavorite.executeQuery();
		if (!rs.next())
			return false;

		return true;

	}

/**
 * 
 * @param con - connection to server
 * @param searchDate - relevant date to return number of searches
 * @return number of searches performed in a requested week according to date
 * @throws SQLException
 */
	
	public ArrayList<SearchNum> getSearchNumber(java.sql.Connection con, Date searchDate) throws SQLException {
		ArrayList<SearchNum> searches = new ArrayList<SearchNum>();
		PreparedStatement stmt = con.prepareStatement("SELECT searchDate,count(basicSearchID) FROM libArabDB.search where searchDate between ? and DATE_ADD(?,INTERVAL 1 WEEK) Group By searchDate");
		stmt.setDate(1, searchDate);
		stmt.setDate(2, searchDate);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
		searches.add(new SearchNum(rs.getDate(1), rs.getInt(2)));
		}
		return searches;
		}
	
/**
 * 
 * @param con - connection to server
 * @param userID - user's ID
 * @param type - type of resource
 * @param BibId - bibliography ID
 * @return all favorites in a certain bibliography  of a given user
 * @throws SQLException
 */
	
	public ArrayList<Favorite> getfavOfBib(java.sql.Connection con, String userID,TypeOfResource type, int BibId) throws SQLException {
		ArrayList<Favorite> allFavOfBib = new ArrayList<Favorite>();
		SelectCommand sc = new SelectCommand();

		Integer thisUserID = sc.returnUserByUsername(con, userID).getUserID();
             
		PreparedStatement getBibStatement = con.prepareStatement(
				"select bookID ,pageLink , pageNumber , description, title, author, publisher, creationDate, other, source" + " from libArabDB.favorite"
						+ " where libArabDB.favorite.userID = ? AND bibliography_idbibliographyID = ?"
						+ " AND libArabDB.favorite.type = ?"
						+ " order by bookID , pageNumber ");
		getBibStatement.setInt(1, thisUserID);
		getBibStatement.setInt(2, BibId);
		getBibStatement.setString(3,type.name());

		ResultSet rs = getBibStatement.executeQuery();

		while (rs.next()) {

			
			Favorite favResult = new Favorite ( rs.getString("bookID"),rs.getString("pageLink"),
				rs.getString("description" ),rs.getInt("pageNumber"),rs.getString("title"),rs.getString("author"),
				rs.getString("publisher"), rs.getString("creationDate"), rs.getString("other"), rs.getString("source"));
			                                
			allFavOfBib.add(favResult);
		}
		return allFavOfBib;

	}

}


// public boolean existSearch (java.sql.Connection con, int id) throws
// SQLException{
// PreparedStatement passwordStatement = con.prepareStatement("select * from
// libArabDB.search where basicSearchID = ?");
// passwordStatement.setInt(1, id);
// User result;
// ResultSet rs = passwordStatement.executeQuery();
// //in case no match was found
// if (!rs.next())
// return false;
// else return true;
//
// }

// returnAllSearches{
// SELECT * FROM libArabDB.user b inner join libArabDB.search a on b.userID =
// a.user_userID inner join libArabDB.usertype c on b.userTypeID = c.userTypeID;
// }

// public String generateQuery(ArrayList<String> requestedColumn, String
// requestedTable, ArrayList<String> requestedJoins,
// ArrayList<String> requestedWhere){
// String result = "SELECT ";
// if (requestedColumn.size() == 1)
// result += requestedColumn.get(0);
// else for (int i=0; i<requestedColumn.size(); i++){
// result += requestedColumn.get(i);
// if (i != (requestedColumn.size()-1))
// result += ", ";
// }
//
// result +=" FROM "+requestedTable;
// if (requestedJoins != null){
//
// }
// else{
// if (requestedWhere != null){
// result+=" WHERE";
// if (requestedColumn.size() == 1)
// result += requestedColumn.get(0);
// else for (int i=0; i<requestedColumn.size(); i++){
// result += requestedColumn.get(i);
// if (i != (requestedColumn.size()-1))
// result += "AND ";
// }
// }
// }
// return result;
// }
