package com.dropthefat.dtfbackend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dropthefat.dtfbackend.Models.Order;
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
public class OrderServices {

  public List<Order> getAllOrder() throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    List<Order> result = new ArrayList<>();
    ApiFuture<QuerySnapshot> future = db.collection("order").get();
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();
    for (QueryDocumentSnapshot doc : documents){
      Order order = doc.toObject(Order.class);
      order.setId(doc.getId());
      result.add(order);
    }
    return result;
  }

  public Order getOrderById(String id) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    ApiFuture<DocumentSnapshot> future = db.collection("order").document(id).get();
    DocumentSnapshot doc = future.get();
    if(doc.exists()){
      Order order = doc.toObject(Order.class);
      order.setId(doc.getId());
      return order;
    }else{
      return null;
    }
  }

  public Response addOrder(Order order) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    ApiFuture<DocumentReference> docRef = db.collection("order").add(order);
    String refId = docRef.get().getId();
    Response resp = new Response();
    resp.setStatus(true);
    resp.setDocId(refId);
    return resp;
  }

  public Response updateOrder(String id, Order order) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    Long tStart = java.lang.System.currentTimeMillis();
    ApiFuture<DocumentSnapshot> future = db.collection("order").document(id).get();
    if(future.get().exists()){
      ApiFuture<WriteResult> result = db.collection("order").document(id).set(order);
      Response resp = new Response();
      resp.setStatus(true);
      Long tEnd = result.get().getUpdateTime().toDate().getTime();
      resp.setUpdateTime(tEnd - tStart);
      resp.setDocId(future.get().getId());
      return resp;
    }else{
      return new Response(false, "Document not found!");
    }
    // ApiFuture<WriteResult> result = docRef.set(menu);
  }

  public Response deleteOrder(String id) throws InterruptedException, ExecutionException {
    Firestore db = FirestoreClient.getFirestore();
    Long tStart = java.lang.System.currentTimeMillis();
    //First, we get the menu reference, to check whether the document is exist or not.
    ApiFuture<DocumentSnapshot> future = db.collection("order").document(id).get();
    if(future.get().exists()){
      ApiFuture<WriteResult> ref = db.collection("order").document(id).delete();
      Long tEnd = ref.get().getUpdateTime().toDate().getTime();
      Response resp = new Response();
      resp.setStatus(true);
      resp.setUpdateTime(tEnd-tStart);
      return resp;
    }else{
      Response resp = new Response(false, "Document not found");
      return resp;
    }
  }
}