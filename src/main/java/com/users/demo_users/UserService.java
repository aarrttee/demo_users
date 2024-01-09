package com.users.demo_users;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found " + id);
        }
        return user.get();
    }

    public User getByFirstNameAndLastName(String firstName, String lastName) {
        Optional<User> user = Optional.ofNullable(userRepository.findByFirstNameAndLastName(firstName, lastName));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with such name was not found");
        }
        return user.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUserData) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (updatedUserData.getFirstName() != null) {
                existingUser.setFirstName(updatedUserData.getFirstName());
            }
            if (updatedUserData.getLastName() != null) {
                existingUser.setLastName(updatedUserData.getLastName());
            }

            return userRepository.save(existingUser);
        } else {
            // Handle user not found scenario (throw an exception or return null, etc.)
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }

    public void deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            // Handle user not found scenario (throw an exception or return null, etc.)
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }
}
