package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.content.Tag;
import ua.hudyma.domain.content.Video;
import ua.hudyma.dto.*;
import ua.hudyma.mapper.*;
import ua.hudyma.repository.ChannelRepository;
import ua.hudyma.repository.CommentRepository;
import ua.hudyma.repository.EmotionRepository;
import ua.hudyma.repository.VideoRepository;
import ua.hudyma.util.MessageProcessor;

import java.util.List;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class ContentService {
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final EntityProviderService provider;
    private final CommentRepository commentRepository;
    private final EmotionRepository emotionRepository;

    private final ChannelMapper channelMapper;
    private final VideoMapper videoMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final EmotionMapper emotionMapper;

    @Transactional
    @SneakyThrows
    public String addTags(List<Tag> tagList, String videoId) {
        var video = provider.getVideo(videoId);
        video.getTagList().addAll(tagList);
        return getReturnMessage(tagList, "");
    }

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
    public String createEmotion(EmotionReqDto dto) {
        var emotion = emotionMapper.toEntity(dto);
        emotionRepository.save(emotion);
        return getReturnMessage(emotion, "emotionType");
    }

    @SneakyThrows
    public String createVideo(VideoReqDto dto) {
        var video = videoMapper.toEntity(dto);
        videoRepository.save(video);
        return getReturnMessage(video, "name");
    }

    @Transactional
    public List<EmotionRespDto> fetchAllVideoEmotions(String videoId) {
        var video = provider.getVideo(videoId);
        var list = video.getEmotionList();
        return emotionMapper.toDtoList(list);
    }

    @Transactional
    public ChannelRespDto fetchChannel(String channelId) {
        var channel = provider.getChannel(channelId);
        return channelMapper.toDto(channel);
    }

    @Transactional
    public CommentRespDto fetchComment(String commentId) {
        var comment = provider.getComment(commentId);
        return commentMapper.toDto(comment);
    }

    @Transactional
    public VideoRespDto fetchVideo(String videoId) {
        var video = provider.getVideo(videoId);
        return videoMapper.toDto(video);
    }

    @Transactional
    public List<String> collectVideosByTags(String videoId) {
        var requestedVideo = provider.getVideo(videoId);
        return videoRepository
                .findAll()
                .stream()
                .filter(video -> !video.getVideoId().equals(videoId))
                .filter(video -> findMatchingTags(video, requestedVideo))
                .map(Video::getName)
                .toList();
    }

    @Transactional
    public String purchaseVideo(String email, String videoId) {
        var user = provider.getUser(email);
        var video = provider.getVideo(videoId);
        user.getPurchasedVideoList().add(video);
        video.getPurchaserUserList().add(user);
        return String.format("%s придбав(-ла) %s",
                user.getProfile().getName(), video.getName());
    }

    @Transactional
    public String rentVideo(String email, String videoId) {
        var user = provider.getUser(email);
        var video = provider.getVideo(videoId);
        user.getRentedVideoList().add(video);
        video.getRenterUserList().add(user);
        return String.format("%s придбав(-ла) право перегляду %s",
                user.getProfile().getName(), video.getName());
    }

    private boolean findMatchingTags(Video video,
                                     Video requestedVideo) {
        var tagList = video.getTagList();
        var requestedTagList = requestedVideo.getTagList();
        var counter = 0;
        for (Tag tag : tagList) {
            if (requestedTagList.contains(tag)) {
                log.info("Video {} and {} MATCH by TAG: {}",
                        requestedVideo.getVideoId(),
                        video.getVideoId(),
                        tag.getName()
                );
                counter++;
            }
        }
        return counter >= 2;
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
