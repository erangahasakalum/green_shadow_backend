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
@Table(name ="field" )
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String name;
    private String location;
    private double extentSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;
    @ManyToMany(mappedBy = "fieldList")
    private List<EquipmentEntity> equipmentsList;
    @ManyToMany
    @JoinTable(
            name = "field_staff_details",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "memberCode")
    )
    private List<StaffEntity> staffList;
    @ManyToMany(mappedBy = "fieldList")
    private List<LogEntity> logList;
    @ManyToMany
    @JoinTable(
            name = "field_crop_details",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "cropCode")
    )
    private List<CropEntity> cropList;
}

