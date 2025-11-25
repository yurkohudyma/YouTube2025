package ua.hudyma.dto;

import ua.hudyma.domain.Address;
import ua.hudyma.domain.Email;
import ua.hudyma.domain.Phone;
import ua.hudyma.enums.Sex;

import java.time.LocalDate;
import java.util.List;

public record UserReqDto(
        List<Email> emailList,
        String name,
        List<Address> addressList,
        List<Phone> phoneList,
        LocalDate birthDate,
        Sex sex
) {}
