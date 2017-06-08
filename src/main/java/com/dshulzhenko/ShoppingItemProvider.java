package com.dshulzhenko;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ShoppingItemProvider implements ISortableDataProvider<ShoppingItem, Object> {
	ArrayList<ShoppingItem> shoppingList = new ArrayList<ShoppingItem>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShoppingItemProvider(){}

	public void setShoppingList (String listName){
		if (listName == null){
			shoppingList = new ArrayList<ShoppingItem>();
		} else {
		shoppingList = MySession.get().getShoppingLists().getShoppingList(listName);
		}
	}
	
	public Iterator<ShoppingItem> iterator(long first, long count) {
		return shoppingList.iterator();
	}

	public long size() {
		// TODO Auto-generated method stub
		return shoppingList.size();
	}

	@Override
	public IModel<ShoppingItem> model(final ShoppingItem shoppingItem) {
		return new AbstractReadOnlyModel<ShoppingItem>() {
            /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			@Override
            public ShoppingItem getObject() {
                return shoppingItem;
            }
     };
	}


	@Override
	public void detach() {
		// TODO Auto-generated method stub		
	}


	@Override
	public ISortState<Object> getSortState() {
		// TODO Auto-generated method stub
		return null;
	}

}
