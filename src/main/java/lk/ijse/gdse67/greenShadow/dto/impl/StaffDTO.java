package lk.ijse.gdse67.greenShadow.dto.impl;
import lk.ijse.gdse67.greenShadow.dto.SuperDTO;
import lk.ijse.gdse67.greenShadow.entity.Gender;
import lk.ijse.gdse67.greenShadow.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StaffDTO implements SuperDTO {
    private String staffCode;
    private String firstName;
    private String lastName;
    private LocalDate joinedDate;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String designation;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Role role;
}
