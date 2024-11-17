package lk.ijse.gdse67.greenShadow.dto.impl;
import lk.ijse.gdse67.greenShadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO {
    private String vehicleCode;
    private String licensePlate;
    private String name;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;
    private String staffCode;

}
