package com.dshulzhenko;

import java.util.HashMap;

public class ShoppingLists {
	private HashMap<String,ShoppingList> shoppingLists = new HashMap<String,ShoppingList>();
	
	public ShoppingLists() {}

	public HashMap<String, ShoppingList> getShoppingLists() {
		return shoppingLists;
	}

	public void setShoppingLists(HashMap<String, ShoppingList> shoppingLists) {
		this.shoppingLists = shoppingLists;
	}

	@Override
	public String toString() {
		return "ShoppingLists [shoppingLists=" + shoppingLists + "]";
	}
	
	public ShoppingList getShoppingList(String listName){
		return shoppingLists.get(listName);
	}
	
	public void addShoppingList (String listName){
		shoppingLists.put(listName, new ShoppingList());
	}
	
	public void removeShoppingList (String listName){
		shoppingLists.remove(listName);
	}
	
	public void addShoppingItem (String listName, ShoppingItem item){
		shoppingLists.get(listName).addItem(item);
	}
	
	public void removeShoppingItem (String listName, ShoppingItem item){
		shoppingLists.get(listName).removeItem(item);
	}
	
	public boolean containsKey (String listName){
		return shoppingLists.containsKey(listName);
	}
}
