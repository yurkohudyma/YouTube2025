package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.content.Channel;
import ua.hudyma.domain.personal.User;
import ua.hudyma.dto.ChannelReqDto;
import ua.hudyma.dto.ChannelRespDto;
import ua.hudyma.dto.VideoReqDto;
import ua.hudyma.mapper.*;
import ua.hudyma.repository.*;
import ua.hudyma.util.MessageProcessor;

import static ua.hudyma.util.MessageProcessor.getExceptionSupplier;
import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class ContentService {
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final EmotionRepository emotionRepository;
    private final UserService userService;

    private final ChannelMapper channelMapper;
    private final VideoMapper videoMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final EmotionMapper emotionMapper;

    @SneakyThrows
    public String createChannel(ChannelReqDto dto) {
        var channel = channelMapper.toEntity(dto);
        channelRepository.save(channel);
        return MessageProcessor.getReturnMessage(channel, "name");
    }

    @SneakyThrows
    public String createVideo(VideoReqDto dto) {
        var video = videoMapper.toEntity(dto);
        videoRepository.save(video);
        return getReturnMessage(video, "name");
    }

    @Transactional
    public ChannelRespDto fetchChannel(String channelId) {
        var channel = getChannel(channelId);
        return channelMapper.toDto(channel);
    }

    @Transactional
    public String subscribeToChannel(String channelId, String email) {
        var channel = getChannel(channelId);
        var user = userService.getUser(email);
        channel.getSubscriberList().add(user);
        user.getSubscribedChannelList().add(channel);
        return String.format("User %s succ subscribed to channel %s",
                user.getProfile().getName(),
                channel.getName());
    }

    public Channel getChannel(String channelId) {
        return channelRepository.findByChannelId(channelId)
                .orElseThrow(getExceptionSupplier(Channel.class,
                        channelId,
                        EntityNotFoundException::new));
    }
}
