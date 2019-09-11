package com.phuree.bookseller;

public class ComicBook implements Book {
	String serial;
	String title;
	double width;
	double height;
	int pages;
	int price;
	
	public ComicBook() {}
	
	public ComicBook(String serial, String title, int pages, int price) {
		super();
		this.serial = serial;
		this.title = title;
		this.pages = pages;
		this.price = price;
		this.setSize();
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setSize() {
		this.setHeight(6.625);
		this.setWidth(10.25);
	}
	
	public String getDetails() {
		String details = String.format("{\"serialID\": \"%s\"", this.serial);
		details += ",\"size\": ";
		details += String.format("\"%1$,.2f inches ", this.width);
		details += String.format("x %1$,.2f inches\"", this.height);
		details += String.format(",\"pages\": %d", this.pages);
		details += String.format(",\"price\": %d", this.price);
		details += String.format(", \"title\": \"%s\"", this.title);
		details += ", \"type\": \"comic book\"}";
		return details;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}