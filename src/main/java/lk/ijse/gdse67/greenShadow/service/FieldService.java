package lk.ijse.gdse67.greenShadow.service;

import lk.ijse.gdse67.greenShadow.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    void updateField(FieldDTO fieldDTO ,String id);
    void deleteField(String id);
    List<FieldDTO> getAllField();
}
