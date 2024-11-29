package lk.ijse.gdse67.greenShadow.entity.impl;
import jakarta.persistence.*;
import lk.ijse.gdse67.greenShadow.entity.SuperEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "crop")
public class CropEntity implements SuperEntity {
    @Id
    private String cropCode;
    private String cropName;
    private String scientificName;
    private String category;
    private String season;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    @ManyToMany(mappedBy = "cropList")
    private List<LogEntity> logList;
    @ManyToMany(mappedBy = "cropList")
    private List<FieldEntity> fieldList;
}
