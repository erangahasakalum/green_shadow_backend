package lk.ijse.gdse67.greenShadow.dao;
import lk.ijse.gdse67.greenShadow.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
    @Query(value = "SELECT * FROM vehicle WHERE vehicle_code = (SELECT vehicle_code FROM crop ORDER BY CAST(SUBSTRING(vehicle_code, 5) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    VehicleEntity findLastRowNative();
}
