package lk.ijse.gdse67.greenShadow.service.impl;

import lk.ijse.gdse67.greenShadow.dao.CropDao;
import lk.ijse.gdse67.greenShadow.dao.FieldDao;
import lk.ijse.gdse67.greenShadow.dto.impl.CropDTO;
import lk.ijse.gdse67.greenShadow.dto.impl.FieldDTO;
import lk.ijse.gdse67.greenShadow.dto.impl.LogDTO;
import lk.ijse.gdse67.greenShadow.dto.impl.VehicleDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.*;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.CropService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping cropMapping;

    @Autowired
    private FieldDao fieldDao;


    @Override
    public void saveCrops(CropDTO cropDTO) {
        int id = 0;
        CropEntity lastRowNative = cropDao.findLastRowNative();
        if (lastRowNative != null){
            try {
                String[] parts = lastRowNative.getCropCode().split("-");
                id = Integer.parseInt(parts[1]);
            }catch (Exception e){
                throw new DataPersistException(e.getMessage());
            }
        }
        cropDTO.setCropCode("CROP-" + ++id);

        CropEntity cropEntity = cropMapping.toCropEntity(cropDTO);

        List<FieldEntity> fieldEntities = new ArrayList<>();
        for (String field_id : cropDTO.getFieldCodeList()){
            if (fieldDao.existsById(field_id)){
                fieldEntities.add(fieldDao.getReferenceById(field_id));
            }
        }
        cropEntity.setFieldList(fieldEntities);

        for (FieldEntity fieldEntity : fieldEntities){
            fieldEntity.getCropList().add(cropEntity);
        }
        CropEntity saved = cropDao.save(cropEntity);
        if (saved == null) {
            throw new DataPersistException("crop not saved");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropDTO> cropDto = new ArrayList<>();

        for (CropEntity crop : cropDao.findAll()) {

            List<String> fieldList = new ArrayList<>();
            List<String> logList = new ArrayList<>();

            for (LogEntity logEntity : crop.getLogList()) {
                logList.add(logEntity.getLogCode());
            }
            for (FieldEntity fieldEntity : crop.getFieldList()) {
                fieldList.add(fieldEntity.getFieldCode());
            }

            cropDto.add(new CropDTO(crop.getCropCode(), crop.getCropName(), crop.getScientificName(), crop.getCategory(), crop.getCropImage(),
                    crop.getSeason(), fieldList,logList));
        }
        return cropDto;
    }

    @Override
    public void deleteCrop(String id) {
        Optional<CropEntity> referenceById = cropDao.findById(id);
        if (referenceById.isPresent()) {
            cropDao.deleteById(id);
        } else {
            throw new DataPersistException("crop not deleted");
        }
    }

    @Override
    public void updateCrop(CropDTO cropDTO, String id) {
        /*public void updateCrop(CropDTO cropDTO) {
            CropEntity cropEntity = mapping.toCropEntity(cropDTO);
            if (cropDAO.existsById(cropDTO.getCrop_code())){
                Optional<CropEntity> byId = cropDAO.findById(cropDTO.getCrop_code());

                if (byId.isPresent()){


                    byId.get().setCrop_common_name(cropEntity.getCrop_common_name());
                    byId.get().setCrop_scientific_name(cropEntity.getCrop_scientific_name());
                    byId.get().setCrop_image(cropEntity.getCrop_image());
                    byId.get().setCategory(cropEntity.getCategory());
                    byId.get().setSeason(cropEntity.getSeason());

                    List<FieldEntity> fieldList = byId.get().getField_list();

                    CropEntity referenceById = cropDAO.getReferenceById(cropEntity.getCrop_code());

                    for (FieldEntity field : fieldList){
                        field.getCrop_list().remove(referenceById);
                    }
                    byId.get().getField_list().clear();


                    List<FieldEntity> fieldEntities = new ArrayList<>();

                    for (String id : cropDTO.getField_code_list()){
                        if (fieldDAO.existsById(id)){
                            fieldEntities.add(fieldDAO.getReferenceById(id));
                        }
                    }

                    byId.get().getField_list().addAll(fieldEntities);
                }
            }else {
                throw new DataNotFoundException("Cant find Data to Update!");
            }

        }*/

    }
}
