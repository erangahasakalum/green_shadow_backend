package lk.ijse.gdse67.greenShadow.dao;
import lk.ijse.gdse67.greenShadow.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropDao extends JpaRepository<CropEntity,String> {
    @Query(value = "SELECT * FROM crop WHERE crop_code = (SELECT crop_code FROM crop ORDER BY CAST(SUBSTRING(crop_code, 6) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    CropEntity findLastRowNative();

}
