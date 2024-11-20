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
import java.util.Optional;

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
        if (vehicleDao.existsById(id)){
            Optional<VehicleEntity> referenceById = vehicleDao.findById(id);
            referenceById.get().setLicensePlate(vehicleDTO.getLicensePlate());
            referenceById.get().setName(vehicleDTO.getName());
            referenceById.get().setCategory(vehicleDTO.getCategory());
            referenceById.get().setFuelType(vehicleDTO.getFuelType());
            referenceById.get().setStatus(vehicleDTO.getStatus());
            referenceById.get().setRemarks(vehicleDTO.getRemarks());
        }else {
            throw new DataPersistException("vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String id) {
        Optional<VehicleEntity> referenceById = vehicleDao.findById(id);
        if(referenceById.isPresent()){
            vehicleDao.deleteById(id);
        }else {
            throw new DataPersistException("vehicle not found");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleEntity> allVehicles = vehicleDao.findAll();
        return vehicleMapping.toVehicleDtoList(allVehicles);
    }
}
