package lk.ijse.gdse67.greenShadow.customerStatusCodes;

import lk.ijse.gdse67.greenShadow.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedCropStatusCodes implements VehicleStatus {
    private int statusCode;
    private String statusMessage;
}
