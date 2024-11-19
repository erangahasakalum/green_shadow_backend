package lk.ijse.gdse67.greenShadow.controller;
import lk.ijse.gdse67.greenShadow.customerStatusCodes.SelectedVehicleStatusCodes;
import lk.ijse.gdse67.greenShadow.dto.VehicleStatus;
import lk.ijse.gdse67.greenShadow.dto.impl.VehicleDTO;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.exeption.NotFoundException;
import lk.ijse.gdse67.greenShadow.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        System.out.println(vehicleDTO);
        try {
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable("vehicleCode") String vehicleCode) {
        try {
            vehicleService.updateVehicle(vehicleDTO, vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        try {
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping(value = "/{vehicleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getSelectedVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        if (vehicleCode.isEmpty()) {
            return new SelectedVehicleStatusCodes(1, "vehicleCode is Empty");
        } else {
            return vehicleService.getSelectedVehicle(vehicleCode);
        }
    }

}
