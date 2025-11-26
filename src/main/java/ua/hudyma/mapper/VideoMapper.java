package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Channel;
import ua.hudyma.domain.content.Video;
import ua.hudyma.dto.VideoReqDto;
import ua.hudyma.dto.VideoRespDto;
import ua.hudyma.repository.ChannelRepository;
import ua.hudyma.service.ContentService;

import static ua.hudyma.util.MessageProcessor.getExceptionSupplier;

@Component
@RequiredArgsConstructor
public class VideoMapper extends BaseMapper<VideoRespDto, Video, VideoReqDto>{

    private final ChannelRepository channelRepository;
    @Override
    public VideoRespDto toDto(Video video) {
        return null;
    }

    @Override
    public Video toEntity(VideoReqDto dto) {
        var channel = getChannel(dto.channelId());
        var video = new Video();
        video.setName(dto.name());
        video.setDescription(dto.description());
        video.setChannel(channel);
        return video;
    }

    public Channel getChannel(String channelId) {
        return channelRepository.findByChannelId(channelId)
                .orElseThrow(getExceptionSupplier(Channel.class,
                        channelId,
                        EntityNotFoundException::new));
    }
}

