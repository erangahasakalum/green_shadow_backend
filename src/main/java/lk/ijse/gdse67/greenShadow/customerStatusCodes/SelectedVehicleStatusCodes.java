package lk.ijse.gdse67.greenShadow.customerStatusCodes;

import lk.ijse.gdse67.greenShadow.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedVehicleStatusCodes implements VehicleStatus {
    private int statusCode;
    private String statusMessage;
}
