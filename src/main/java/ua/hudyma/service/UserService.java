package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.dto.UserRespDto;
import ua.hudyma.mapper.UserMapper;
import ua.hudyma.repository.UserRepository;

import java.util.List;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityProviderService provider;

    @SneakyThrows
    @Transactional
    public String createUser (UserReqDto dto){
        var user = userMapper.toEntity(dto);
        userRepository.save(user);
        return getReturnMessage(user.getProfile(), "name");
    }
    @SneakyThrows
    @Transactional
    public String createUserBatch (List<UserReqDto> dtoList){
        var userList = userMapper.toEntityList(dtoList);
        userRepository.saveAll(userList);
        return getReturnMessage(userList, "name");
    }

    @Transactional
    public UserRespDto fetchUser(String email) {
        var user = provider.getUser(email);
        return userMapper.toDto(user);
    }




}
