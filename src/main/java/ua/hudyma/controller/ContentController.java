package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.domain.content.Tag;
import ua.hudyma.dto.*;
import ua.hudyma.service.ContentService;

import java.util.List;

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
    @GetMapping("/comment")
    public ResponseEntity<CommentRespDto> fetchComment (@RequestParam String commentId){
        return ResponseEntity.ok(contentService.fetchComment(commentId));
    }
    @PostMapping("/emotion")
    public ResponseEntity<String> createEmotion (
            @RequestBody EmotionReqDto dto){
        return ResponseEntity.ok(contentService
                .createEmotion(dto));
    }
    @GetMapping("/video/emotions")
    public ResponseEntity<List<EmotionRespDto>> fetchAllVideoEmotions (
            @RequestParam String videoId){
        return ResponseEntity.ok(contentService
                .fetchAllVideoEmotions(videoId));
    }
    @PostMapping("/video/tag")
    public ResponseEntity<String> addTags (
            @RequestBody List<Tag> tagList,
            @RequestParam String videoId){
        return ResponseEntity.ok(contentService
                .addTags(tagList, videoId));
    }
    @GetMapping("/video/compareTags")
    public ResponseEntity<List<String>> findVideoListWithTags(
            @RequestParam String videoId){
        return ResponseEntity.ok(contentService
                .collectVideosByTags(videoId));
    }
    @GetMapping("/video/purchase")
    public ResponseEntity<String> purchaseVideo (
            @RequestParam String email,
            @RequestParam String videoId){
        return ResponseEntity.ok(contentService
                .purchaseVideo(email, videoId));
    }
    @GetMapping("/video/rent")
    public ResponseEntity<String> rentVideo (
            @RequestParam String email,
            @RequestParam String videoId){
        return ResponseEntity.ok(contentService
                .rentVideo(email, videoId));
    }
}
