package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.VehicleRepository;
import co.edu.cue.test.demo.mapping.dtos.VehicleDto;
import co.edu.cue.test.demo.mapping.mappers.VehicleMapper;
import co.edu.cue.test.demo.model.Vehicle;
import co.edu.cue.test.demo.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
        private VehicleRepository vehicleRepository;

        @Override
        public VehicleDto createVehicle(VehicleDto vehicleDto) {
            Vehicle vehicle = VehicleMapper.mapFromDTO(vehicleDto);
            Vehicle savedVehicle = vehicleRepository.save(vehicle);
            return VehicleMapper.mapFrom(savedVehicle);
        }

        @Override
        public VehicleDto getVehicleById(Long id) {
            Optional<Vehicle> vehicle = vehicleRepository.findById(id);
            return vehicle.map(VehicleMapper::mapFrom).orElse(null);
        }

        @Override
        public List<VehicleDto> getAllVehicles() {
            return vehicleRepository.findAll().stream()
                    .map(VehicleMapper::mapFrom)
                    .collect(Collectors.toList());
        }

        @Override
        public VehicleDto updateVehicle(Long id, VehicleDto vehicleDto) {
            if (!vehicleRepository.existsById(id)) {
                return null;
            }
            Vehicle vehicle = VehicleMapper.mapFromDTO(vehicleDto);
            vehicle.setId(id);
            Vehicle updatedVehicle = vehicleRepository.save(vehicle);
            return VehicleMapper.mapFrom(updatedVehicle);
        }

        @Override
        public boolean deleteVehicle(Long id) {
            if (!vehicleRepository.existsById(id)) {
                return false;
            }
            vehicleRepository.deleteById(id);
            return true;
        }
    }
