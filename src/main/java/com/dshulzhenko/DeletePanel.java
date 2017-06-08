package com.dshulzhenko;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

class DeletePanel extends Panel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @param id
     *            component id
     * @param model
     *            model for contact
     */
    public DeletePanel(String id, IModel<ShoppingItem> model) {
        super(id, model);
        add(new Link<ShoppingItem>("select") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
				MySession session = MySession.get();
				ShoppingItem selectedItem = (ShoppingItem)getParent().getDefaultModelObject();
				String selectedList = session.getSelectedShoppingList();
				session.getShoppingLists().removeShoppingItem(selectedList, selectedItem);
            }
        });
    }
}
