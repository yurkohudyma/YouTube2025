package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.dto.CommentReqDto;
import ua.hudyma.dto.CommentRespDto;
import ua.hudyma.service.EntityProviderService;
import ua.hudyma.service.UserService;

@Component
@RequiredArgsConstructor
public class CommentMapper extends BaseMapper<CommentRespDto, Comment, CommentReqDto> {
    private final EntityProviderService provider;
    @Override
    public CommentRespDto toDto(Comment comment) {
        return null;
    }

    @Override
    public Comment toEntity(CommentReqDto dto) {
        var comment = new Comment();
        comment.setText(dto.commentText());
        return comment;
    }
}
