package com.dropthefat.dtfbackend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.dropthefat.dtfbackend.Models.Menu;
import com.dropthefat.dtfbackend.Models.Response;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
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
      menu.setId(doc.getId());
      result.add(menu);
    }
    return result;
  }

  public Menu getMenuById(String id) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    DocumentReference docRef = db.collection("menu").document(id);
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot doc = future.get();
    if(doc.exists()){
      Menu menu = doc.toObject(Menu.class);
      menu.setId(doc.getId());
      return menu;
    }else{
      return null;
    }
  }

  public Response addMenu(Menu menu) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    ApiFuture<DocumentReference> docRef = db.collection("menu").add(menu);
    String refId = docRef.get().getId();
    Response resp = new Response();
    resp.setStatus(true);
    resp.setDocId(refId);
    return resp;
  }

  public Response updateMenu(String id,Menu menu) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    ApiFuture<DocumentSnapshot> future = db.collection("menu").document(id).get();
    if(future.get().exists()){
      ApiFuture<WriteResult> result = db.collection("menu").document(id).set(menu);
      Response resp = new Response();
      resp.setStatus(true);
      resp.setUpdateTime(result.get().getUpdateTime());
      resp.setDocId(future.get().getId());
      return resp;
    }else{
      return new Response(false, "Document not found!");
    }
    // ApiFuture<WriteResult> result = docRef.set(menu);
  }


  public Response deleteMenu(String id) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    //First, we get the menu reference, to check whether the document is exist or not.
    ApiFuture<DocumentSnapshot> future = db.collection("menu").document(id).get();
    if(future.get().exists()){
      ApiFuture<WriteResult> ref = db.collection("menu").document(id).delete();
      Response resp = new Response();
      resp.setStatus(true);
      resp.setUpdateTime(ref.get().getUpdateTime());
      return resp;
    }else{
      Response resp = new Response(false, "Document not found");
      return resp;
    }

  }

  

}