package lk.ijse.gdse67.greenShadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.gdse67.greenShadow.entity.SuperEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String equipmentCode;
    private String Name;
    private String type;
    private String status;
    private int availableCount;
    @ManyToMany
    @JoinTable(
            name = "equipment_staff_details",
            joinColumns = @JoinColumn(name = "equipmentCode"),
            inverseJoinColumns = @JoinColumn(name = "memberCode")
    )
    private List<StaffEntity> staffCodeList;
    @ManyToMany
    @JoinTable(
            name = "equipment_field_details",
            joinColumns = @JoinColumn(name = "equipmentCode"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode")
    )
    private List<FieldEntity> fieldList;

}
