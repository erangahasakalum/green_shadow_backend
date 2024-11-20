package lk.ijse.gdse67.greenShadow.dao;

import lk.ijse.gdse67.greenShadow.entity.impl.CropEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropDao extends JpaRepository<CropEntity,String> {
    @Query(value = "SELECT * FROM crop WHERE crop_id = (SELECT crop_id FROM crop ORDER BY CAST(SUBSTRING(crop_id, 5) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    VehicleEntity findLastRowNative();
}
