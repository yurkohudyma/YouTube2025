package ua.hudyma.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.dto.UserRespDto;
import ua.hudyma.service.UserBatchService;
import ua.hudyma.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserBatchService userBatchService;
    @PostMapping
    public ResponseEntity<String> createUser (@RequestBody UserReqDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }
    @PostMapping("/batch")
    public ResponseEntity<String> createUserBatch (@RequestBody List<UserReqDto> dtoList){
        return ResponseEntity.ok(userService.createUserBatch(dtoList));
    }
    @GetMapping
    public ResponseEntity<UserRespDto> fetchUser (@RequestParam String email){
        return ResponseEntity.ok(userService.fetchUser(email));
    }
    @GetMapping("/insertWith")
    public String insertWith() {
        int total = 7000;
        return userBatchService.insertWithBatching(total, 50);
    }
    @GetMapping("/insertWithout")
    public String insertWithout() {
        int total = 7000;
        return userBatchService.insertWithoutBatching(total);
    }
}
