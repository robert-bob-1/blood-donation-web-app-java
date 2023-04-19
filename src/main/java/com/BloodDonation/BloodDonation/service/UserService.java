package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.dto.UserCreateDTO;
import com.BloodDonation.BloodDonation.dto.UserDTO;
import com.BloodDonation.BloodDonation.entity.users.User;

import java.util.UUID;

public interface UserService {
    UserDTO registerUserDTO(UserCreateDTO dto);
    User registerUser(User dto);

    UserDTO getUserById(UUID uuid);

    User getUserByEmail(String email);

    User loginUser(String email, String password);

    User updateUser(User user);
}
