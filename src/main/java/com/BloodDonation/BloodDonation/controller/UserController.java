package com.BloodDonation.BloodDonation.controller;

//punct de acces in aplicatia asta
//spring va porni un server care va rula pe portul nostru

import com.BloodDonation.BloodDonation.dto.UserCreateDTO;
import com.BloodDonation.BloodDonation.dto.UserDTO;
import com.BloodDonation.BloodDonation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    //REST controller
    //cand vrem sa obtinem o resursa cu id
    //GET /resource/{id}

    //cand vrem sa cream o resursa
    //POST /resource

    //cand vrem sa updatam o resursa cu id
    //PUT  /resource/{id}

    //sterge resursa cu id-ul dat
    //DELETE /resource/{id}

    //informatiile pe care le trimitem le grupam in DTO data transfer object
    //pentru asta cream un pachet DTO
//    @Autowired
//    UserService userService;

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    ResponseEntity<UserDTO> registerUser(@RequestBody UserCreateDTO dto){
        UserDTO registeredUser = userService.registerUserDTO(dto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/user/{email}")
    ResponseEntity<UserDTO> getUser(@PathVariable("email") String email){
        UserDTO foundUser = userService.getUserByEmail(email);
        if (foundUser != null)
            return ResponseEntity.ok(foundUser);
        else
            return (ResponseEntity<UserDTO>) ResponseEntity.notFound();
    }
}
