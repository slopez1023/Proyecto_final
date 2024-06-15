package co.edu.cue.test.demo.Controllers;


import co.edu.cue.test.demo.mapping.dtos.UserDto;
import co.edu.cue.test.demo.service.Impl.UserServiceImpl;
import co.edu.cue.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @RestController
    @RequestMapping("/api/users")
    public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping
        public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
            UserDto createdUser = userService.createUser(userDto);
            return ResponseEntity.ok(createdUser);
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
            UserDto user = userService.getUserById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        }

        @GetMapping
        public ResponseEntity<List<UserDto>> getAllUsers() {
            List<UserDto> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
            UserDto updatedUser = userService.updateUser(id, userDto);
            if (updatedUser == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedUser);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            boolean isDeleted = userService.deleteUser(id);
            if (!isDeleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/login")
        public ResponseEntity<UserDto> login(@RequestParam String email, @RequestParam String password) {
            UserDto user = userService.login(email, password);
            if (user == null) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(user);
        }
    }
