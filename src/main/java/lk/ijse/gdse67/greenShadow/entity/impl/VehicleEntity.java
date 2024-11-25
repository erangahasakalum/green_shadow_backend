package lk.ijse.gdse67.greenShadow.entity.impl;
import jakarta.persistence.*;
import lk.ijse.gdse67.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String Name;
    private String category;
    private String fuelType;
    private String status;
    private String remark;
    @ManyToOne
    @JoinColumn(name = "memberCode")
    private StaffEntity staff;
}
