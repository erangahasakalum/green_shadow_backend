package lk.ijse.gdse67.greenShadow.service.impl;
import lk.ijse.gdse67.greenShadow.dao.EquipmentDao;
import lk.ijse.gdse67.greenShadow.dao.FieldDao;
import lk.ijse.gdse67.greenShadow.dao.StaffDao;
import lk.ijse.gdse67.greenShadow.dto.impl.EquipmentDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.EquipmentEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.FieldEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.StaffEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.VehicleEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.EquipmentService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private Mapping equipmentMapping;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private StaffDao staffDao;

   @Autowired
   private FieldDao fieldDao;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        int id = 0;
        EquipmentEntity lastRowNative = equipmentDao.findLastRowNative();
        if (lastRowNative != null){
            try {
                String[] parts = lastRowNative.getEquipmentCode().split("-");
                id = Integer.parseInt(parts[1]);
            }catch (Exception e){
                throw new DataPersistException(e.getMessage());
            }
        }

        equipmentDTO.setEquipmentCode("EQUIPMENT-" + ++id);

        EquipmentEntity equipmentEntity = equipmentMapping.toEquipmentEntity(equipmentDTO);

        List<StaffEntity> staffEntities = new ArrayList<>();
        for (String staff_id : equipmentDTO.getStaffCodeList()){
            if (staffDao.existsById(staff_id)){
                staffEntities.add(staffDao.getReferenceById(staff_id));
            }
        }

        List<FieldEntity> fieldEntities = new ArrayList<>();
        for (String equipment_id : equipmentDTO.getFieldList()){
            if (fieldDao.existsById(equipment_id)){
                fieldEntities.add(fieldDao.getReferenceById(equipment_id));
            }
        }
        equipmentEntity.setStaffCodeList(staffEntities);
        equipmentEntity.setFieldList(fieldEntities);
        EquipmentEntity save = equipmentDao.save(equipmentEntity);
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
        return equipmentMapping.toEquipmenttoList(equipmentDao.findAll());
    }
}
