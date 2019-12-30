package com.example.demo.service;

import java.security.PublicKey;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.object.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.core.Path;

@Service
public class FirebaseServices {
	
	public String saveUserDetails(Person message) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("users").document(message.getName()).set(message);
        return future.get().getUpdateTime().toString();
    }

	public Person getUserDetails(String name) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("users").document(name);
		// asynchronously retrieve the document
		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document = future.get();
		Person person = null;
		if (document.exists()) {
		  // convert document to POJO
		  person = document.toObject(Person.class);
		  System.out.println(person);
		  return person;
		} else {
		  System.out.println("No such document!");
		  return null;
		}
	}
	
	public String deleteUser(String name) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = db.collection("users").document(name).delete();
		return writeResult.get().getUpdateTime().toString();
	}
}
