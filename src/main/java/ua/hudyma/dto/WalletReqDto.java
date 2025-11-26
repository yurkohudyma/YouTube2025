package ua.hudyma.dto;

import ua.hudyma.domain.wallet.Card;
import ua.hudyma.domain.wallet.Method;
import ua.hudyma.domain.wallet.Subcription;

import java.util.List;

public record WalletReqDto(
        String userEmail,
        List<Method> methodList,
        List<Subcription> subcriptionList,
        List<Card> cardList
) {
}
