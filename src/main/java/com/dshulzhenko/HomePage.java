package com.dshulzhenko;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.WebPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.crypt.StringUtils;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.markup.html.list.PropertyListView;

public class HomePage extends WebPage {
	/** A global list of all comments from all users across all sessions */
    public static ShoppingLists lists = new ShoppingLists();
    public String listName;
    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public HomePage() {
    	HashMap<String, ShoppingList> start = new HashMap<String, ShoppingList>();
    	start.put("list1", new ShoppingList());
    	start.put("list2", new ShoppingList());
    	lists.setShoppingLists(start);
    	
    	//Form<?> form = new Form("form");
        //add(form);
        final DropDownChoice<String> lists123 = new DropDownChoice<String>("lists123", Model.of(listName),
                new ArrayList<String>(lists.getShoppingLists().keySet())) {
        	 @Override
             protected CharSequence getDefaultChoice(String selectedValue) {
 // put return type as empty so that default value will be removed
                 return " ";
             }

        };
        add (lists123);
        // Add comment form
        add(new ShoppingItemForm("shoppingItemForm"));
        // Add commentListView of existing comments
        /*add(new PropertyListView<ShoppingItem>("shoppingItems", shoppingList) {
            @Override
            public void populateItem(final ListItem<ShoppingItem> listItem) {
                listItem.add(new Label("name"));
                listItem.add(new Label("quanity"));
                listItem.add(new MultiLineLabel("comment"));
            }
        }).setVersioned(false);*/

        listName="list1";
        
        add(new Label("listNameValue",lists123.getValue()));
        List<ICellPopulator> columns = new ArrayList<ICellPopulator>();
        
        columns.add(new PropertyPopulator("name"));
        columns.add(new PropertyPopulator("quanity"));
        columns.add(new PropertyPopulator("comment"));
        
        add(new DataGridView("shoppingItems", columns, new ShoppingItemProvider(listName)));
    }
    /**
     * A form that allows a user to add a comment.
     */
    public final class ShoppingItemForm extends Form<ValueMap> {
        public ShoppingItemForm(final String id) {
            // Construct form with no validation listener
            super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
            // this is just to make the unit test happy
            setMarkupId("shoppingItemForm");
            // Add text entry widget
            add(new TextField<String>("name").setType(String.class));
            add(new TextField<String>("quanity").setType(String.class));
            // Add simple automated spam prevention measure.
            add(new TextField<String>("comment").setType(String.class));
        }
        /**
         * Show the resulting valid edit
         */
        @Override
        public final void onSubmit() {
            ValueMap values = getModelObject();
            // check if the honey pot is filled
            // Construct a copy of the edited comment
            ShoppingItem shoppingItem = new ShoppingItem();
            // Set date of comment to add
            shoppingItem.setName((String)values.get("name"));
            shoppingItem.setQuanity(Integer.valueOf((String) values.get("quanity")));
            shoppingItem.setComment((String)values.get("comment"));
           
            lists.getShoppingLists().get(listName).getShoppingList().add(0,shoppingItem);
            // Clear out the text component
            values.put("text", "");
        }
    }
    /**
     * Clears the comments.
     */
   // public static void clear() {
   // 	lists.clear();
  //  }
}
