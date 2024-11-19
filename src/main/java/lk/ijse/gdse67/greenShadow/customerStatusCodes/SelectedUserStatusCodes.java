package lk.ijse.gdse67.greenShadow.customerStatusCodes;

import lk.ijse.gdse67.greenShadow.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserStatusCodes implements UserStatus {
    private int errorCode;
    private String errorMessage;
}
