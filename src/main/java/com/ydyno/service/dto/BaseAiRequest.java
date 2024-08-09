package com.ydyno.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseAiRequest implements Serializable {
    private List<MessageEntity> messages;
    private String model;
    private Double temperature;
    private Integer max_tokens;
    private String apiType;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class MessageEntity implements Serializable {

        private String role;
        private String content;
    }
}
