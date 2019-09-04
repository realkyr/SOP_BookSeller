package com.phuree.bookseller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

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
		return home;
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
