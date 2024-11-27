package lk.ijse.gdse67.greenShadow.dao;
import lk.ijse.gdse67.greenShadow.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentDao extends JpaRepository<EquipmentEntity, String> {
    @Query(value = "SELECT * FROM equipment WHERE equipment_code = (SELECT equipment_code FROM equipment ORDER BY CAST(SUBSTRING(equipment_code, 11) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    EquipmentEntity findLastRowNative();
}
