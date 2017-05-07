package com.dshulzhenko;

import java.util.ArrayList;

public class ShoppingList {
	private ArrayList<ShoppingItem> shoppingList = new ArrayList<ShoppingItem>();

	public ShoppingList() {
	}

	public ArrayList<ShoppingItem> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(ArrayList<ShoppingItem> shoppingList) {
		this.shoppingList = shoppingList;
	}

	@Override
	public String toString() {
		return "ShoppingList [shoppingList=" + shoppingList + "]";
	}	
	
}
