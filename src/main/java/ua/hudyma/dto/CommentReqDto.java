package ua.hudyma.dto;

public record CommentReqDto(
        String videoId,
        String email,
        String commentText

) {
}
