package Resto.JundiResto.service.serviceImpl;

import Resto.JundiResto.dto.UserRequest;
import Resto.JundiResto.dto.UserRespon;
import Resto.JundiResto.entity.Userss;
import Resto.JundiResto.repository.UserRepository;
import Resto.JundiResto.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserRespon> readUser(String userName) {
        List<Userss> usersses = userRepository.findAllActiveUsersNative(userName);
        List<UserRespon> userRespons = usersses.stream().map(data -> buildResponse(data)).collect(Collectors.toList());
        return userRespons;
    }

    @Override
    public Optional<UserRespon> findById(String userId) {
            Userss userss = userRepository.findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
     UserRespon respon = buildResponse(userss);
        return Optional.of(respon);
    }

    @Transactional
    @Override
    public UserRespon createUser(UserRequest request) {
        Userss userss = builtUserEntity(request);
        userRepository.save(userss);
        log.info("Create Success: User ID = {}", userss.getUserId());
        return buildResponse(userss);
    }

    private Userss builtUserEntity(UserRequest request) {
        return Userss.builder()
                .userName(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .roll(request.getRoll())
                .createdAt(Timestamp.from(Instant.now()))
                .createdBy(request.getCreatedBy())
                .isDeleted(false)
                .build();
    }

    private UserRespon buildResponse(Userss userss) {
        return UserRespon.builder()
                .userId(userss.getUserId())
                .name(userss.getUserName())
                .email(userss.getEmail())
                .roll(userss.getRoll())
                .isDeleted(userss.isDeleted())
                .creadtedAt(userss.getCreatedAt())
                .createdBy(userss.getCreatedBy())
                .build();
    }
    }

