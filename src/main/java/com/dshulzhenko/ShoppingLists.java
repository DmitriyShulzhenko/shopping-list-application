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
	
}
