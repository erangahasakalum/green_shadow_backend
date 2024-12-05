package lk.ijse.gdse67.greenShadow.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUerName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    String genarateToken(UserDetails userDetails);
    String refreshToken(UserDetails userDetails);
}