package lk.ijse.gdse67.greenShadow.entity.impl;
import jakarta.persistence.*;
import lk.ijse.gdse67.greenShadow.entity.Gender;
import lk.ijse.gdse67.greenShadow.entity.Role;
import lk.ijse.gdse67.greenShadow.entity.SuperEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String memberCode;
    private String firstName;
    private String lastName;
    private LocalDate joinedDate;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String designation;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "staff")
    private List<VehicleEntity> vehicleList;
    @ManyToMany(mappedBy = "staffList")
    private List<FieldEntity> fieldList;
    @ManyToMany(mappedBy = "staffList")
    private List<LogEntity> logList;
    @ManyToMany(mappedBy = "staffCodeList")
    private List<EquipmentEntity> equipmentList;



}
