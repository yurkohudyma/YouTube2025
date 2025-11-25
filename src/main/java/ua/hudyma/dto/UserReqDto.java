package ua.hudyma.dto;

import ua.hudyma.domain.personal.Address;
import ua.hudyma.domain.personal.Email;
import ua.hudyma.domain.personal.Phone;
import ua.hudyma.domain.security.Device;
import ua.hudyma.enums.Sex;

import java.time.LocalDate;
import java.util.List;

public record UserReqDto(
        String name,
        LocalDate birthDate,
        Sex sex,
        List<Email> emailList,
        List<Address> addressList,
        List<Phone> phoneList,
        List<Device> deviceList
) {}
