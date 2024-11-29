package lk.ijse.gdse67.greenShadow.service.impl;
import lk.ijse.gdse67.greenShadow.dao.CropDao;
import lk.ijse.gdse67.greenShadow.dao.EquipmentDao;
import lk.ijse.gdse67.greenShadow.dao.FieldDao;
import lk.ijse.gdse67.greenShadow.dao.StaffDao;
import lk.ijse.gdse67.greenShadow.dto.impl.FieldDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.CropEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.EquipmentEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.FieldEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.StaffEntity;
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
    private Mapping fieldMapping;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        int id = 0;
        FieldEntity lastRowNative = fieldDao.findLastRowNative();
        if (lastRowNative != null){
            try {
                String[] parts = lastRowNative.getFieldCode().split("-");
                id = Integer.parseInt(parts[1]);
            }catch (Exception e){
                throw new DataPersistException(e.getMessage());
            }
        }
        fieldDTO.setFieldCode("FIELD-" + ++id);

        FieldEntity fieldEntity = fieldMapping.toFieldEntity(fieldDTO);

        List<StaffEntity> staffEntities = new ArrayList<>();
        for (String staff_id : fieldDTO.getMemberCodeList()){
            if (staffDao.existsById(staff_id)){
                staffEntities.add(staffDao.getReferenceById(staff_id));
            }
        }

        List<CropEntity> cropEntities = new ArrayList<>();
        for (String crop_id : fieldDTO.getCropCodeList()){
            if (cropDao.existsById(crop_id)){
                cropEntities.add(cropDao.getReferenceById(crop_id));
            }
        }

        fieldEntity.setStaffList(staffEntities);
        fieldEntity.setCropList(cropEntities);
        FieldEntity save = fieldDao.save(fieldEntity);
        if (save == null) {
            throw new DataPersistException("field not saved");
        }

    }

    @Override
    public void updateField(FieldDTO fieldDTO, String id) {

    }

    @Override
    public void deleteField(String id) {
        Optional<FieldEntity> referenceById =fieldDao.findById(id);
        if (referenceById.isPresent()) {
            fieldDao.delete(referenceById.get());
        }else {
            throw new DataPersistException("field not deleted");
        }
    }

    @Override
    public List<FieldDTO> getAllField() {
        return fieldMapping.toFieldDtoList(fieldDao.findAll());
    }
}
