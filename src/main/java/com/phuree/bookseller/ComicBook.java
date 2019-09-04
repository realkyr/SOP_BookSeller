package com.phuree.bookseller;

public class ComicBook implements Book {
	String serial;
	String title;
	double width;
	double height;
	int pages;
	int price;

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
		String details = String.format("{\"serailID\": \"%s\"", this.serial);
		return details;
	}
}