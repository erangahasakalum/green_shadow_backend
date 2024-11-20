package lk.ijse.gdse67.greenShadow.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lk.ijse.gdse67.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "log")
public class LogEntity implements SuperEntity {
    @Id
    private String logCode;
    private String logDate;
    private String details;
    private String observedImage;
    @ManyToMany
    private List<FieldEntity> fields;
    @ManyToMany
    private List<CropEntity> crops;
    @ManyToMany
    private List<StaffEntity> staffs;


}
