package lk.ijse.gdse67.greenShadow.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
//    boolean sendCodeToChangePassword(UserWithKey userWithKey);
}
