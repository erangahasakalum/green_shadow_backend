package lk.ijse.gdse67.greenShadow.service;

import lk.ijse.gdse67.greenShadow.dto.impl.UserDTO;
import lk.ijse.gdse67.greenShadow.entity.Role;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void deleteUser(String id);
    void updateUser(String id, UserDTO userDTO);

}