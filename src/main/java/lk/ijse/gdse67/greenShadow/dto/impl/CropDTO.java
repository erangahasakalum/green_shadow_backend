package lk.ijse.gdse67.greenShadow.dto.impl;

import lk.ijse.gdse67.greenShadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CropDTO implements SuperDTO {
    private String cropId;
    private String cropName;
    private String scientificName;
    private String image;
    private String category;
    private String cropSeason;
}
