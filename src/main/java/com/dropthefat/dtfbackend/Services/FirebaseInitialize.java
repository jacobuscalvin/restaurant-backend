package com.dropthefat.dtfbackend.Services;

import java.io.File;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialize {
  @PostConstruct
  public void initialize() {
    try {
      File accFile = new File("./serviceAccount.json");
      FileInputStream serviceAccount = new FileInputStream(accFile);

      FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://java-restaurant-2c40a.firebaseio.com")
        .build();

      FirebaseApp.initializeApp(options);
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}