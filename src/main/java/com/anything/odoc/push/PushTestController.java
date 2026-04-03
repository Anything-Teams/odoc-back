package com.anything.odoc.push;

import com.anything.odoc.push.dao.PushDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PushTestController {

    private final FcmSendService fcmSendService;
    private final PushDao pushDao;

    @GetMapping("/testAccessToken")
    public String testAccessToken() throws Exception {
        String accessToken = GoogleAccessTokenProvider.getAccessToken();
        return accessToken != null && !accessToken.isBlank() ? "SUCCESS" : "FAIL";
    }

    @GetMapping("/testPush")
    public String testPush(@RequestParam String userId) throws Exception {
        List<String> tokens = pushDao.selectPushTokens(userId);

        if (tokens == null || tokens.isEmpty()) {
            return "NO_TOKEN";
        }

        String lastResponse = "";

        for (String token : tokens) {
            lastResponse = fcmSendService.sendToToken(
                    token,
                    "ODOC",
                    "테스트 알림입니다.",
                    "http://localhost:3000/projects"
            );
        }

        return lastResponse;
    }
}