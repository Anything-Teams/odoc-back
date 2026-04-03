package com.anything.odoc.push;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Collections;

public class GoogleAccessTokenProvider {

    private static final String FIREBASE_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";

    public static String getAccessToken() throws Exception {

        InputStream inputStream;

        String path = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

        if (path != null && !path.isEmpty()) {
            inputStream = new java.io.FileInputStream(path);
        } else {
            // 로컬 개발용
            ClassPathResource resource = new ClassPathResource("firebase/service-account.json");
            inputStream = resource.getInputStream();
        }

        try (inputStream) {
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(inputStream)
                    .createScoped(Collections.singletonList(FIREBASE_SCOPE));

            credentials.refreshIfExpired();

            if (credentials.getAccessToken() == null) {
                credentials.refresh();
            }

            return credentials.getAccessToken().getTokenValue();
        }
    }
}