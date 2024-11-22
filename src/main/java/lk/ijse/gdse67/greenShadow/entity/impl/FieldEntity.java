package lk.ijse.gdse67.greenShadow.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String logCode;
    private String date;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @ManyToMany
    @JoinTable(
            name = "staff_log_details",
            joinColumns = @JoinColumn(name = "logCode"),
            inverseJoinColumns = @JoinColumn(name = "memberCode")
    )
    private List<StaffEntity> staffList;
    @ManyToMany
    @JoinTable(
            name = "log_crop_details",
            joinColumns = @JoinColumn(name = "logCode"),
            inverseJoinColumns = @JoinColumn(name = "cropCode")
    )
    private List<CropEntity> cropList;
    @ManyToMany
    @JoinTable(
            name = "field_log_details",
            joinColumns = @JoinColumn(name = "logCode"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode")
    )
    private List<FieldEntity> fieldList;
}

