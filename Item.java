package lib;

public class Item {
	private String type;
	private String name;
	private int itemID;
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getItemID() {
		return itemID;
	}

	public Item(String type, String name, int itemID) {
		super();
		this.type = type;
		this.name = name;
		this.itemID = itemID;
	}
}
