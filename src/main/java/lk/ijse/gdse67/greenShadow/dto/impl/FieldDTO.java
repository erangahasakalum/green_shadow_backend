package lk.ijse.gdse67.greenShadow.dto.impl;

import lk.ijse.gdse67.greenShadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FieldDTO implements SuperDTO {
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private String extendSize;
    private String fieldImage1;
    private String fieldImage2;

}
