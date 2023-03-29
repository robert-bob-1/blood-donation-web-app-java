package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.dto.UserCreateDTO;
import com.BloodDonation.BloodDonation.dto.UserDTO;
import com.BloodDonation.BloodDonation.entity.User;

import java.util.UUID;

public interface UserService {
    UserDTO registerUserDTO(UserCreateDTO dto);
    UserDTO registerUser(User dto);

    UserDTO getUserById(UUID uuid);

    UserDTO getUserByEmail(String email);

    User loginUser(String email, String password);
}
