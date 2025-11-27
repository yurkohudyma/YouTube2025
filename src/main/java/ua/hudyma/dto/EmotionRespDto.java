package ua.hudyma.dto;

import ua.hudyma.enums.EmotionType;

public record EmotionRespDto(
        String authorName,
        String emotionType,
        String videoName,
        String postName,
        String commentText
) {
}
