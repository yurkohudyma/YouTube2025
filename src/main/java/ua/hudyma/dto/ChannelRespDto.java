package ua.hudyma.dto;

import java.util.List;

public record ChannelRespDto(
        String id,
        String name,
        String description,
        String ownerName,
        List<String> videoList,
        List<String> subscriberList
) {
}
