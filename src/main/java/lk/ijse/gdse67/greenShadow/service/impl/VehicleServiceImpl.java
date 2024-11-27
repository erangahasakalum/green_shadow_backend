package lk.ijse.gdse67.greenShadow.service.impl;
import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.VehicleDao;
import lk.ijse.gdse67.greenShadow.dto.impl.VehicleDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.CropEntity;
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
        int id = 0;
        VehicleEntity lastRowNative = vehicleDao.findLastRowNative();
        if (lastRowNative != null){
            try {
                String[] parts = lastRowNative.getVehicleCode().split("-");
                id = Integer.parseInt(parts[1]);
            }catch (Exception e){
                throw new DataPersistException(e.getMessage());
            }
        }
        vehicleDTO.setVehicleCode("VEHICLE-" + ++id);
        VehicleEntity save = vehicleDao.save(vehicleMapping.toVehicleEntity(vehicleDTO));
        if (save == null) {
            throw new DataPersistException("vehicle not saved");
        }
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO, String id) {
        if (vehicleDao.existsById(id)) {
            Optional<VehicleEntity> referenceById = vehicleDao.findById(id);
            referenceById.get().setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            referenceById.get().setName(vehicleDTO.getName());
            referenceById.get().setCategory(vehicleDTO.getCategory());
            referenceById.get().setFuelType(vehicleDTO.getFuelType());
            referenceById.get().setStatus(vehicleDTO.getStatus());
            referenceById.get().setRemark(vehicleDTO.getRemark());
        } else {
            throw new DataPersistException("vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String id) {
        Optional<VehicleEntity> referenceById = vehicleDao.findById(id);
        if (referenceById.isPresent()) {
            vehicleDao.deleteById(id);
        } else {
            throw new DataPersistException("vehicle not found");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleEntity> allVehicles = vehicleDao.findAll();
        return vehicleMapping.toVehicleDtoList(allVehicles);
    }
}
