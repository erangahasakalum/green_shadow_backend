package lk.ijse.gdse67.greenShadow.dao;

import lk.ijse.gdse67.greenShadow.entity.impl.FieldEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffDao extends JpaRepository<StaffEntity,String> {
    @Query(value = "SELECT * FROM field WHERE staff_code = (SELECT staff_code FROM crop ORDER BY CAST(SUBSTRING(staff_code, 5) AS UNSIGNED) DESC LIMIT 1);"
            , nativeQuery = true)
    StaffEntity findLastRowNative();
}
