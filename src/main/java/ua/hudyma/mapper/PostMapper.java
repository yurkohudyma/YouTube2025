package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.domain.content.Emotion;
import ua.hudyma.domain.content.Post;
import ua.hudyma.dto.PostReqDto;
import ua.hudyma.dto.PostRespDto;
import ua.hudyma.service.EntityProviderService;

@Component
@RequiredArgsConstructor
public class PostMapper extends BaseMapper<PostRespDto, Post, PostReqDto> {
    private final EntityProviderService provider;
    @Override
    public PostRespDto toDto(Post post) {
        return new PostRespDto(
                post.getPostId(),
                post.getName(),
                post.getUser().getProfile().getName(),
                post.getText(),
                getEntityFieldList(post.getCommentList(),
                        Comment::getText),
                        getEntityFieldList(post.getEmotionList(),
                                emo -> emo.getEmotionType().name())
        );
    }

    @Override
    public Post toEntity(PostReqDto dto) {
        var user = provider.getUser(dto.email());
        var post = new Post();
        post.setText(dto.text());
        post.setName(dto.name());
        post.setUser(user);
        return post;
    }
}
