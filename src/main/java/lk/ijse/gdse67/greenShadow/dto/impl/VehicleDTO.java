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
public class VehicleDTO implements SuperDTO {
    private String vehicleId;
    private String licensePlate;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;
}
