package ua.hudyma.dto;

import java.util.List;

public record CommentRespDto(
        String commentId,
        String text,
        String videoName,
        String postName,
        String authorName,
        List<String> emotionList
) {
}
