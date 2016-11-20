package lib;

public class Item {
	private String type;
	private String name;
	private String itemID;
	private String Author;
	private String Publisher;
	private String CreationDate;
	private String UrlImage;
	public Item(String type, String name, String itemID, String author) {
		super();
		this.type = type;
		this.name = name;
		this.itemID = itemID;
		Author = author;
	}
	public Item(String type, String name, String itemID) {
		super();
		this.type = type;
		this.name = name;
		this.itemID = itemID;
	}
	public Item(String name, String author ){
		super();
		this.name = name;
		this.Author = author;
	}
	
	public Item(String name, String author, String publisher, String creationDate,
			String urlImage) {
		super();
		
		Author = author;
		Publisher = publisher;
		CreationDate = creationDate;
		UrlImage = urlImage;
	}
	
	public Item( String name, String itemID, String author, String publisher, String creationDate,
			String urlImage) {
		super();
		this.name = name;
		this.itemID = itemID;
		Author = author;
		Publisher = publisher;
		CreationDate = creationDate;
		UrlImage = urlImage;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}
	public String getUrlImage() {
		return UrlImage;
	}
	public void setUrlImage(String urlImage) {
		UrlImage = urlImage;
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
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	@Override
	public String toString() {
		return "Item [type=" + type + ", name=" + name + ", itemID=" + itemID + ", Author=" + Author + ", Publisher="
				+ Publisher + ", CreationDate=" + CreationDate + ", UrlImage=" + UrlImage + "]";
	}
	
	

}
