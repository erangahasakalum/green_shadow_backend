package lk.ijse.gdse67.greenShadow.dto.impl;
import lk.ijse.gdse67.greenShadow.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleStatus {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String name;
    private String category;
    private String fuelType;
    private String status;
    private String remark;
    private String memberCode;


}
