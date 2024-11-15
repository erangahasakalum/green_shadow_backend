package lk.ijse.gdse67.greenShadow.dto.impl;

import lk.ijse.gdse67.greenShadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO {
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    private String availableCount;
}
