package lk.ijse.gdse67.greenShadow.dto.impl;

import lk.ijse.gdse67.greenShadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDTO implements SuperDTO {
    @Id
    private String fieldCode;
    private String name;
    private String location;
    private double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<String> equipmentsList;
    private List<String> memberCodeList;
    private List<String> logCodeList;
    private List<String> cropCodeList;

}
