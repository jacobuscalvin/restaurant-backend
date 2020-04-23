package com.dropthefat.dtfbackend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dropthefat.dtfbackend.Models.Employee;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
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
            result.add(employee);
        }
        return result;
    }
    
}