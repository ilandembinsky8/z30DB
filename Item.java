package lib;

public class Item {
	private String type;
	private String name;
	private int itemID;
	private String Author;
	public Item(String type, String name, int itemID, String author) {
		super();
		this.type = type;
		this.name = name;
		this.itemID = itemID;
		Author = author;
	}
	public Item(String type, String name, int itemID) {
		super();
		this.type = type;
		this.name = name;
		this.itemID = itemID;
	}
	public Item(String name, String author) {
		super();
		this.name = name;
		this.Author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}

}
