import com.example.tripapi.models.Vehicle;
import com.example.tripapi.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Endpoint to list all vehicles
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // Endpoint to create a new vehicle
    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
    }

    // Endpoint to create multiple vehicles
    @PostMapping("/vehicles/batch")
    public ResponseEntity<List<Vehicle>> createVehiclesBatch(@RequestBody List<Vehicle> vehicles) {
        List<Vehicle> createdVehicles = vehicleService.createVehicles(vehicles);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicles);
    }
}
