package co.edu.cue.test.demo.service;

// VehicleService.java
import co.edu.cue.test.demo.mapping.dtos.VehicleDto;

import java.util.List;

public interface VehicleService {
    VehicleDto createVehicle(VehicleDto vehicleDto);
    VehicleDto getVehicleById(Long id);
    List<VehicleDto> getAllVehicles();
    VehicleDto updateVehicle(Long id, VehicleDto vehicleDto);
    boolean deleteVehicle(Long id);
}
