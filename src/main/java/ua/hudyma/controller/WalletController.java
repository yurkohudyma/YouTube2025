package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.WalletReqDto;
import ua.hudyma.dto.WalletRespDto;
import ua.hudyma.service.WalletService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;
    @PostMapping
    public ResponseEntity<String> createWallet (
            @RequestBody WalletReqDto dto){
        return ResponseEntity.ok(walletService
                .createWallet(dto));
    }
    @GetMapping
    public ResponseEntity<WalletRespDto> fetchWallet (
            @RequestParam String email){
        return ResponseEntity.ok(walletService
                .fetchWallet(email));
    }
}
