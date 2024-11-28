package lk.ijse.gdse67.greenShadow.service.impl;
import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.UserDao;
import lk.ijse.gdse67.greenShadow.dto.impl.UserDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.UserEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.exeption.NotFoundException;
import lk.ijse.gdse67.greenShadow.service.UserService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping userMapping;
    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity save = userDao.save(userMapping.toUserEntity(userDTO));
        if (save == null) {
            throw new DataPersistException("user not saved");
        }
    }

    @Override
    public void deleteUser(String id) {
        Optional<UserEntity> exitUser = userDao.findById(id);
        if (exitUser.isPresent()) {
            userDao.delete(exitUser.get());
        }else {
            throw new DataPersistException("user not found");
        }
    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {
        if (userDao.existsById(id)){
            Optional<UserEntity> referenceById = userDao.findById(id);
            referenceById.get().setEmail(userDTO.getEmail());
            referenceById.get().setPassword(userDTO.getPassword());
            referenceById.get().setRole(userDTO.getRole());
        }else {
            throw new NotFoundException("This Id Not in database!!!");
        }

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapping.toUserDtoList(userDao.findAll());
    }

}
