package com.dshulzhenko;

public class ShoppingItem {
	private String name;
	private int quanity;
	private String comment;
	
	public ShoppingItem() {}
	
	public ShoppingItem(ShoppingItem item) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ShoppingItem [name=" + name + ", quanity=" + quanity + ", comment=" + comment + "]";
	}
	
	
	
}
