package com.phuree.bookseller;


public class BookFactory {
	static int id = 1;
	
	public static NoteBook createBook() {
		NoteBook book = new NoteBook();
		book.setSize(5.8, 8.3);
		book.setPages(20);
		book.setPrice(2 * 20);
		String id = BookDatabase.addNotebookToDatabase(book);
		book.setSerial(id);
		return book;
	}
	
	public static NoteBook createBook(int pages, double width, double height) {
		NoteBook book = new NoteBook();
		book.setSize(width, height);
		book.setPages(pages);
		book.setPrice(2 * pages);
		String id = BookDatabase.addNotebookToDatabase(book);
		book.setSerial(id);
		return book;
	}
	
	public static NoteBook createBook(int pages) {
		NoteBook book = new NoteBook();
		book.setSize(5.8, 8.3);
		book.setPages(pages);
		book.setPrice(2 * pages);
		String id = BookDatabase.addNotebookToDatabase(book);
		book.setSerial(id);
		return book;
	}

	public static ComicBook createBook(int pages, String title) {
		ComicBook book = new ComicBook();
		book.setTitle(title);
		book.setSize();
		book.setPages(pages);
		book.setPrice(5 * pages);
		String id = BookDatabase.addComicBookToDatabase(book);
		book.setSerial(id);
		return book;
	}
}
