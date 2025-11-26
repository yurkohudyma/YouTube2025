package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.wallet.Card;
import ua.hudyma.domain.wallet.Wallet;
import ua.hudyma.dto.WalletReqDto;
import ua.hudyma.dto.WalletRespDto;
import ua.hudyma.service.UserService;

@Component
@RequiredArgsConstructor
public class WalletMapper extends BaseMapper<WalletRespDto, Wallet, WalletReqDto> {
    private final UserService userService;

    @Override
    public WalletRespDto toDto(Wallet wallet) {
        return new WalletRespDto(
                wallet.getUser().getProfile().getName(),
                getEntityFieldList(wallet.getMethodList(),
                        method -> method.getPaymentMethodType().getValue()),
                getEntityFieldList(wallet.getSubcriptionList(),
                        sub -> sub.getSubcriptionType().getValue()),
                getEntityFieldList(wallet.getCardList(), Card::getCardName)
        );
    }

    @Override
    public Wallet toEntity(WalletReqDto dto) {
        var user = userService.getUser(dto.userEmail());
        Wallet wallet;
        if (user.getWallet() == null) {
            wallet = new Wallet();
            wallet.setUser(user);
        }
        else {
            wallet = user.getWallet();
        }
        wallet.getCardList().addAll(dto.cardList());
        wallet.getMethodList().addAll(dto.methodList());
        wallet.getSubcriptionList().addAll(dto.subcriptionList());
        return wallet;
    }
}
