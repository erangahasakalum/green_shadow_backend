package lk.ijse.gdse67.greenShadow.service.impl;

import lk.ijse.gdse67.greenShadow.dao.CropDao;
import lk.ijse.gdse67.greenShadow.dto.impl.CropDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.CropEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.FieldEntity;
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
        System.out.println(cropDTO);
        CropEntity saved = cropDao.save(cropMapping.toCropEntity(cropDTO));
        System.out.println(saved);
        if (saved == null) {
            throw new DataPersistException("crop not saved");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return cropMapping.toCropDtoList(cropDao.findAll());
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

    }
}
