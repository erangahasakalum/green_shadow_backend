package lk.ijse.gdse67.greenShadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.gdse67.greenShadow.entity.Gender;
import lk.ijse.gdse67.greenShadow.entity.Role;
import lk.ijse.gdse67.greenShadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joinedDate;
    private String dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    


}
