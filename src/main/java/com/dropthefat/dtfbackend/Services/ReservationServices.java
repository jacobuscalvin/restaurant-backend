package com.dropthefat.dtfbackend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dropthefat.dtfbackend.Models.Reservation;
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
public class ReservationServices {

    public List<Reservation> getAllReservation() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        List<Reservation> result = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection("reservation").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot doc: documents) {
            Reservation reservation = doc.toObject(Reservation.class);
            reservation.setId(doc.getId());
            result.add(reservation);
        }
        return result;
    }
    
    public Reservation getReservationById(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("reservation").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot doc = future.get();
        if(doc.exists()){
            Reservation reservation = doc.toObject(Reservation.class);
            reservation.setId(doc.getId());
            return reservation;
        } else{
            return null;
        }
    }

    public Response addReservation(Reservation reservation) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> docRef = db.collection("reservation").add(reservation);
        String refId = docRef.get().getId();
        Response res = new Response();
        res.setStatus(true);
        res.setDocId(refId);
        return res;
    }

    public Response updateReservation(String id, Reservation reservation) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Long tStart = java.lang.System.currentTimeMillis();
        ApiFuture<DocumentSnapshot> future = db.collection("reservation").document(id).get();
        if(future.get().exists()){
            ApiFuture<WriteResult> ref = db.collection("reservation").document(id).set(reservation);
            Long tEnd = ref.get().getUpdateTime().toDate().getTime();
            Response res = new Response();
            res.setStatus(true);
            res.setUpdateTime(tEnd-tStart);
            res.setDocId(future.get().getId());
            return res;
        } else{
            return new Response(false, "Document not found!");
        }
    }

    public Response deleteReservation(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Long tStart = java.lang.System.currentTimeMillis();
        ApiFuture<DocumentSnapshot> future = db.collection("reservation").document(id).get();
        if(future.get().exists()){
            ApiFuture<WriteResult> ref = db.collection("reservation").document(id).delete();
            Long tEnd = ref.get().getUpdateTime().toDate().getTime();
            Response res = new Response();
            res.setStatus(true);
            res.setUpdateTime(tEnd-tStart);
            return res;
        } else{
            Response res = new Response(false, "Document not found!");
            return res;
        }
    }

}