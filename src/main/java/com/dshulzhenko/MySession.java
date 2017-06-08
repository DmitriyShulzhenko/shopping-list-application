package com.dshulzhenko;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public final class MySession extends WebSession {

	public MySession(Request request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ShoppingLists shoppingLists = new ShoppingLists();
	private String selectedShoppingList;

	public String getSelectedShoppingList() {
		return selectedShoppingList;
	}

	public void setSelectedShoppingList(String selectedShoppingList) {
		this.selectedShoppingList = selectedShoppingList;
	}

	public ShoppingLists getShoppingLists() {
		return shoppingLists;
	}

	public void setShoppingLists(ShoppingLists shoppingLists) {
		this.shoppingLists = shoppingLists;
	}
	
	public static MySession get() {
		return (MySession)Session.get();
	}
}
