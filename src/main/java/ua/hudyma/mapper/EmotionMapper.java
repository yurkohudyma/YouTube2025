package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.domain.content.Emotion;
import ua.hudyma.domain.content.Post;
import ua.hudyma.domain.content.Video;
import ua.hudyma.dto.EmotionReqDto;
import ua.hudyma.dto.EmotionRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.service.EntityProviderService;

@Component
@RequiredArgsConstructor
public class EmotionMapper extends BaseMapper<EmotionRespDto, Emotion, EmotionReqDto> {
    private final EntityProviderService provider;

    @Override
    public EmotionRespDto toDto(Emotion emotion) {
        String videoName;
        String postName;
        String commentText;
        videoName = emotion.getVideo() == null ? "NA" : emotion.getVideo().getName();
        postName = emotion.getPost() == null ? "NA" : emotion.getPost().getName();
        commentText = emotion.getComment() == null ? "NA" : emotion.getComment().getText();
        return new EmotionRespDto(
                emotion.getUser().getProfile().getName(),
                emotion.getEmotionType().name(),
                videoName,
                postName,
                commentText
        );
    }

    public Emotion toEntity(EmotionReqDto dto) {
        var emotion = new Emotion();
        Post post;
        Video video;
        Comment comment;
        if (dto.email() == null){
            throw new DtoObligatoryFieldsAreMissingException("User email is VOID");
        }
        var user = provider.getUser(dto.email());
        post = dto.postId() == null ? null : provider.getPost(dto.postId());
        video = dto.videoId() == null ? null : provider.getVideo(dto.videoId());
        comment = dto.commentId() == null ? null : provider.getComment(dto.commentId());
        emotion.setEmotionType(dto.emotionType());
        emotion.setPost(post);
        emotion.setComment(comment);
        emotion.setVideo(video);
        emotion.setUser(user);
        return emotion;
    }
}
