package co.edu.cue.test.demo.mapping.mappers;


import co.edu.cue.test.demo.mapping.dtos.UserDto;
import co.edu.cue.test.demo.model.User;


public class UserMapper {

    public static UserDto mapFrom (User reservation){
        return  new UserDto(reservation.getId(), reservation.getName(), reservation.getPassword(), reservation.getEmail());
    }
    public static User mapFromDTO(UserDto reservationDto){
        return User.builder()
                .id(reservationDto.id())
                .name(reservationDto.name())
                .password(reservationDto.password())
                .email(reservationDto.email())
                .build();
    }


}
