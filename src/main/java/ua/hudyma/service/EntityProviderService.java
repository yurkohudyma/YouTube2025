package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.content.Channel;
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
