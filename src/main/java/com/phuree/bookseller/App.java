package com.phuree.bookseller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

@RestController
@EnableAutoConfiguration
public class App {

	@RequestMapping("/")
	String home() {
		String home = "";
		home += "<h1>API สั่งพิมพ์หนังสือ</h1>";
		home += "<h2>สามารถสั่งผลิตสมุดโน้ตได้ผ่าน GET</h2>";
		home += "<code>http://localhost:8080/buybook?type=notebook&pages=[จำนวนหน้าที่ต้องการสั่ง]&width=[ความกว้างเป็นนิ้ว]&height=[ความยาวเป็นนิ้ว]</code>";
		home += "<h2>สามารถสั่งพิมพ์การ์ตูนได้ผ่าน GET</h2>";
		home += "<code>http://localhost:8080/buybook?type=comicbook&pages=[จำนวนหน้าที่ต้องการสั่ง]&title=[ชื่อหนังสือการ์ตูน]</code>";
		home += "<h2>สามารถดูรายละเอียดการสั่งซื้อได้จาก</h2>";
		home += "<code>http://localhost:8080/book/[id ของหนังสือที่สั่ง]</code>";
		home += "<h2>สามารถดูรายชื่อหนังสือทั้งหมดได้จาก</h2>";
		home += "<code>http://localhost:8080/allbooks</code>";
		return home;
	}
	
	@RequestMapping("/allbooks")
	String allbooks() {
		String results = "[";
		Firestore db = DatabaseInstance.getInstance();
		ApiFuture<QuerySnapshot> future = db.collection("books").get();
		List<QueryDocumentSnapshot> documents;
		try {
			documents = future.get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				double width = (double) document.getData().get("width");
				double height = (double) document.getData().get("height");
				int pages = App.longToInt((long) document.getData().get("pages"));
				int price = App.longToInt((long) document.getData().get("price"));
				if (document.exists()) {
					Book book;
					if (document.getData().get("type").equals("notebook")) {
						book = new NoteBook(
								document.getId(),
								width,
								height,
								pages,
								price
								);
					} else {
						book = new ComicBook(
								document.getId(),
								(String) document.getData().get("title"),
								pages,
								price
								);
					}
					results += book.getDetails();
					results += ", ";
				}
			}
			if (results != null && results.length() > 1) {
				results = results.substring(0, results.length() - 2);
		    }
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		results += "]";
		return results;
	}
	
	@RequestMapping(path="/book/{id}")
	String book(@PathVariable("id") String id) {
		Firestore db = DatabaseInstance.getInstance();
		DocumentReference docRef = db.collection("books").document(id);
		ApiFuture<DocumentSnapshot> future = docRef.get();
		try {
			DocumentSnapshot document = future.get();
			double width = (double) document.getData().get("width");
			double height = (double) document.getData().get("height");
			int pages = App.longToInt((long) document.getData().get("pages"));
			int price = App.longToInt((long) document.getData().get("price"));
			if (document.exists()) {
				Book book;
				if (document.getData().get("type").equals("notebook")) {
					book = new NoteBook(
							document.getId(),
							width,
							height,
							pages,
							price
							);
				} else {
					book = new ComicBook(
							document.getId(),
							(String) document.getData().get("title"),
							pages,
							price
							);
				}
				return book.getDetails();
			} else {
				return "No such document!";
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	private static int longToInt(long number) {
		int i_num = (int) number;
		return i_num;
	}
	
	@RequestMapping("/buybook")
	String buybook(
			@RequestParam String type,
			@RequestParam(required=false) String pages,
			@RequestParam(required=false) String width,
			@RequestParam(required=false) String height,
			@RequestParam(required=false) String title
	) {
//		Book book = BookFactory.createBook();
//		return book.getDetails();
		if (type.equals("notebook")) {
			if ((width != null && height == null) || width == null && height != null) {
				return "{\"error\": \"กรุณากรอกทั้งความกว้างและสูง\"}";
			}
			if (pages != null && (width == null && height == null)) {
				NoteBook book = BookFactory.createBook(Integer.parseInt(pages));
				return book.getDetails();
			}
			if (pages == null && width == null && height == null) {
				NoteBook book = BookFactory.createBook();
				return book.getDetails();
			}
			int pages_num = Integer.parseInt(pages);
			double width_num = Double.parseDouble(width);
			double height_num = Double.parseDouble(height);
			NoteBook book = BookFactory.createBook(pages_num, width_num, height_num);
			return book.getDetails();
		} else if (type.equals("comicbook")) {
			if (width != null || height != null) {
				return "{\"error\": \"ห้ามกรอกทั้งความกว้างและสูง\"}";
			}
			if (pages == null || title == null) {
				return "{\"error\": \"กรุณากรอกข้อมูลให้ครบถ้วน\"}";
			}
			ComicBook book = BookFactory.createBook(Integer.parseInt(pages), title);
			return book.getDetails();
		} else {
			NoteBook book = BookFactory.createBook();
			return book.getDetails();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
