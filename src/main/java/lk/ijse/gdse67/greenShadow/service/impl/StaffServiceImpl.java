package lk.ijse.gdse67.greenShadow.service.impl;
import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.StaffDao;
import lk.ijse.gdse67.greenShadow.dto.impl.StaffDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.EquipmentEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.StaffEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.StaffService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping staffMapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        int id = 0;
        StaffEntity lastId = staffDao.findLastRowNative();
        if (lastId != null) {
            try {
                String[] split = lastId.getMemberCode().split("-");
                id = Integer.parseInt(split[0]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid member code");
            }
        }
        staffDTO.setMemberCode("MEMBER -" + ++id);
        StaffEntity save = staffDao.save(staffMapping.toStaffEntity(staffDTO));
        if (save == null) {
            throw new DataPersistException("vehicle not saved");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return List.of();
    }

    @Override
    public void updateStaff(StaffDTO staff, String id) {

    }

    @Override
    public void deleteStaff(String id) {

    }
}
