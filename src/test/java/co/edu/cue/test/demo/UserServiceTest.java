package co.edu.cue.test.demo;

import co.edu.cue.test.demo.Repositories.UserRepository;
import co.edu.cue.test.demo.mapping.dtos.UserDto;
import co.edu.cue.test.demo.mapping.mappers.UserMapper;
import co.edu.cue.test.demo.model.User;
import co.edu.cue.test.demo.service.Impl.UserServiceImpl;
import co.edu.cue.test.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setPassword("password");
        user.setEmail("john.doe@example.com");

        userDto = new UserDto(
                1L,
                "John Doe",
                "password",
                "john.doe@example.com"
        );
    }

    @Test
    void createUserTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto savedUserDto = userService.createUser(userDto);
        assertNotNull(savedUserDto);
        assertEquals(userDto.name(), savedUserDto.name());
    }

    @Test
    void getUserByIdTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        UserDto foundUserDto = userService.getUserById(1L);
        assertNotNull(foundUserDto);
        assertEquals(userDto.name(), foundUserDto.name());
    }

    @Test
    void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<UserDto> users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals(userDto.name(), users.get(0).name());
    }

    @Test
    void updateUserTest() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto updatedUserDto = userService.updateUser(1L, userDto);
        assertNotNull(updatedUserDto);
        assertEquals(userDto.name(), updatedUserDto.name());
    }

    @Test
    void updateUserNotFoundTest() {
        when(userRepository.existsById(anyLong())).thenReturn(false);
        UserDto updatedUserDto = userService.updateUser(1L, userDto);
        assertNull(updatedUserDto);
    }

    @Test
    void deleteUserTest() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        boolean isDeleted = userService.deleteUser(1L);
        assertTrue(isDeleted);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUserNotFoundTest() {
        when(userRepository.existsById(anyLong())).thenReturn(false);
        boolean isDeleted = userService.deleteUser(1L);
        assertFalse(isDeleted);
        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    void loginTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        UserDto loginUserDto = userService.login("john.doe@example.com", "password");
        assertNotNull(loginUserDto);
        assertEquals(userDto.email(), loginUserDto.email());
    }

    @Test
    void loginInvalidCredentialsTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        UserDto loginUserDto = userService.login("john.doe@example.com", "wrongpassword");
        assertNull(loginUserDto);
    }
}