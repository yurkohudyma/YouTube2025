package ua.hudyma.dto;

import ua.hudyma.domain.content.Emotion;

import java.util.List;

public record PostRespDto(
        String postId,
        String name,
        String authorName,
        String text,
        List<String> commentList,
        List<String> emotionList
) {
}
