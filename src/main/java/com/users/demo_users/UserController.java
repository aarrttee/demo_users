package com.users.demo_users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User addUser(@Validated @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    private record ErrorResponse(String message) {
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @GetMapping
    public Object getByFirstNameLastName(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName
    ) {
        if (firstName != null && lastName != null) {
            return userService.getByFirstNameAndLastName(firstName, lastName);
        } else {
            return userService.getAllUsers();
        }
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Validated @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
