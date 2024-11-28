package lk.ijse.gdse67.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.StaffDao;
import lk.ijse.gdse67.greenShadow.dto.impl.StaffDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.StaffEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.VehicleEntity;
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
        StaffEntity lastRowNative = staffDao.findLastRowNative();
        if (lastRowNative != null){
            try {
                String[] parts = lastRowNative.getMemberCode().split("-");
                id = Integer.parseInt(parts[1]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        staffDTO.setMemberCode("MEMBER-"+ ++id);
        StaffEntity save = staffDao.save(staffMapping.toStaffEntity(staffDTO));
        if (save == null) {
            throw new DataPersistException("vehicle not saved");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return staffMapping.toStaffDtoList(staffDao.findAll());
    }

    @Override
    public void updateStaff(StaffDTO staff, String id) {

    }

    @Override
    public void deleteStaff(String id) {

    }
}
