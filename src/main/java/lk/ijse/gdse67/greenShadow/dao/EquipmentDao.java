package lk.ijse.gdse67.greenShadow.dao;
import lk.ijse.gdse67.greenShadow.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentDao {
    @Query(value = "SELECT * FROM field WHERE equipment_code = (SELECT equipment_code FROM crop ORDER BY CAST(SUBSTRING(equipment_code, 5) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    EquipmentEntity findLastRowNative();
}
