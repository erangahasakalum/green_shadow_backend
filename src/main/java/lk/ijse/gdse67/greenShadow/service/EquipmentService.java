package lk.ijse.gdse67.greenShadow.service;
import lk.ijse.gdse67.greenShadow.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void updateEquipment(EquipmentDTO equipmentDTO ,String id);
    void deleteEquipment(String id);
    List<EquipmentDTO> getAllEquipment();
}
