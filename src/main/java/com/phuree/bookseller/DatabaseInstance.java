package com.phuree.bookseller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class DatabaseInstance {
	private static Firestore db;
	
	public static Firestore getInstance() {
		if (db == null) {
			try {
				InputStream serviceAccount = new FileInputStream("serviceAccount.json");
				GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
				FirebaseOptions options = new FirebaseOptions.Builder()
					    .setCredentials(credentials)
					    .build();
				FirebaseApp.initializeApp(options);
				db = FirestoreClient.getFirestore();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return db;
	}
}
