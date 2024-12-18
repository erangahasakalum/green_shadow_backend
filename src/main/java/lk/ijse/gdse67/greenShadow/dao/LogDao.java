package lk.ijse.gdse67.greenShadow.dao;
import lk.ijse.gdse67.greenShadow.entity.impl.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogDao extends JpaRepository<LogEntity, String> {
    @Query(value = "SELECT * FROM logs WHERE log_code = (SELECT log_code FROM logs ORDER BY CAST(SUBSTRING(log_code,5) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    LogEntity findLastRowNative();
}
