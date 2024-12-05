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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        if (lastRowNative != null) {
            try {
                String[] parts = lastRowNative.getMemberCode().split("-");
                id = Integer.parseInt(parts[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        staffDTO.setMemberCode("MEMBER-" + ++id);

        StaffEntity staffEntity = staffMapping.toStaffEntity(staffDTO);

        List<VehicleEntity> vehicleEntities = new ArrayList<>();
        for (String vehicle_id : staffDTO.getVehicleList()) {
            if (vehicleDao.existsById(vehicle_id)) {
                vehicleEntities.add(vehicleDao.getById(vehicle_id));
            }
        }

        List<FieldEntity> fieldEntities = new ArrayList<>();
        for (String field_id : staffDTO.getFieldCodeList()) {
            if (fieldDao.existsById(field_id)) {
                fieldEntities.add(fieldDao.getById(field_id));
            }
        }

        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
        for (String equipment_id : staffDTO.getEquipmentList()) {
            if (equipmentDao.existsById(equipment_id)) {
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
        List<StaffDTO> staffDTOS = new ArrayList<>();

        for (StaffEntity staffEntity : staffDao.findAll()) {
            List<String> vehicleList = new ArrayList<>();
            List<String> fieldList = new ArrayList<>();
            List<String> equipmentList = new ArrayList<>();
            List<String> logList = new ArrayList<>();

            for (VehicleEntity vehicleEntity : vehicleDao.findAll()) {
                vehicleList.add(vehicleEntity.getVehicleCode());
            }
            for (FieldEntity fieldEntity : fieldDao.findAll()) {
                fieldList.add(fieldEntity.getFieldCode());
            }
            for (EquipmentEntity equipmentEntity : equipmentDao.findAll()) {
                equipmentList.add(equipmentEntity.getEquipmentCode());
            }
            for (LogEntity logEntity : logDao.findAll()) {
                logList.add(logEntity.getLogCode());
            }


            staffDTOS.add(new StaffDTO(staffEntity.getMemberCode(),
                    staffEntity.getFirstName(),
                    staffEntity.getLastName(),
                    staffEntity.getJoinedDate(),
                    staffEntity.getDateOfBirth(),
                    staffEntity.getGender(),
                    staffEntity.getDesignation(),
                    staffEntity.getAddressLine1(),
                    staffEntity.getAddressLine2(),
                    staffEntity.getAddressLine3(),
                    staffEntity.getAddressLine4(),
                    staffEntity.getAddressLine5(),
                    staffEntity.getContactNo(),
                    staffEntity.getEmail(),
                    staffEntity.getRole(),
                    vehicleList,
                    fieldList,
                    equipmentList,
                    logList));
        }
        return staffDTOS;
    }

    @Override
    public void updateStaff(StaffDTO staff, String id) {

    }

    @Override
    public void deleteStaff(String id) {
        if (staffDao.existsById(id)){
            StaffEntity referenceById = staffDao.getReferenceById(id);
            List<VehicleEntity> vehicleList = referenceById.getVehicleList();
            List<FieldEntity> fieldList = referenceById.getFieldList();
            List<LogEntity> logList = referenceById.getLogList();
            List<EquipmentEntity> equipmentList = referenceById.getEquipmentList();
//
//            for (VehicleEntity vehicleEntity : vehicleList) {
//                StaffEntity staff = vehicleEntity.getStaff();
//
//            }

            for (FieldEntity fieldEntity : fieldList) {
                List<StaffEntity> staffList = fieldEntity.getStaffList();
                staffList.remove(referenceById);
            }

            for (LogEntity logEntity : logList) {
                List<StaffEntity> staffList = logEntity.getStaffList();
                staffList.remove(referenceById);
            }

            for (EquipmentEntity equipmentEntity : equipmentList) {
                List<StaffEntity> staffCodeList = equipmentEntity.getStaffCodeList();
                staffCodeList.remove(referenceById);
            }

            referenceById.getVehicleList().clear();
            referenceById.getFieldList().clear();
            referenceById.getLogList().clear();
            referenceById.getEquipmentList().clear();

            staffDao.delete(referenceById);
        }else {
            throw new DataPersistException("Cant find data to delete");
        }

    }
}
