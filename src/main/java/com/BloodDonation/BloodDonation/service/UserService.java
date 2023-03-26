package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.dto.UserCreateDTO;
import com.BloodDonation.BloodDonation.dto.UserDTO;

import java.util.UUID;

public interface UserService {
    UserDTO registerUser(UserCreateDTO dto);

    UserDTO getUserById(UUID uuid);

    UserDTO getUserByEmail(String email);
}
