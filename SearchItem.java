package lib;

import java.sql.Date;

public class SearchItem {
	private int basicSearchID ;
	private Date searchDate;
	private TypeOfResource TOS;
	private String author;
	private String title;
	private String freetext;
	private int user_userID;
	int fromYear;
	int toYear;
//constructor to use when inserting data into table	
	public SearchItem(Date searchDate, TypeOfResource tOS, String author, String title,
			String freetext, int user_userID, int fromYear, int toYear) {
		super();
		this.basicSearchID = 0;
		this.searchDate = searchDate;
		this.TOS = tOS;
		if (author != null) this.author = author;
			else this.author = "";
		if (title != null) this.title = title;
			else this.title = "";
		if (freetext != null) this.freetext = freetext;
			else this.freetext="";
		this.user_userID = user_userID;
		this.fromYear = fromYear;
		this.toYear = toYear;
	}
	
	//constructor to use when inserting data into table	
		public SearchItem(Date searchDate, TypeOfResource tOS, String author, String title,
				String freetext, int user_userID) {
			super();
			this.basicSearchID = 0;
			this.searchDate = searchDate;
			this.TOS = tOS;
			if (author != null) this.author = author;
				else this.author = "";
			if (title != null) this.title = title;
				else this.title = "";
			if (freetext != null) this.freetext = freetext;
				else this.freetext="";
			this.user_userID = user_userID;
		}

//constructor to use when fetching data from table
	public SearchItem(int basicSearchID, Date searchDate, TypeOfResource tOS, String author, String title,
			String freetext, int user_userID, int fromYear, int toYear) {
		super();
		this.basicSearchID = basicSearchID;
		this.searchDate = searchDate;
		TOS = tOS;
		this.author = author;
		this.title = title;
		this.freetext = freetext;
		this.user_userID = user_userID;
		this.fromYear = fromYear;
		this.toYear = toYear;
	}



	public int getBasicSearchID() {
		return basicSearchID;
	}



	public void setBasicSearchID(int basicSearchID) {
		this.basicSearchID = basicSearchID;
	}



	public Date getSearchDate() {
		return searchDate;
	}



	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}



	public TypeOfResource getTOS() {
		return TOS;
	}



	public void setTOS(TypeOfResource tOS) {
		TOS = tOS;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getFreetext() {
		return freetext;
	}



	public void setFreetext(String freetext) {
		this.freetext = freetext;
	}



	public int getUser_userID() {
		return user_userID;
	}



	public void setUser_userID(int user_userID) {
		this.user_userID = user_userID;
	}



	public int getFromYear() {
		return fromYear;
	}



	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}



	public int getToYear() {
		return toYear;
	}



	public void setToYear(int toYear) {
		this.toYear = toYear;
	}

	
	
	
}