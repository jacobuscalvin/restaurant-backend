package com.dropthefat.dtfbackend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dropthefat.dtfbackend.Models.Employee;
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
public class EmployeeServices {
    
    public List<Employee> getAllEmployee() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        List<Employee> result = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection("employee").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot doc : documents) {
            Employee employee = doc.toObject(Employee.class);
            employee.setId(doc.getId());
            result.add(employee);
        }
        return result;
    }

    public Employee getEmployeeById(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("employee").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot doc = future.get();
        if(doc.exists()){
            Employee employee = doc.toObject(Employee.class);
            employee.setId(doc.getId());
            return employee;
        } else{
            return null;
        }
    }

    public Response addEmployee(Employee employee) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> docRef = db.collection("employee").add(employee);
        String refId = docRef.get().getId();
        Response res = new Response();
        res.setStatus(true);
        res.setDocId(refId);
        return res;
    }

    public Response updateEmployee(String id, Employee employee) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Long tStart = java.lang.System.currentTimeMillis();
        ApiFuture<DocumentSnapshot> future = db.collection("employee").document(id).get();
        if(future.get().exists()){
            ApiFuture<WriteResult> ref = db.collection("employee").document(id).set(employee);
            Long tEnd = ref.get().getUpdateTime().toDate().getTime();
            Response res = new Response();
            res.setStatus(true);
            res.setUpdateTime(tEnd - tStart);
            res.setDocId(future.get().getId());
            return res;
        } else{
            return new Response(false, "Document not found!");
        }
    }

    public Response deleteEmployee(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Long tStart = java.lang.System.currentTimeMillis();
        ApiFuture<DocumentSnapshot> future = db.collection("employee").document(id).get();
        if(future.get().exists()){
            ApiFuture<WriteResult> ref = db.collection("employee").document(id).delete();
            Long tEnd = ref.get().getUpdateTime().toDate().getTime();
            Response res = new Response();
            res.setStatus(true);
            res.setUpdateTime(tEnd - tStart);
            return res;
        } else{
            Response res = new Response(false, "Document not found!");
            return res;
        }
    }
}