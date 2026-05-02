package com.anything.odoc.routineShare.controller;

import com.anything.odoc.routineShare.dto.RoutineShareCreateReq;
import com.anything.odoc.routineShare.dto.RoutineShareCreateRes;
import com.anything.odoc.routineShare.dto.RoutineShareImportRes;
import com.anything.odoc.routineShare.service.RoutineShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routine-shares")
@RequiredArgsConstructor
public class RoutineShareController {

    private final RoutineShareService routineShareService;

    @PostMapping
    public ResponseEntity<RoutineShareCreateRes> createShare(
            @RequestBody RoutineShareCreateReq req
    ) {
        System.out.println("루틴 공유 API 진입");
        return ResponseEntity.ok(routineShareService.createShare(req));
    }

    @GetMapping("/{shareCode}")
    public ResponseEntity<RoutineShareImportRes> getSharedRoutine(
            @PathVariable String shareCode
    ) {
        return ResponseEntity.ok(routineShareService.getSharedRoutine(shareCode));
    }
}