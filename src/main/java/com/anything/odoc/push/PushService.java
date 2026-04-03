package com.anything.odoc.push;

import com.anything.odoc.push.dao.PushDao;
import com.anything.odoc.push.vo.PushTokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushService {

    private final PushDao pushDao;

    public int insertPushToken(PushTokenVO pushTokenVO) {
        return pushDao.insertPushToken(pushTokenVO);
    }
}