package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.content.Channel;
import ua.hudyma.domain.content.Video;
import ua.hudyma.domain.personal.User;
import ua.hudyma.dto.*;
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
    private final EntityProviderService provider;
    private final CommentRepository commentRepository;

    private final ChannelMapper channelMapper;
    private final VideoMapper videoMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final EmotionMapper emotionMapper;

    @Transactional
    @SneakyThrows
    public String commentVideo(CommentReqDto dto) {
        var video = provider.getVideo(dto.videoId());
        var user = provider.getUser(dto.email());
        var comment = commentMapper.toEntity(dto);
        comment.setUser(user);
        comment.setVideo(video);
        commentRepository.save(comment);
        video.getCommentList().add(comment);
        return getReturnMessage(comment, "text");
    }

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
        var channel = provider.getChannel(channelId);
        return channelMapper.toDto(channel);
    }

    @Transactional
    public VideoRespDto fetchVideo(String videoId) {
        var video = provider.getVideo(videoId);
        return videoMapper.toDto(video);
    }

    @Transactional
    public String subscribeToChannel(String channelId, String email) {
        var channel = provider.getChannel(channelId);
        var user = provider.getUser(email);
        channel.getSubscriberList().add(user);
        user.getSubscribedChannelList().add(channel);
        return String.format("User %s succ subscribed to channel %s",
                user.getProfile().getName(),
                channel.getName());
    }
}
