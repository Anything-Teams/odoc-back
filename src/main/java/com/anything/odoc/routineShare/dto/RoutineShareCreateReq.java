package com.anything.odoc.routineShare.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class RoutineShareCreateReq {

    private String routineName;
    private String routineType;
    private JsonNode routineJson;
    private String creatorType;
    private String creatorName;
    private String creatorUid;
}