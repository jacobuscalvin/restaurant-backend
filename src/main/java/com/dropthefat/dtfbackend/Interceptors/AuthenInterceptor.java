package com.dropthefat.dtfbackend.Interceptors;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropthefat.dtfbackend.Models.Admin;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.api.core.ApiFuture;

public class AuthenInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        String authHeader = request.getHeader("Authorization");
        String token = request.getHeader("Token");
        
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("admin").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        
        if(authHeader != null && token != null){
            String usernameAndPassHash = authHeader.split(" ")[1];
            String usernameAndPass = new String(Base64.getDecoder().decode(usernameAndPassHash));
            String username = usernameAndPass.split(":")[0];
            String password = usernameAndPass.split(":")[1];
            boolean checker = false;

            for (QueryDocumentSnapshot doc : documents) {
                Admin admin = doc.toObject(Admin.class);
                admin.setId(doc.getId());

                if (username.equalsIgnoreCase(admin.getUsername()) && password.equals(admin.getPassword())){
                    checker = true;
                }
            }

            if (checker == true){
                return true;
            }else {
                response.sendError(404,"Unauthorized");
                return false;
            }

        }else {
            response.sendError(404,"Unauthorized");
            return false;
        }
	}

}