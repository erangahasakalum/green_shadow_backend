package lk.ijse.gdse67.greenShadow.service;
import lk.ijse.gdse67.greenShadow.dto.impl.StaffDTO;
import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staff);
    List<StaffDTO> getAllStaff();
    void updateStaff(StaffDTO staff,String id);
    void deleteStaff(String id);
}
