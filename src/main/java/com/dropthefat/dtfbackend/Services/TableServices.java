package com.dropthefat.dtfbackend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.dropthefat.dtfbackend.Models.Table;
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
public class TableServices {
    public List<Table> getAllTable() throws InterruptedException, ExecutionException{
        Firestore db = FirestoreClient.getFirestore();
        List<Table> result = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection("table").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot doc : documents) {
            Table table = doc.toObject(Table.class);
            table.setId(doc.getId());
            result.add(table);
        }
        return result;
      }

      public Table getTableById(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("table").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot doc = future.get();
        if(doc.exists()){
            Table table = doc.toObject(Table.class);
            table.setId(doc.getId());
            return table;
        }else{
          return null;
        }
      }

      public Response addTable(Table table) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("table").whereEqualTo("tableNumber", table.getTableNumber()).get();
        if(future.get().size() == 0){
          ApiFuture<DocumentReference> docRef = db.collection("table").add(table);
          String refId = docRef.get().getId();
          Response resp = new Response();
          resp.setStatus(true);
          resp.setDocId(refId);
          return resp;
        }else{
          return new Response(false, "Table is already used.");
        }

      }

      public Response updateTable(String id,Table table) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Long tStart = java.lang.System.currentTimeMillis();
        ApiFuture<DocumentSnapshot> future = db.collection("table").document(id).get();
        if(future.get().exists()){
          ApiFuture<WriteResult> ref = db.collection("table").document(id).set(table);
          Long tEnd = ref.get().getUpdateTime().toDate().getTime();
          Response resp = new Response();
          resp.setStatus(true);
          resp.setUpdateTime(tEnd-tStart);
          resp.setDocId(future.get().getId());
          return resp;
        }else{
          return new Response(false, "Document not found!");
        }
        // ApiFuture<WriteResult> result = docRef.set(table);
      }
    
      public Response deleteTable(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Long tStart = java.lang.System.currentTimeMillis();
        //First, we get the table reference, to check whether the document is exist or not.
        ApiFuture<DocumentSnapshot> future = db.collection("table").document(id).get();
        if(future.get().exists()){
          ApiFuture<WriteResult> ref = db.collection("table").document(id).delete();
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