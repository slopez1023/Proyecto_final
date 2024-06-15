package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.VehicleRepository;
import co.edu.cue.test.demo.mapping.dtos.VehicleDto;
import co.edu.cue.test.demo.mapping.mappers.VehicleMapper;
import co.edu.cue.test.demo.model.Vehicle;
import co.edu.cue.test.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para gestionar vehículos.
 */
@Service
@Validated
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Crea un nuevo vehículo.
     *
     * @param vehicleDto DTO del vehículo a crear.
     * @return DTO del vehículo creado.
     *
     * Este método toma un objeto VehicleDto, lo convierte en una entidad Vehicle,
     * guarda la entidad en el repositorio y devuelve un DTO con los datos del vehículo guardado.
     */
    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(vehicleDto.type());
        vehicle.setCategory(vehicleDto.category());
        vehicle.setPrice(vehicleDto.price());
        vehicle.setAvailable(vehicleDto.available());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return new VehicleDto(savedVehicle.getId(), savedVehicle.getType(), savedVehicle.getCategory(), savedVehicle.getPrice(), savedVehicle.isAvailable());
    }

    /**
     * Obtiene un vehículo por su ID.
     *
     * @param id ID del vehículo a buscar.
     * @return DTO del vehículo encontrado, o null si no se encuentra.
     *
     * Este método busca un vehículo en el repositorio por su ID y, si lo encuentra,
     * devuelve un DTO con los datos del vehículo. Si no lo encuentra, devuelve null.
     */
    @Override
    public VehicleDto getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(v -> new VehicleDto(v.getId(), v.getType(), v.getCategory(), v.getPrice(), v.isAvailable())).orElse(null);
    }

    /**
     * Obtiene todos los vehículos.
     *
     * @return Lista de DTOs de todos los vehículos.
     *
     * Este método obtiene todas las entidades Vehicle del repositorio, las convierte en DTOs
     * y devuelve una lista de estos DTOs.
     */
    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(v -> new VehicleDto(v.getId(), v.getType(), v.getCategory(), v.getPrice(), v.isAvailable()))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un vehículo existente.
     *
     * @param id ID del vehículo a actualizar.
     * @param vehicleDto DTO del vehículo con los nuevos datos.
     * @return DTO del vehículo actualizado, o null si no se encuentra.
     *
     * Este método busca un vehículo por su ID. Si lo encuentra, actualiza los datos del vehículo
     * con la información proporcionada en el DTO, guarda la entidad actualizada en el repositorio
     * y devuelve un DTO con los datos del vehículo actualizado. Si no lo encuentra, devuelve null.
     */
    @Override
    public VehicleDto updateVehicle(Long id, VehicleDto vehicleDto) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setType(vehicleDto.type());
            vehicle.setCategory(vehicleDto.category());
            vehicle.setPrice(vehicleDto.price());
            vehicle.setAvailable(vehicleDto.available());
            Vehicle updatedVehicle = vehicleRepository.save(vehicle);
            return new VehicleDto(updatedVehicle.getId(), updatedVehicle.getType(), updatedVehicle.getCategory(), updatedVehicle.getPrice(), updatedVehicle.isAvailable());
        }
        return null;
    }

    /**
     * Elimina un vehículo por su ID.
     *
     * @param id ID del vehículo a eliminar.
     * @return true si el vehículo fue eliminado, false si no se encuentra.
     *
     * Este método verifica si un vehículo existe por su ID. Si existe, lo elimina del repositorio
     * y devuelve true. Si no existe, devuelve false.
     */
    @Override
    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
