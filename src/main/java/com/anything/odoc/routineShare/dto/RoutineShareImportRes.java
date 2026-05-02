package com.anything.odoc.routineShare.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class RoutineShareImportRes {

    private String shareCode;

    private String routineName;

    private String routineType;

    private JsonNode routineJson;
}