package ua.hudyma.dto;

import java.util.List;

public record WalletRespDto(
        String userName,
        List<String> methodList,
        List<String> subscriptioList,
        List<String> cardList

) {
}
