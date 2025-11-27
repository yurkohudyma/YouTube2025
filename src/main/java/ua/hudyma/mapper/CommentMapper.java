package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.dto.CommentReqDto;
import ua.hudyma.dto.CommentRespDto;
import ua.hudyma.service.EntityProviderService;

@Component
@RequiredArgsConstructor
public class CommentMapper extends BaseMapper<CommentRespDto, Comment, CommentReqDto> {
    private final EntityProviderService provider;
    @Override
    public CommentRespDto toDto(Comment comment) {
        String postName;
        String videoName;
        postName = comment.getPost() == null ? "NA": comment.getPost().getName();
        videoName = comment.getVideo() == null ? "NA": comment.getVideo().getName();
        return new CommentRespDto(
                comment.getCommentId(),
                comment.getText(),
                videoName,
                postName,
                comment.getUser().getProfile().getName(),
                getEntityFieldList(comment.getEmotionList(),
                        cmt -> cmt.getEmotionType().name())
        );
    }

    @Override
    public Comment toEntity(CommentReqDto dto) {
        var comment = new Comment();
        comment.setText(dto.commentText());
        return comment;
    }
}
