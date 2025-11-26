package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Channel;
import ua.hudyma.domain.content.Video;
import ua.hudyma.dto.ChannelReqDto;
import ua.hudyma.dto.ChannelRespDto;
import ua.hudyma.service.EntityProviderService;
import ua.hudyma.service.UserService;

@Component
@RequiredArgsConstructor
public class ChannelMapper extends BaseMapper<ChannelRespDto, Channel, ChannelReqDto> {
    private final EntityProviderService provider;
    @Override
    public ChannelRespDto toDto(Channel channel) {
        return new ChannelRespDto(
                channel.getChannelId(),
                channel.getName(),
                channel.getDescription(),
                channel.getUser().getProfile().getName(),
                getEntityFieldList(channel.getVideoList(), Video::getName),
                getEntityFieldList(channel.getSubscriberList(),
                        user -> user.getProfile().getName())
        );
    }

    @Override
    public Channel toEntity(ChannelReqDto dto) {
        var user = provider.getUser(dto.email());
        var channel = new Channel();
        channel.setUser(user);
        channel.setName(dto.name());
        channel.setDescription(dto.description());
        return channel;
    }
}
