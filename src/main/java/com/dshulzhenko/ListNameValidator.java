package com.dshulzhenko;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class ListNameValidator implements IValidator<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void validate(IValidatable<String> validatable) {
		final String listName = validatable.getValue();
		if (MySession.get().getShoppingLists().containsKey(listName)){
			validatable.error(new ValidationError().setMessage("List name is already taken!"));
		}
	}

}
