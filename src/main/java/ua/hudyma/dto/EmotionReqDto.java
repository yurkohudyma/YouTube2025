package ua.hudyma.dto;

import ua.hudyma.enums.EmotionType;

public record EmotionReqDto(
        EmotionType emotionType,
        String email,
        String videoId,
        String postId,
        String commentId
) {
}
