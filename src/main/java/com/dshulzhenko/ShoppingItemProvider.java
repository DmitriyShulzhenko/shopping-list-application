package com.dshulzhenko;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ShoppingItemProvider implements ISortableDataProvider<Object, Object> {
	ArrayList<ShoppingItem> shoppingList;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShoppingItemProvider(String listName){
		shoppingList = HomePage.lists.getShoppingLists().get(listName).getShoppingList();
	}


	public Iterator iterator(long first, long count) {
		return shoppingList.iterator();
	}

	public long size() {
		// TODO Auto-generated method stub
		return shoppingList.size();
	}

	@Override
	public IModel model(final Object object) {
		 return new AbstractReadOnlyModel<ShoppingItem>() {
	            @Override
	            public ShoppingItem getObject() {
	                return (ShoppingItem) object;
	            }
	     };
	}


	@Override
	public void detach() {
		// TODO Auto-generated method stub		
	}


	@Override
	public ISortState getSortState() {
		// TODO Auto-generated method stub
		return null;
	}

}
