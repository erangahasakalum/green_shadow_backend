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

public class LogDTO implements SuperDTO {
    @Id
    private String logCode;
    private String date;
    private String logDetails;
    private String observedImage;
    private List<String> staffList;
    private List<String> cropList;
    private List<String> fieldList;
}
