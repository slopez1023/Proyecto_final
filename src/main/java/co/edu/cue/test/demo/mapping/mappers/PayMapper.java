package co.edu.cue.test.demo.mapping.mappers;

import co.edu.cue.test.demo.mapping.dtos.PayDto;
import co.edu.cue.test.demo.model.Pay;


public class PayMapper {
    public static PayDto mapFrom (Pay reservation){
        return new PayDto(reservation.getId(), reservation.getMounter());
    }
    public static Pay mapFromDTO(PayDto payDto){
        return Pay.builder()
                .id(payDto.id())
                .mounter(payDto.mounter())
                .build();

    }

}
