package com.anything.odoc.push;

import com.anything.odoc.push.vo.PushTokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PushController {

    private final PushService pushService;

    @PostMapping("/insertPushToken")
    public ResponseEntity<Integer> savePushToken(@RequestBody PushTokenVO pushTokenVO) {
        int result = pushService.insertPushToken(pushTokenVO);
        return ResponseEntity.ok(result);
    }
}