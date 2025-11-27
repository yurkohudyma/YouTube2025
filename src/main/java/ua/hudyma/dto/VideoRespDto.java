package ua.hudyma.dto;

import java.util.List;

public record VideoRespDto(
        String videoId,
        String channelName,
        String name,
        String description,
        List<String> tagList,
        Long views,
        Integer comments,
        Integer emotions
) {
}
