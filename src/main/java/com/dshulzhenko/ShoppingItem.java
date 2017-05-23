package com.dshulzhenko;

public class ShoppingItem {
	private final String name;
	private final String quanity;
	private final String comment;

	private ShoppingItem(String name, String quanity, String comment) {
		this.name = name;
		this.quanity = quanity;
		this.comment = comment;
	}

//	public ShoppingItem(ShoppingItem item) {
//
//	}

	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	public String getQuanity() {
		return quanity;
	}

//	public void setQuanity(String quanity) {
//		this.quanity = quanity;
//	}

	public String getComment() {
		return comment;
	}

//	public void setComment(String comment) {
//		this.comment = comment;
//	}

	@Override
	public String toString() {
		return "ShoppingItem [name=" + name + ", quanity=" + quanity + ", comment=" + comment + "]";
	}

	public static ShoppingItemBuilder builder() {
		return new ShoppingItemBuilder();
	}

	public static class ShoppingItemBuilder {
		private String name;
		private String quanity;
		private String comment;

		public ShoppingItemBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ShoppingItemBuilder quanity(String quanity) {
			this.quanity = quanity;
			return this;
		}

		public ShoppingItemBuilder comment(String comment) {
			this.comment = comment;
			return this;
		}

		public ShoppingItem build() {
			return new ShoppingItem(name, quanity, comment);
		}

	}

}
