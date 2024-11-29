package lk.ijse.gdse67.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.*;
import lk.ijse.gdse67.greenShadow.dto.impl.StaffDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.*;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.StaffService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping staffMapping;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private LogDao logDao;

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

        StaffEntity staffEntity = staffMapping.toStaffEntity(staffDTO);

        List<VehicleEntity> vehicleEntities = new ArrayList<>();
        for (String vehicle_id:staffDTO.getVehicleList()){
            if(vehicleDao.existsById(vehicle_id)){
                vehicleEntities.add(vehicleDao.getById(vehicle_id));
            }
        }

        List<FieldEntity> fieldEntities = new ArrayList<>();
        for (String field_id :staffDTO.getFieldCodeList()){
            if(fieldDao.existsById(field_id)){
                fieldEntities.add(fieldDao.getById(field_id));
            }
        }

        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
        for (String equipment_id :staffDTO.getEquipmentList()){
            if(equipmentDao.existsById(equipment_id)){
                equipmentEntities.add(equipmentDao.getById(equipment_id));
            }
        }


        staffEntity.setEquipmentList(equipmentEntities);
        staffEntity.setFieldList(fieldEntities);
        staffEntity.setVehicleList(vehicleEntities);
        StaffEntity save = staffDao.save(staffEntity);
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
