package com.anything.odoc.routineShare.service;

import com.anything.odoc.routineShare.dto.RoutineShareCreateReq;
import com.anything.odoc.routineShare.dto.RoutineShareCreateRes;
import com.anything.odoc.routineShare.dto.RoutineShareImportRes;

public interface RoutineShareService {

    RoutineShareCreateRes createShare(RoutineShareCreateReq req);

    RoutineShareImportRes getSharedRoutine(String shareCode);
}