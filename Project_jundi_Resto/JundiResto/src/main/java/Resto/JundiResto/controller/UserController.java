package Resto.JundiResto.controller;


import Resto.JundiResto.dto.UserRequest;
import Resto.JundiResto.dto.UserRespon;
import Resto.JundiResto.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public Optional<UserRespon> readFindById(@PathVariable String userId){
        return userService.findById(userId);
    }

    @GetMapping
    public List<UserRespon> read (@Param("userId") String userName) {
        return userService.readUser(userName);
    }

    @PostMapping
    public UserRespon createUser (@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }
}
