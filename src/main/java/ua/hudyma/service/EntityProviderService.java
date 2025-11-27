package ua.hudyma.service;

import ch.qos.logback.classic.model.LoggerModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.content.Channel;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.domain.content.Post;
import ua.hudyma.domain.content.Video;
import ua.hudyma.domain.personal.User;
import ua.hudyma.repository.*;

import static ua.hudyma.util.MessageProcessor.getExceptionSupplier;

@Service
@RequiredArgsConstructor
public class EntityProviderService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final EmotionRepository emotionRepository;

    public Comment getComment(String commentId) {
        return commentRepository.findByCommentId(commentId)
                .orElseThrow(getExceptionSupplier(Comment.class, commentId,
                        EntityNotFoundException::new));
    }

    public Post getPost(String postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(getExceptionSupplier(Post.class, postId,
                        EntityNotFoundException::new));
    }

    public Video getVideo(String videoId) {
        return videoRepository.findByVideoId(videoId)
                .orElseThrow(getExceptionSupplier(
                        Video.class,
                        videoId,
                        EntityNotFoundException::new));
    }

    public Channel getChannel(String channelId) {
        return channelRepository.findByChannelId(channelId)
                .orElseThrow(getExceptionSupplier(Channel.class,
                        channelId,
                        EntityNotFoundException::new));
    }

    public User getUser(String email) {
        return userRepository
                .findByProfile_EmailList_Email(email)
                .orElseThrow(getExceptionSupplier(User.class, email,
                        EntityNotFoundException::new));
    }

}
