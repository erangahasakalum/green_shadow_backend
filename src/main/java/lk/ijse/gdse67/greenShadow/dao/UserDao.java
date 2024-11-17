package lk.ijse.gdse67.greenShadow.dao;

import lk.ijse.gdse67.greenShadow.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,String> {
}
