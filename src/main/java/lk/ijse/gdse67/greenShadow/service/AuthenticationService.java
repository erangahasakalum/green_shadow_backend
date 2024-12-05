package lk.ijse.gdse67.greenShadow.service;

import lk.ijse.gdse67.greenShadow.secure.SignUp;
import lk.ijse.gdse67.greenShadow.secure.SingIn;
import lk.ijse.gdse67.greenShadow.secureAndResponse.response.JwtAuthResponse;

public interface AuthenticationService {
    JwtAuthResponse signUp (SignUp signUp);
    JwtAuthResponse signIn (SingIn signUp);
    JwtAuthResponse refreshToken(String refreshToken);
//    void changePassword(ChangePasswordDTO changePasswordDTO);

}
