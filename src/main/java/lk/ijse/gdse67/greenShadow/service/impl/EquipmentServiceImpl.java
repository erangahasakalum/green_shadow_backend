package lk.ijse.gdse67.greenShadow.service.impl;

import lk.ijse.gdse67.greenShadow.dao.EquipmentDao;
import lk.ijse.gdse67.greenShadow.dto.impl.EquipmentDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.EquipmentEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.VehicleEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.EquipmentService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private Mapping equipmentMapping;

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        int id = 0;
        EquipmentEntity lastId = equipmentDao.findLastRowNative();
        if (lastId != null) {
            try {
                String[] split = lastId.getEquipmentCode().split("-");
                id = Integer.parseInt(split[0]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid vehicle code");
            }
        }
        equipmentDTO.setEquipmentCode("EQUIPMENT -" + ++id);
        EquipmentEntity save = equipmentDao.save(equipmentMapping.toEquipmentEntity(equipmentDTO));
        if (save == null) {
            throw new DataPersistException("vehicle not saved");
        }
    }

    @Override
    public void updateEquipment(EquipmentDTO equipmentDTO, String id) {

    }

    @Override
    public void deleteEquipment(String id) {

    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return List.of();
    }
}
