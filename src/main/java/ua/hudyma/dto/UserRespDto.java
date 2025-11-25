package ua.hudyma.dto;

import java.util.List;

public record UserRespDto(
        String name,
        String birthDate,
        String sex,
        List<String> addressList,
        List<String> emailList,
        List<String> phoneList,
        List<String> deviceList) {
}
