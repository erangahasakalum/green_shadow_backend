package lk.ijse.gdse67.greenShadow.dao;
import lk.ijse.gdse67.greenShadow.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FieldDao extends JpaRepository<FieldEntity, String> {
    @Query(value = "SELECT * FROM field WHERE field_code = (SELECT field_code FROM crop ORDER BY CAST(SUBSTRING(field_code, 5) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    FieldEntity findLastRowNative();
}
