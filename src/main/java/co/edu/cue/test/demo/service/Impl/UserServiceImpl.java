package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.UserRepository;
import co.edu.cue.test.demo.mapping.dtos.UserDto;
import co.edu.cue.test.demo.mapping.mappers.UserMapper;
import co.edu.cue.test.demo.model.User;
import co.edu.cue.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
        private UserRepository userRepository;

        @Override
        public UserDto createUser(UserDto userDto){
            User user = UserMapper.mapFromDTO(userDto);
            User savedUser = userRepository.save(user);
            return UserMapper.mapFrom(savedUser);
        }

        @Override
        public UserDto getUserById(Long id){
            Optional<User> user = userRepository.findById(id);
            return user.map(UserMapper::mapFrom).orElse(null);
        }

        @Override
        public List<UserDto> getAllUsers(){
            return userRepository.findAll().stream()
                    .map(UserMapper::mapFrom)
                    .collect(Collectors.toList());
        }

        @Override
        public UserDto updateUser(Long id, UserDto userDto){
            if (!userRepository.existsById(id)){
                return null;
            }
            User user = UserMapper.mapFromDTO(userDto);
            user.setId(id);
            User updatedUser = userRepository.save(user);
            return UserMapper.mapFrom(updatedUser);
        }

        @Override
        public boolean deleteUser(Long id){
            if (!userRepository.existsById(id)){
                return false;
            }
            userRepository.deleteById(id);
            return true;
        }

    @Override
    public UserDto login(String email, String password){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return UserMapper.mapFrom(user);
            }
        }
        return null;
        }
    }
