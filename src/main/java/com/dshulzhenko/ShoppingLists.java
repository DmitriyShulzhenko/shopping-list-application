package com.dshulzhenko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingLists {
	private Map<String,ArrayList<ShoppingItem>> shoppingLists = new HashMap<String,ArrayList<ShoppingItem>>();
	
	public ShoppingLists() {}

	public Map<String, ArrayList<ShoppingItem>> getShoppingLists() {
		return shoppingLists;
	}

	public void setShoppingLists(HashMap<String, ArrayList<ShoppingItem>> shoppingLists) {
		this.shoppingLists = shoppingLists;
	}

	@Override
	public String toString() {
		return "ShoppingLists [shoppingLists=" + shoppingLists + "]";
	}
	
	public ArrayList<ShoppingItem> getShoppingList(String listName){
		return shoppingLists.get(listName);
	}
	
	public void addShoppingList (String listName){
		shoppingLists.put(listName, new ArrayList<ShoppingItem>());
	}
	
	public void removeShoppingList (String listName){
		shoppingLists.remove(listName);
	}
	
	public void addShoppingItem (String listName, ShoppingItem item){
		shoppingLists.get(listName).add(item);
	}
	
	public void removeShoppingItem (String listName, ShoppingItem item){
		shoppingLists.get(listName).remove(item);
	}
	
	public boolean containsKey (String listName){
		return shoppingLists.containsKey(listName);
	}
}
