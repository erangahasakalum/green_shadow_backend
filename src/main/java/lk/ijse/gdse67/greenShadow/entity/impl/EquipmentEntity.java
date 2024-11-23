package lk.ijse.gdse67.greenShadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.gdse67.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String equipmentCode;
    private String Name;
    private String type;
    private String status;
    private int availableCount;
  /*  @OneToMany(mappedBy = "equipmentEntity")
    private List<StaffEquipmentDetailsEntity> staffEquipmentDetailsList;*/
    @ManyToMany
    @JoinTable(
            name = "equipment_field_details",
            joinColumns = @JoinColumn(name = "equipmentCode"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode")
    )
    private List<FieldEntity> fieldList;

}
