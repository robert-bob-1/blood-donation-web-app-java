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
    ResponseEntity<User> loginUser(@PathVariable("email") String email,
                                   @PathVariable("password") String password){
        User foundUser = userService.loginUser(email, password);
        if (foundUser != null)
            return ResponseEntity.ok(foundUser);
        else
            return (ResponseEntity<User>) ResponseEntity.notFound();
    }
    @GetMapping("/{email}")
    ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User foundUser = userService.getUserByEmail(email);
        if (foundUser != null)
            return ResponseEntity.ok(foundUser);
        else
            return (ResponseEntity<User>) ResponseEntity.notFound();
    }

    @PutMapping("")
    ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null)
            return ResponseEntity.ok(updatedUser);
        else
            return (ResponseEntity<User>) ResponseEntity.notFound();
    }

//    @DeleteMapping("/delete/{uuid}")
//    ResponseEntity<User> deleteUser(@PathVariable("uuid") UUID uuid){
//        User foundUser = userService.deleteById(email);
//        if (foundUser != null)
//            return ResponseEntity.ok(foundUser);
//        else
//            return (ResponseEntity<User>) ResponseEntity.notFound();
//    }
//    @GetMapping("/{id}")
//    ResponseEntity<?> getUserById(@PathVariable("id") UUID id){
//        try {
//            UserDTO foundUser = userService.getUserById(id);
//            return ResponseEntity.ok(foundUser);
//        }
//        catch (InvalidParameterException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

}
