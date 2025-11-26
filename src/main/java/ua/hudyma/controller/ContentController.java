package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.*;
import ua.hudyma.service.ContentService;

@RestController
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    @PostMapping("/channel")
    public ResponseEntity<String> createChannel (@RequestBody ChannelReqDto dto){
        return ResponseEntity.ok(contentService.createChannel(dto));
    }

    @GetMapping("/channel")
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
    @GetMapping("/video")
    public ResponseEntity<VideoRespDto> fetchVideo (@RequestParam String videoId){
        return ResponseEntity.ok(contentService.fetchVideo(videoId));
    }
    @PostMapping("/commentVideo")
    public ResponseEntity<String> commentVideo (@RequestBody CommentReqDto dto){
        return ResponseEntity.ok(contentService.commentVideo(dto));
    }
}
