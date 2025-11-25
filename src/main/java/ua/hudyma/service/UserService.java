package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.mapper.UserMapper;
import ua.hudyma.repository.UserRepository;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @SneakyThrows
    @Transactional
    public String createUser (UserReqDto dto){
        var user = userMapper.toEntity(dto);
        userRepository.save(user);
        return getReturnMessage(user.getProfile(), "name");
    }
}
