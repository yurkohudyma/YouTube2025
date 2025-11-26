package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.ChannelReqDto;
import ua.hudyma.dto.ChannelRespDto;
import ua.hudyma.dto.VideoReqDto;
import ua.hudyma.service.ContentService;

@RestController
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    @PostMapping("/channel")
    public ResponseEntity<String> createChannel (@RequestBody ChannelReqDto dto){
        return ResponseEntity.ok(contentService.createChannel(dto));
    }

    @GetMapping
    public ResponseEntity<ChannelRespDto> fetchChannel (@RequestParam String channelId){
        return ResponseEntity.ok(contentService.fetchChannel(channelId));
    }

    @GetMapping("/subscribe")
    public ResponseEntity<String> subscribeToChannel (
            @RequestParam String channelId, @RequestParam String email){
        return ResponseEntity.ok(contentService
                .subscribeToChannel(channelId, email));
    }
    @PostMapping("/video")
    public ResponseEntity<String> createVideo (@RequestBody VideoReqDto dto){
        return ResponseEntity.ok(contentService.createVideo (dto));
    }
}
