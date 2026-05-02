package com.anything.odoc.routineShare.dto;

import lombok.Data;

@Data
public class RoutineShareImportRes {

    private String shareCode;

    private String routineName;

    private String routineType;

    private String routineJson;
}