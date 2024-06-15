package co.edu.cue.test.demo.mapping.mappers;

import co.edu.cue.test.demo.mapping.dtos.VehicleDto;
import co.edu.cue.test.demo.model.Vehicle;


public class VehicleMapper {

    public static VehicleDto mapFrom(Vehicle reservation){
        return new VehicleDto(reservation.getId(), reservation.getType(), reservation.getCategory(), reservation.getPrice(), reservation.isAvailable());
    }
    public static Vehicle mapFromDTO(VehicleDto reservationDto){
        return Vehicle.builder()
                .id(reservationDto.id())
                .type(reservationDto.type())
                .category(reservationDto.category())
                .price(reservationDto.price())
                .available(reservationDto.available())
                .build();
    }

}
