package lk.ijse.gdse67.greenShadow.service.impl;

import lk.ijse.gdse67.greenShadow.dao.UserDAO;
import lk.ijse.gdse67.greenShadow.dto.impl.UserDTO;
import lk.ijse.gdse67.greenShadow.entity.Role;
import lk.ijse.gdse67.greenShadow.entity.impl.UserEntity;
import lk.ijse.gdse67.greenShadow.exeption.UerNotFoundExseption;
import lk.ijse.gdse67.greenShadow.secure.SignUp;
import lk.ijse.gdse67.greenShadow.secure.SingIn;
import lk.ijse.gdse67.greenShadow.secureAndResponse.response.JwtAuthResponse;
import lk.ijse.gdse67.greenShadow.service.AuthenticationService;

import lk.ijse.gdse67.greenShadow.service.JwtService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final Mapping mapping;
    private final UserDAO userDAO;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse signUp(SignUp signUp) {
        UserEntity lastRowNative = userDAO.findLastRowNative();
        int number =0;

        if (lastRowNative != null){
            String[] parts = lastRowNative.getUser_id().split("-");
            number = Integer.parseInt(parts[1]);
        }

        // emailService.sendEmail(signUp.getEmail(),"Email From GreenShadow", "Your user email:  "+signUp.getEmail() +"\n Your temporary password to login: "+signUp.getPassword());
        UserDTO userDTO = UserDTO.builder()
                .user_id("USER-"+ ++number)
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(Role.valueOf(signUp.getRole()))
                .build();

        UserEntity save = userDAO.save(mapping.toUserEntity(userDTO));
        String generateToken = jwtService.genarateToken(save);
        return JwtAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JwtAuthResponse signIn(SingIn signIn) {
        System.out.println("SIGN IN CALLED");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword())
        );
        UserEntity user = userDAO.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generateToken = jwtService.genarateToken(user);
        return JwtAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JwtAuthResponse refreshToken(String refreshToken) {
        //        extract userName
        String user = jwtService.extractUerName(refreshToken);
//        check the user availability in the db
        UserEntity findUser = userDAO.findByEmail(user).orElseThrow(() -> new UerNotFoundExseption("Cant find User"));
        String token = jwtService.refreshToken(findUser);
        return JwtAuthResponse.builder().token(token).build();
    }
}