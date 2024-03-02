package com.nsharma.springboot.service.impl;

import com.nsharma.springboot.dto.UserDto;
import com.nsharma.springboot.entity.User;
import com.nsharma.springboot.exception.EmailAlreadyExistException;
import com.nsharma.springboot.exception.ResourceNotFoundException;
import com.nsharma.springboot.mapper.UserMapper;
import com.nsharma.springboot.repository.UserRepository;
import com.nsharma.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistException("Email Already Exist for User");
        }
//        User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto, User.class);
        User newUser = userRepository.save(user);
//        return UserMapper.mapToUserDto(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User", "id", userId)
        );
//        return UserMapper.mapToUserDto(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updateUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updateUser);
        return modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
