package lk.ijse.gdse67.greenShadow.dao;

import lk.ijse.gdse67.greenShadow.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
}
