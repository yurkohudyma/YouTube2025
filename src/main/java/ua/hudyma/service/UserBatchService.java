package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.personal.User;
import ua.hudyma.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserBatchService {

    private final UserRepository userRepository;

    @Transactional
    public String insertWithoutBatching(int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user = new User();
            userRepository.save(user); // кожен save окремо → no batching
        }
        return "Without batching: " + (System.currentTimeMillis() - start) + " ms";
    }

    @Transactional
    public String insertWithBatching(int count, int batchSize) {
        long start = System.currentTimeMillis();
        List<User> batch = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            batch.add(user);
            if (batch.size() == batchSize) {
                userRepository.saveAll(batch);
                batch.clear();
            }
        }
        if (!batch.isEmpty()) {
            userRepository.saveAll(batch);
        }
        return "With batching: " + (System.currentTimeMillis() - start) + " ms";
    }
}

