package lk.ijse.gdse67.greenShadow.service.impl;

import lk.ijse.gdse67.greenShadow.dao.UserDAO;
import lk.ijse.gdse67.greenShadow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    // private final EmailService emailService;

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDAO.findByEmail(username).
                        orElseThrow(()->new UsernameNotFoundException("User Not Found"));

    }

//    @Override
//    public boolean sendCodeToChangePassword(UserWithKey userWithKey) {
//        Optional<UserEntity> byEmail = userDAO.findByEmail(userWithKey.getEmail());
//        if (byEmail.isPresent()){
//          //  emailService.sendEmail(userWithKey.getEmail(),"Your password change Code From Green Shadow(PVT) Ltd.","Dont share with anyone:  "+userWithKey.getCode());
//            return true;
//        }
//        return false;
//    }
}