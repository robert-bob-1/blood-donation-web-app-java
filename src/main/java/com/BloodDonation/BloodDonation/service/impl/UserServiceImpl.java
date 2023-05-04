package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.dto.UserCreateDTO;
import com.BloodDonation.BloodDonation.dto.UserDTO;
import com.BloodDonation.BloodDonation.entity.users.User;
import com.BloodDonation.BloodDonation.mapper.UserMapper;
import com.BloodDonation.BloodDonation.repository.UserRepository;
import com.BloodDonation.BloodDonation.service.UserService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
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
    public User registerUser(User user){
        User savedUser = userRepository.save(user);
        return savedUser;
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
            throw new InvalidParameterException("There is no user with id " + uuid);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        return foundUser.orElse(null);
    }

    @Override
    public User loginUser(String email, String password) {
        Optional<User> foundUser = userRepository.findByEmailAndPassword(email, password);
        if(foundUser.isPresent())
            return (foundUser.get());
        else
            return null;
    }

    @Override
    public User updateUser(User newUser) {
        userRepository
                .findById(newUser.id)
                .ifPresent(user -> {
                    user.email = newUser.email;
                    user.password = newUser.password;
                    user.firstName = newUser.firstName;
                    user.lastName = newUser.lastName;

                    userRepository.save(user);
                });
        return newUser;
    }
}
