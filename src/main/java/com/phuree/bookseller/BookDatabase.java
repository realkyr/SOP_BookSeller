package com.phuree.bookseller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

public class BookDatabase {
	public static String addNotebookToDatabase(NoteBook book) {
		Firestore db = DatabaseInstance.getInstance();
		Map<String, Object> data = new HashMap<>();
		data.put("pages", book.getPages());
		data.put("width", book.getWidth());
		data.put("height", book.getHeight());
		data.put("price", book.getPrice());
		data.put("type", "notebook");
		ApiFuture<DocumentReference> addedDocRef = db.collection("books").add(data);
		try {
			return addedDocRef.get().getId();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	public static String addComicBookToDatabase(ComicBook book) {
		Firestore db = DatabaseInstance.getInstance();
		Map<String, Object> data = new HashMap<>();
		data.put("pages", book.getPages());
		data.put("width", book.getWidth());
		data.put("height", book.getHeight());
		data.put("price", book.getPrice());
		data.put("title", book.getTitle());
		data.put("type", "comic book");
		ApiFuture<DocumentReference> addedDocRef = db.collection("books").add(data);
		try {
			return addedDocRef.get().getId();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
}
