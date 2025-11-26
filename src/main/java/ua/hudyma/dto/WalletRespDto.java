package ua.hudyma.dto;

import java.util.List;

public record WalletRespDto(
        String userName,
        List<String> methodList,
        List<String> subscriptionList,
        List<String> cardList

) {
}
