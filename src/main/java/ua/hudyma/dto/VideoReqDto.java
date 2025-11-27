package ua.hudyma.dto;

import ua.hudyma.domain.content.Tag;

import java.util.List;

public record VideoReqDto(
        String channelId,
        String name,
        String description,
        List<Tag> tagList
) {
}
