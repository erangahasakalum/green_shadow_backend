package lk.ijse.gdse67.greenShadow.service;

import lk.ijse.gdse67.greenShadow.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrops(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    void deleteCrop(String id);
    void updateCrop(CropDTO cropDTO ,String id);
}
