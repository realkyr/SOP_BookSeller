package com.phuree.bookseller;


public class BookFactory {
	static int id = 1;
	
	public static NoteBook createBook() {
		NoteBook book = new NoteBook();
		book.setSerial(BookFactory.generateID());
		book.setSize(5.8, 8.3);
		book.setPages(20);
		book.setPrice(2 * 20);
		return book;
	}
	
	public static NoteBook createBook(int pages, double width, double height) {
		NoteBook book = new NoteBook();
		book.setSerial(BookFactory.generateID());
		book.setSize(width, height);
		book.setPages(pages);
		book.setPrice(2 * pages);
		return book;
	}
	
	public static NoteBook createBook(int pages) {
		NoteBook book = new NoteBook();
		book.setSerial(BookFactory.generateID());
		book.setSize(5.8, 8.3);
		book.setPages(pages);
		book.setPrice(2 * pages);
		return book;
	}

	public static ComicBook createBook(int pages, String title) {
		ComicBook book = new ComicBook();
		book.setSerial(BookFactory.generateID());
		book.setTitle(title);
		book.setSize();
		book.setPages(pages);
		book.setPrice(5 * pages);
		return book;
	}

	private static String generateID() {
		String id = Integer.toString(BookFactory.id);
		BookFactory.id = BookFactory.id + 1;
		return id;
	}
}
