package lib;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

public class webMethods {
	
	
	public ArrayList<String> returnResourceList(java.sql.Connection con, int fromYear, int toYear) throws SQLException {

		ArrayList<String> returnCategoriesBySearch = new ArrayList<String>();
	//	java.sql.Statement returnCategoriesState = con.createStatement();

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
			} else { /** fromYear is zero , toYear is x (less than x ) **/
					PreparedStatement returnCategoryStatment= con
							.prepareStatement("select search.typeOfResource , count(*) as numOfResources "
									+ " from libArabDB.search " + "where extract(year from searchDate) <= ?   "
									+ " group by typeOfResource " + "order by numOfResources DESC");

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
		if (toYear == 0) { /** from year x to inf **/
				PreparedStatement returnCategoryStatment = con
						.prepareStatement("select search.typeOfResource , count(*) as numOfResources " + " from libArabDB.search "
								+ "where extract(year from searchDate) >= ?   " + "group by typeOfResource "
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

		} else { /** from year and to year defined **/
			
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
	
	/*public ArrayList<Favorite> getBib(java.sql.Connection con , String userID , int BibId ) throws SQLException 
	{
		ArrayList<Favorite> allFavOfBib=new ArrayList<>();
		SelectCommand sc= new SelectCommand();
		
		Integer thisUserID = sc.returnUserByUsername(con,userID).getUserID();
		
		
		PreparedStatement getBibStatement=con.prepareStatement("select favoriteID, bookID , type , pageLink , pageNumber , description" 
				                                  + " from libArabDB.favorite"
				                                  + " where libArabDB.favorite.userID = ? AND bibliography_idbibliographyID = ? ");
		getBibStatement.setInt(1,thisUserID);
		getBibStatement.setInt(2, BibId);
		
		
		ResultSet rs=getBibStatement.executeQuery();
		
		while(rs.next())
		{
			Favorite favResult = new Favorite(rs.getInt("favoriteID"), rs.getString("bookID"), rs.getString("pageLink"),
					rs.getString("description"),rs.getInt("pageNumber"));
			allFavOfBib.add(favResult);
		}
		return allFavOfBib;
				
		
		
	}*/
	
	public ArrayList<String> returnFreeTextList(java.sql.Connection con, int fromYear, int toYear) throws SQLException {

		ArrayList<String> returnFreeTextBySearch = new ArrayList<String>();
	//	java.sql.Statement returnCategoriesState = con.createStatement();

		if (fromYear == 0) {
			if (toYear == 0) /** return all years **/
			{
					PreparedStatement returnFreeTextStatment = con 
							.prepareStatement("SELECT search.freeText , count(*) as numOfFreeTexts"
                                    + " FROM libArabDB.search "
                                    + " group by freeText"
                                    + " order by numOfFreeTexts ");
					ResultSet rs = returnFreeTextStatment.executeQuery();
					while (rs.next()) {
						String FreeText = rs.getString("freeText");
						String numOfFreetext = rs.getString("numOfFreeTexts");
						returnFreeTextBySearch.add(FreeText);
						returnFreeTextBySearch.add(numOfFreetext);
					}
					return returnFreeTextBySearch;
			} else { /** fromYear is zero , toYear is x (less than x ) **/
					PreparedStatement returnFreeTextStatment= con
							.prepareStatement("select search.freeText , count(*) as numOfFreeTexts "
									+ " from libArabDB.search " + "where extract(year from searchDate) <= ?   "
									+ " group by freeText " + "order by numOfFreeTexts DESC");

					returnFreeTextStatment.setInt(1, toYear);

					ResultSet rs = returnFreeTextStatment.executeQuery();

					while (rs.next()) {
						String FreeText = rs.getString("freeText");
						String numOfFreeTexts = rs.getString("numOfFreeTexts");
						returnFreeTextBySearch.add(FreeText);
						returnFreeTextBySearch.add(numOfFreeTexts);
					}
					return returnFreeTextBySearch;

				}
			}
		if (toYear == 0) { /** from year x to inf **/
				PreparedStatement returnFreeTextStatment = con
						.prepareStatement("select search.freeText , count(*) as numOfFreeTexts " + " from libArabDB.search "
								+ "where extract(year from searchDate) >= ?   " + "group by freeText "
								+ "order by numOfFreeTexts DESC");

				returnFreeTextStatment.setInt(1, fromYear);

				ResultSet rs = returnFreeTextStatment.executeQuery();

				while (rs.next()) {
					String FreeText = rs.getString("freeText");
					String numOfFreeText = rs.getString("numOfFreeTexts");
					returnFreeTextBySearch.add(FreeText);
					returnFreeTextBySearch.add(numOfFreeText);
				}
				return returnFreeTextBySearch;

		} else { /** from year and to year defined **/
			
				PreparedStatement returnFreeTextStatment = con
						.prepareStatement("SELECT search.freeText , count(*) as numOfFreeTexts"
                                           + " FROM libArabDB.search"
                                           + " where extract(year from searchDate) Between ? AND ? "
                                           + " group by freeText"
                                           + " order by numOfFreeTexts");

				returnFreeTextStatment.setInt(1, fromYear);
				returnFreeTextStatment.setInt(2, toYear);
				ResultSet rs = returnFreeTextStatment.executeQuery();

				while (rs.next()) {
					String FreeText = rs.getString("freeText");
					String numOfFreeText = rs.getString("numOfFreeTexts");
					returnFreeTextBySearch.add(FreeText);
					returnFreeTextBySearch.add(numOfFreeText);
				}
				return returnFreeTextBySearch;
			}

	}
	
}
