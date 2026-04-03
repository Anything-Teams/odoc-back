package com.anything.odoc.push;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FcmSendService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${firebase.project-id}")
    private String projectId;

    public String sendToToken(String token, String title, String body, String link) throws Exception {
        String accessToken = GoogleAccessTokenProvider.getAccessToken();

        String url = "https://fcm.googleapis.com/v1/projects/" + projectId + "/messages:send";

        Map<String, Object> message = new HashMap<>();
        Map<String, Object> inner = new HashMap<>();
        Map<String, Object> notification = new HashMap<>();
        Map<String, Object> webpush = new HashMap<>();
        Map<String, Object> fcmOptions = new HashMap<>();

        notification.put("title", title);
        notification.put("body", body);

        fcmOptions.put("link", link);
        webpush.put("fcm_options", fcmOptions);

        inner.put("token", token);
        inner.put("notification", notification);
        inner.put("webpush", webpush);

        message.put("message", inner);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = objectMapper.writeValueAsString(message);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }
}