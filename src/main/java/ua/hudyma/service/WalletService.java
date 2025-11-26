package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.dto.WalletReqDto;
import ua.hudyma.dto.WalletRespDto;
import ua.hudyma.mapper.WalletMapper;
import ua.hudyma.repository.WalletRepository;
import ua.hudyma.util.MessageProcessor;

@Service
@RequiredArgsConstructor
@Log4j2
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final EntityProviderService provider;

    @Transactional
    @SneakyThrows
    public String createWallet(WalletReqDto dto) {
        var wallet = walletMapper.toEntity(dto);
        walletRepository.save(wallet);
        return MessageProcessor.getReturnMessage(
                wallet.getUser().getProfile(), "name");
    }

    @Transactional
    public WalletRespDto fetchWallet(String email) {
        var user = provider.getUser(email);
        return walletMapper.toDto(user.getWallet());
    }
}
