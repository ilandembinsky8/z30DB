package lib;

import java.sql.Date;

public class SearchNum {
	private Date searchDay;
	private int search_counter;
	public SearchNum(Date searchDay, int search_counter) {
		super();
		this.searchDay = searchDay;
		this.search_counter = search_counter;
	}
	public Date getSearchDay() {
		return searchDay;
	}
	public void setSearchDay(Date searchDay) {
		this.searchDay = searchDay;
	}
	public int getSearch_counter() {
		return search_counter;
	}
	public void setSearch_counter(int search_counter) {
		this.search_counter = search_counter;
	}
}
