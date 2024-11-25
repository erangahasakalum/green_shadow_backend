package lk.ijse.gdse67.greenShadow.dto.impl;
import lk.ijse.gdse67.greenShadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO {
    @Id
    private String equipmentCode;
    private String name;
    private String type;
    private String status;
    private int availableCount;
    private List<String> staffCodeList;
    private List<String> fieldList;
}
