package com.anything.odoc.push;

import com.anything.odoc.push.dao.PushDao;
import com.anything.odoc.push.vo.PushTargetVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PushScheduler {

    private final PushDao pushDao;
    private final FcmSendService fcmSendService;

    @Scheduled(cron = "0 * * * * *")
    public void sendOdocReminder() {
        String nowHm = LocalTime.now(ZoneId.of("Asia/Seoul"))
                .format(DateTimeFormatter.ofPattern("HH:mm"));

        List<PushTargetVO> targets = pushDao.selectPushTargets(nowHm);

        if (targets == null || targets.isEmpty()) {
            log.info("push target 없음. nowHm={}", nowHm);
            return;
        }

        for (PushTargetVO target : targets) {
            try {
                String title = "ODOC";
                String body = "[" + target.getOdocNames() + "] 시간입니다";
                String link = "https://odoc.vercel.app/";
//                String link = "http://localhost:3000/";

                String response = fcmSendService.sendToToken(
                        target.getPushToken(),
                        title,
                        body,
                        link
                );

                log.info("push 발송 성공. userId={}, odocSn={}, response={}",
                        target.getUserId(), target.getOdocSn(), response);

            } catch (Exception e) {
                log.error("push 발송 실패. userId={}, odocSn={}",
                        target.getUserId(), target.getOdocSn(), e);
            }
        }
    }
}