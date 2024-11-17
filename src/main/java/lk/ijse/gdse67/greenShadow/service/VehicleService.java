package lk.ijse.gdse67.greenShadow.service;

import lk.ijse.gdse67.greenShadow.dto.VehicleStatus;
import lk.ijse.gdse67.greenShadow.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void updateVehicle(VehicleDTO vehicleDTO,String id);
    void deleteVehicle(String id);
    VehicleStatus getVehicle(String id);
    List<VehicleDTO> getAllVehicles();

}
