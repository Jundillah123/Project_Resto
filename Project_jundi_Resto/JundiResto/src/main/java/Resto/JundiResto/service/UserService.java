package Resto.JundiResto.service;

import Resto.JundiResto.dto.UserRequest;
import Resto.JundiResto.dto.UserRespon;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserRespon> readUser (String userName);

    Optional<UserRespon> findById (String userId);

    UserRespon createUser (UserRequest userRequest);
}
