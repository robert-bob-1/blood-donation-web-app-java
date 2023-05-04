package com.BloodDonation.BloodDonation.controller;

//punct de acces in aplicatia asta
//spring va porni un server care va rula pe portul nostru

import com.BloodDonation.BloodDonation.dto.UserCreateDTO;
import com.BloodDonation.BloodDonation.dto.UserDTO;
import com.BloodDonation.BloodDonation.entity.users.User;
import com.BloodDonation.BloodDonation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("")
    ResponseEntity<UserDTO> registerUser(@RequestBody UserCreateDTO dto){
        UserDTO registeredUser = userService.registerUserDTO(dto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/{email}/{password}")
    ResponseEntity<?> loginUser(@PathVariable("email") String email,
                                   @PathVariable("password") String password){
        User foundUser = userService.loginUser(email, password);
        System.out.println(foundUser.toString());
//        if (foundUser != null)
            return ResponseEntity.ok(foundUser);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{email}")
    ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        User foundUser = userService.getUserByEmail(email);
        if (foundUser != null)
            return ResponseEntity.ok(foundUser);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("")
    ResponseEntity<?> updateUser(@RequestBody User user){
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null)
            return ResponseEntity.ok(updatedUser);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
