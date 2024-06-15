package co.edu.cue.test.demo.service;

import co.edu.cue.test.demo.mapping.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
    UserDto login(String email, String password);

}