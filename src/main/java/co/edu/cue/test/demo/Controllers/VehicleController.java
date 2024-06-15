package co.edu.cue.test.demo.Controllers;


import co.edu.cue.test.demo.mapping.dtos.VehicleDto;
import co.edu.cue.test.demo.service.Impl.VehicleServiceImpl;
import co.edu.cue.test.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

        @Autowired
        private VehicleService vehicleService;

        @PostMapping
        public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
            VehicleDto createdVehicle = vehicleService.createVehicle(vehicleDto);
            return ResponseEntity.ok(createdVehicle);
        }

        @GetMapping("/{id}")
        public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id) {
            VehicleDto vehicle = vehicleService.getVehicleById(id);
            if (vehicle == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(vehicle);
        }

        @GetMapping
        public ResponseEntity<List<VehicleDto>> getAllVehicles() {
            List<VehicleDto> vehicles = vehicleService.getAllVehicles();
            return ResponseEntity.ok(vehicles);
        }

        @PutMapping("/{id}")
        public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
            VehicleDto updatedVehicle = vehicleService.updateVehicle(id, vehicleDto);
            if (updatedVehicle == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedVehicle);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
            boolean isDeleted = vehicleService.deleteVehicle(id);
            if (!isDeleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        }
    }

