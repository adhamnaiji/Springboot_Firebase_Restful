package com.firebaseInitializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;


import org.slf4j.Logger;


@Service
public class FirebaseInitilization {

	private static final Logger logger = LoggerFactory.getLogger(FirebaseException.class);

    @PostConstruct
    public void initialization() {
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("/springboot.firebase.demo/serviceAccountKey.json");
            logger.info("Found serviceAccountKey.json file");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            logger.info("FirebaseApp initialized successfully");
        } catch (FileNotFoundException e) {
            logger.error("serviceAccountKey.json file not found", e);
        } catch (Exception e) {
            logger.error("Error initializing FirebaseApp", e);
        }
    }
}
