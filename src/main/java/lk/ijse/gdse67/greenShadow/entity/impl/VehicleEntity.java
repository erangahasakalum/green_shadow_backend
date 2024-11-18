package lk.ijse.gdse67.greenShadow.entity.impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String licensePlate;
    private String name;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "staffCode")
    private StaffEntity staff;



}
