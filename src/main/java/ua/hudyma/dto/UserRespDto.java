package ua.hudyma.dto;

import java.time.LocalDate;
import java.util.List;

public record UserRespDto(
        String name,
        LocalDate birthDate,
        String sex,
        List<String> addressList,
        List<String> emailList,
        List<String> phoneList) {
}
