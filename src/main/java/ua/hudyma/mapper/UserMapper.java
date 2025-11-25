package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.*;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.dto.UserRespDto;

@Component
public class UserMapper extends BaseMapper<UserRespDto, User, UserReqDto> {
    @Override
    public UserRespDto toDto(User user) {
        var profile = user.getProfile();
        return new UserRespDto(
                profile.getName(),
                profile.getBirthday(),
                profile.getSex().getValue(),
                getEntityFieldList(profile.getAddressList(), Address::getAddress),
                getEntityFieldList(profile.getEmailList(), Email::getEmail),
                getEntityFieldList(profile.getPhoneList(), Phone::getPhoneNumber)
        );
    }

    @Override
    public User toEntity(UserReqDto dto) {
        var user = new User();
        var profile = new Profile();
        profile.setName(dto.name());
        profile.setBirthday(dto.birthDate());
        profile.setSex(dto.sex());
        profile.setAddressList(dto.addressList());
        profile.setEmailList(dto.emailList());
        profile.setPhoneList(dto.phoneList());
        user.setProfile(profile);
        return user;
    }
}
