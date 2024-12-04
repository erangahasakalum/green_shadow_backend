package lk.ijse.gdse67.greenShadow.service.impl;

import lk.ijse.gdse67.greenShadow.dao.CropDao;
import lk.ijse.gdse67.greenShadow.dao.EquipmentDao;
import lk.ijse.gdse67.greenShadow.dao.FieldDao;
import lk.ijse.gdse67.greenShadow.dao.StaffDao;
import lk.ijse.gdse67.greenShadow.dto.impl.FieldDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.*;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.FieldService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private CropDao cropDao;
    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private Mapping fieldMapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        int id = 0;
        FieldEntity lastRowNative = fieldDao.findLastRowNative();
        if (lastRowNative != null) {
            try {
                String[] parts = lastRowNative.getFieldCode().split("-");
                id = Integer.parseInt(parts[1]);
            } catch (Exception e) {
                throw new DataPersistException(e.getMessage());
            }
        }
        fieldDTO.setFieldCode("FIELD-" + ++id);

        FieldEntity fieldEntity = fieldMapping.toFieldEntity(fieldDTO);

        List<StaffEntity> staffEntities = new ArrayList<>();
        for (String staff_id : fieldDTO.getMemberCodeList()) {
            if (staffDao.existsById(staff_id)) {
                staffEntities.add(staffDao.getReferenceById(staff_id));
            }
        }

        List<CropEntity> cropEntities = new ArrayList<>();
        for (String crop_id : fieldDTO.getCropCodeList()) {
            if (cropDao.existsById(crop_id)) {
                cropEntities.add(cropDao.getReferenceById(crop_id));
            }
        }

        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
        for (String equipment_id : fieldDTO.getEquipmentsList()) {
            if (equipmentDao.existsById(equipment_id)) {
                equipmentEntities.add(equipmentDao.getReferenceById(equipment_id));
            }
        }
        fieldEntity.setEquipmentsList(equipmentEntities);
        fieldEntity.setStaffList(staffEntities);
        fieldEntity.setCropList(cropEntities);

        for (EquipmentEntity equipmentEntity : equipmentEntities) {
            equipmentEntity.getFieldList().add(fieldEntity);
        }

        FieldEntity save = fieldDao.save(fieldEntity);
        if (save == null) {
            throw new DataPersistException("field not saved");
        }

    }

    @Override
    public void updateField(FieldDTO fieldDTO, String id) {
     /*   FieldEntity fieldEntity = fieldMapping.toFieldEntity(fieldDTO);
        if (fieldDao.existsById(fieldDTO.getFieldCode())) {
            Optional<FieldEntity> byId = fieldDao.findById(fieldDTO.getFieldCode());

            if (byId.isPresent()) {
                byId.get().setName(fieldEntity.getName());
                byId.get().setLocation(fieldEntity.getLocation());
                byId.get().setExtentSize(fieldEntity.getExtentSize());
                byId.get().setFieldImage1(fieldEntity.getFieldImage1());
                byId.get().setFieldImage2(fieldEntity.getFieldImage2());
                byId.get().setFieldImage2(fieldEntity.getFieldImage2());

                List<FieldEntity> fieldList = byId.get().getField_list();

                CropEntity referenceById = cropDAO.getReferenceById(cropEntity.getCrop_code());

                for (FieldEntity field : fieldList) {
                    field.getCrop_list().remove(referenceById);
                }
                byId.get().getField_list().clear();


                List<FieldEntity> fieldEntities = new ArrayList<>();

                for (String id : cropDTO.getField_code_list()) {
                    if (fieldDAO.existsById(id)) {
                        fieldEntities.add(fieldDAO.getReferenceById(id));
                    }
                }

                byId.get().getField_list().addAll(fieldEntities);
            }
        } else {
            throw new DataPersistException("Cant find Data to Update!");
        }*/
    }


    @Override
    public void deleteField(String id) {
     /*   if (fieldDao.existsById(id)) {
            FieldEntity referenceById = fieldDao.getReferenceById(id);
            List<FieldEntity> fieldList = referenceById.getField_list();
            for (FieldEntity field : fieldList) {
                List<CropEntity> cropList = field.getCrop_list();
                cropList.remove(referenceById);
            }

            referenceById.getField_list().clear();

            cropDAO.delete(referenceById);


        } else {
            throw new DataNotFoundException("Cant find data to delete!");
        }*/
    }


    @Override
    public List<FieldDTO> getAllField() {
        List<FieldDTO> fieldDTOS = new ArrayList<>();
        for (FieldEntity field : fieldDao.findAll()) {

            List<String> staffList = new ArrayList<>();
            List<String> cropList = new ArrayList<>();
            List<String> logList = new ArrayList<>();
            List<String> equipmentList = new ArrayList<>();

            for (StaffEntity staff : field.getStaffList()) {
                staffList.add(staff.getMemberCode());
            }
            for (CropEntity entity : field.getCropList()) {
                cropList.add(entity.getCropCode());
            }
            for (LogEntity logEntity : field.getLogList()) {
                logList.add(logEntity.getLogCode());
            }
            for (EquipmentEntity equipmentEntity : field.getEquipmentsList()) {
                equipmentList.add(equipmentEntity.getEquipmentCode());
            }

            fieldDTOS.add(new FieldDTO(field.getFieldCode(), field.getName(), field.getLocation(), field.getExtentSize(), field.getFieldImage1(),
                    field.getFieldImage2(), equipmentList, staffList, logList, cropList));
        }
        return fieldDTOS;
    }
}
