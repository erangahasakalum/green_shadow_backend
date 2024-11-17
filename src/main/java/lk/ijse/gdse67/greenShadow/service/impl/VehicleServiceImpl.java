package lk.ijse.gdse67.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.VehicleDao;
import lk.ijse.gdse67.greenShadow.dto.VehicleStatus;
import lk.ijse.gdse67.greenShadow.dto.impl.VehicleDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.VehicleEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.VehicleService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    private Mapping vehicleMapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        VehicleEntity save = vehicleDao.save(vehicleMapping.toVehicleEntity(vehicleDTO));
        if (save ==null){
            throw new DataPersistException("vehicle not saved");
        }
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO, String id) {

    }

    @Override
    public void deleteVehicle(String id) {

    }

    @Override
    public VehicleStatus getVehicle(String id) {
        return null;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return List.of();
    }
}
