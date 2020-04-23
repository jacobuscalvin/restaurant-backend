package com.dropthefat.dtfbackend.Services;

import java.util.ArrayList;
import java.util.List;
// import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import com.dropthefat.dtfbackend.Models.Menu;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class MenuServices {
  
  public List<Menu> getAllMenu() throws InterruptedException, ExecutionException{
    Firestore db = FirestoreClient.getFirestore();
    List<Menu> result = new ArrayList<>();
    ApiFuture<QuerySnapshot> future = db.collection("menu").get();
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();
    for (QueryDocumentSnapshot doc : documents) {
      Menu menu = doc.toObject(Menu.class);
      result.add(menu);
    }
    return result;
  }

}