package lib;

import java.util.ArrayList;

public class FavoriteList {
	private ArrayList<Item> favorites;

	public ArrayList<Item> getFavorites() {
		return favorites;
	}

	public void setFavorites(ArrayList<Item> favorites) {
		this.favorites = favorites;
	}

	public FavoriteList() {
		super();
	}
	
	public void addItemToFavorites(Item item){
		this.favorites.add(item);
	}
	
	public void deleteItemFromFavorites(Item item){
		favorites.remove(item.getName());
	}
}
