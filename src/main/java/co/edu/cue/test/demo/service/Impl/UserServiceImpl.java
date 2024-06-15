package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.UserRepository;
import co.edu.cue.test.demo.mapping.dtos.UserDto;
import co.edu.cue.test.demo.mapping.mappers.UserMapper;
import co.edu.cue.test.demo.model.User;
import co.edu.cue.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para gestionar usuarios.
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param userDto DTO del usuario a crear.
     * @return DTO del usuario creado.
     *
     * Este método toma un objeto UserDto, lo convierte en una entidad User,
     * guarda la entidad en el repositorio y devuelve un DTO con los datos del usuario guardado.
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.name());
        user.setPassword(userDto.password());
        user.setEmail(userDto.email());

        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getPassword(), savedUser.getEmail());
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario a buscar.
     * @return DTO del usuario encontrado, o null si no se encuentra.
     *
     * Este método busca un usuario en el repositorio por su ID y, si lo encuentra,
     * devuelve un DTO con los datos del usuario. Si no lo encuentra, devuelve null.
     */
    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> new UserDto(u.getId(), u.getName(), u.getPassword(), u.getEmail())).orElse(null);
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return Lista de DTOs de todos los usuarios.
     *
     * Este método obtiene todas las entidades User del repositorio, las convierte en DTOs
     * y devuelve una lista de estos DTOs.
     */
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getPassword(), u.getEmail()))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id ID del usuario a actualizar.
     * @param userDto DTO del usuario con los nuevos datos.
     * @return DTO del usuario actualizado, o null si no se encuentra.
     *
     * Este método busca un usuario por su ID. Si lo encuentra, actualiza los datos del usuario
     * con la información proporcionada en el DTO, guarda la entidad actualizada en el repositorio
     * y devuelve un DTO con los datos del usuario actualizado. Si no lo encuentra, devuelve null.
     */
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDto.name());
            user.setPassword(userDto.password());
            user.setEmail(userDto.email());
            User updatedUser = userRepository.save(user);
            return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getPassword(), updatedUser.getEmail());
        }
        return null;
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario a eliminar.
     * @return true si el usuario fue eliminado, false si no se encuentra.
     *
     * Este método verifica si un usuario existe por su ID. Si existe, lo elimina del repositorio
     * y devuelve true. Si no existe, devuelve false.
     */
    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Inicia sesión con el email y contraseña del usuario.
     *
     * @param email Email del usuario.
     * @param password Contraseña del usuario.
     * @return DTO del usuario si las credenciales son correctas, o null si no lo son.
     *
     * Este método busca en el repositorio todos los usuarios, luego verifica si las credenciales proporcionadas
     * coinciden con las de algún usuario. Si las credenciales son correctas, devuelve un DTO del usuario.
     * Si no, devuelve null.
     */
    @Override
    public UserDto login(String email, String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return UserMapper.mapFrom(user);
            }
        }
        return null;
    }
}
