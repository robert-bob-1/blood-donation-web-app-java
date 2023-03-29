package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.dto.UserCreateDTO;
import com.BloodDonation.BloodDonation.dto.UserDTO;
import com.BloodDonation.BloodDonation.entity.User;
import com.BloodDonation.BloodDonation.mapper.UserMapper;
import com.BloodDonation.BloodDonation.repository.UserRepository;
import com.BloodDonation.BloodDonation.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO registerUser(User dto){
        User savedUser = userRepository.save(dto);
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO registerUserDTO(UserCreateDTO dto){
        User savedUser = userRepository.save(userMapper.toUser(dto));
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(UUID uuid) {
        Optional<User> foundUser = userRepository.findById(uuid);
        if(foundUser.isPresent())
            return userMapper.toUserDTO(foundUser.get());
        else
            return null;
    }
    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if(foundUser.isPresent())
            return userMapper.toUserDTO(foundUser.get());
        else
            return null;
    }

    @Override
    public User loginUser(String email, String password) {
        Optional<User> foundUser = userRepository.findByEmailAndPassword(email, password);
        if(foundUser.isPresent())
            return (foundUser.get());
        else
            return null;
    }
}
