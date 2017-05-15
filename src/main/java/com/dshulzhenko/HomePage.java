package com.dshulzhenko;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

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
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

public class HomePage extends WebPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static ShoppingLists lists = new ShoppingLists();

    public String listName;
    public ArrayList<String> choices = new ArrayList<String>();
    public final IModel<String> dropdownModel = new PropertyModel<String>(this, "listName");
    final IModel<List<String>> choicesModel = new PropertyModel<List<String>>(this,"choices");

    public HomePage() {	
    	FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
    	
    	final ShoppingListForm shoppingListForm= new ShoppingListForm("shoppingListForm");
    	add(shoppingListForm);
    	shoppingListForm.setOutputMarkupId(true);     
        
        final ShoppingItemForm shoppingItemForm= new ShoppingItemForm("shoppingItemForm");
    	add(shoppingItemForm);
    	shoppingItemForm.setOutputMarkupId(true);
            
        // Table
    	List <IColumn> columns = new ArrayList<IColumn>();
        columns.add(new PropertyColumn(new Model<String>("Name"), "name"));
        columns.add(new PropertyColumn(new Model<String>("Quanity"), "quanity"));
        columns.add(new PropertyColumn(new Model<String>("Comment"), "comment"));
        
        final ShoppingItemProvider shoppingItemProvider = new ShoppingItemProvider();
         
        final DefaultDataTable table = new DefaultDataTable("datatable", columns, shoppingItemProvider, 10);       
        table.setOutputMarkupPlaceholderTag(true);        
        add(table);
        
        // DropDown of Shopping Lists
        final DropDownChoice<String> shoppingLists = new DropDownChoice<String>("shoppingLists", dropdownModel,
        		choicesModel);
        shoppingLists.add(new AjaxFormComponentUpdatingBehavior("change") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
				shoppingItemProvider.setShoppingList((String)dropdownModel.getObject());
		        target.add(table);		        		
			}
        });
        add (shoppingLists);
        
        // Button for List delete
        Button deleteList = new Button("deleteList") {
            /**
			 * 
			 */
        		
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				MySession.get().getShoppingLists().removeShoppingList((String)dropdownModel.getObject());
                choices.remove((String)dropdownModel.getObject());
            }
        };
     
        deleteList.setDefaultFormProcessing(false);
        shoppingListForm.add(deleteList);
    }
    /**
     * A form that allows a user to add a comment.
     */
    public final class ShoppingItemForm extends Form<ValueMap> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ShoppingItemForm(final String id) {
            super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
            setMarkupId("shoppingItemForm");
            final FormComponent<String> name = new TextField<String>("name").setType(String.class);
            name.setRequired(true);
            add(name);
            add(new TextField<String>("quanity").setType(String.class));
            add(new TextField<String>("comment").setType(String.class));
        }
        /**
         * Show the resulting valid edit
         */
        @Override
        public final void onSubmit() {
            ValueMap values = getModelObject();
            ShoppingItem shoppingItem = new ShoppingItem();
            shoppingItem.setName((String)values.get("name"));
            shoppingItem.setQuanity((String) values.get("quanity"));
            shoppingItem.setComment((String)values.get("comment"));      
			try {
				MySession.get().getShoppingLists().addShoppingItem((String) dropdownModel.getObject(), shoppingItem);
			} catch (NullPointerException e) {
				error("You haven't choosed list!");
			}
            values.put("name", "");
            values.put("quanity", "");
            values.put("comment", "");          
            
        }
    }
    public final class ShoppingListForm extends Form<ValueMap> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ShoppingListForm(final String id) {
            super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
            setMarkupId("shoppingListForm");
            final FormComponent<String> listName =new TextField<String>("listname").setType(String.class);
            listName.setRequired(true);
            listName.add(new ListNameValidator());
            add(listName);
        }
        /**
         * Show the resulting valid edit
         */
        @Override
        public final void onSubmit() {
            ValueMap values = getModelObject();
            MySession.get().getShoppingLists().addShoppingList((String)values.get("listname"));
            choices.add((String)values.get("listname"));
            values.put("listname", "");
        }
    }

}
