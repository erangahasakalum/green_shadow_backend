package lk.ijse.gdse67.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.UserDao;
import lk.ijse.gdse67.greenShadow.dto.impl.UserDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.UserEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.UserService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity save = userDao.save(mapping.toUserEntity(userDTO));
        if (save == null) {
            throw new DataPersistException("user not saved");
        }
    }

    @Override
    public void deleteUser(String id) {

    }
}
