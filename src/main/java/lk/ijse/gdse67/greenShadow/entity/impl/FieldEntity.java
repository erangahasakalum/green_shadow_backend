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
@Table(name ="field" )
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private String extendSize;
    private String fieldImage1;
    private String fieldImage2;
    @ManyToMany
    private List<LogEntity> logs;
    @ManyToMany
    private List<EquipmentEntity> equipments;
    @ManyToMany
    private List<CropEntity> crops;

}
