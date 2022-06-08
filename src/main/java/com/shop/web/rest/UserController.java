package com.shop.web.rest;

import com.shop.domain.User;
import com.shop.dto.user.UserRequestDto;
import com.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody UserRequestDto userRequest) {
        return userService.save(userRequest);
    }

    @GetMapping("/index")
    public String getIndex() {
        return "Index";
    }
}
