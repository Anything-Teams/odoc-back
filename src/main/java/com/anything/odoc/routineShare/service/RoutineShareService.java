package com.anything.odoc.routineShare.service;

import com.anything.odoc.routineShare.dao.RoutineShareDao;
import com.anything.odoc.routineShare.dto.RoutineShareCreateReq;
import com.anything.odoc.routineShare.dto.RoutineShareCreateRes;
import com.anything.odoc.routineShare.dto.RoutineShareImportRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class RoutineShareService {

    private final RoutineShareDao routineShareDao;

    private static final String CODE_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Transactional
    public RoutineShareCreateRes createShare(RoutineShareCreateReq req) {
        if (req == null || req.getRoutineJson() == null) {
            throw new IllegalArgumentException("공유할 루틴 데이터가 없습니다.");
        }

        String shareCode = createUniqueShareCode();

        routineShareDao.insertSharedRoutine(shareCode, req);

        return new RoutineShareCreateRes(shareCode);
    }

    @Transactional
    public RoutineShareImportRes getSharedRoutine(String shareCode) {
        if (shareCode == null || shareCode.trim().isEmpty()) {
            throw new IllegalArgumentException("공유 코드가 없습니다.");
        }

        String safeShareCode = shareCode.trim().toUpperCase();

        RoutineShareImportRes result =
                routineShareDao.selectSharedRoutine(safeShareCode);

        if (result == null) {
            throw new IllegalArgumentException("공유 루틴을 찾을 수 없습니다.");
        }

        routineShareDao.increaseDownloadCount(safeShareCode);

        return result;
    }

    private String createUniqueShareCode() {
        for (int i = 0; i < 10; i++) {
            String code = generateShareCode();

            if (routineShareDao.existsShareCode(code) == 0) {
                return code;
            }
        }

        throw new IllegalStateException("공유 코드를 생성하지 못했습니다.");
    }

    private String generateShareCode() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(CODE_CHARS.length());
            sb.append(CODE_CHARS.charAt(index));
        }

        return sb.toString();
    }
}