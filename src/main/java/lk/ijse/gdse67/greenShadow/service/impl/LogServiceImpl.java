package lk.ijse.gdse67.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.CropDao;
import lk.ijse.gdse67.greenShadow.dao.FieldDao;
import lk.ijse.gdse67.greenShadow.dao.LogDao;
import lk.ijse.gdse67.greenShadow.dao.StaffDao;
import lk.ijse.gdse67.greenShadow.dto.impl.LogDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.CropEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.FieldEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.LogEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.StaffEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.LogService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Autowired
    private Mapping logMapping;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private CropDao cropDao;

    @Autowired
    private FieldDao fieldDao;



    @Override
    public void saveLogs(LogDTO logDTO) {
        int id = 0;
        LogEntity lastRowNative = logDao.findLastRowNative();
        if (lastRowNative != null){
            try {
                String[] parts = lastRowNative.getLogCode().split("-");
                id = Integer.parseInt(parts[1]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        logDTO.setLogCode("LOG-"+ ++id);
        LogEntity logEntity = logMapping.toLogEntity(logDTO);

        List<StaffEntity> staffEntities = new ArrayList<>();
        for (String staff_id : logDTO.getStaffList()){
            if (staffDao.existsById(staff_id)){
                staffEntities.add(staffDao.getReferenceById(staff_id));
            }
        }

        List<CropEntity> cropEntities = new ArrayList<>();
        for (String crop_id : logDTO.getCropList()){
            if (cropDao.existsById(crop_id)){
                cropEntities.add(cropDao.getReferenceById(crop_id));
            }
        }
        List<FieldEntity> fieldEntities = new ArrayList<>();
        for (String field_id : logDTO.getFieldList()){
            if (fieldDao.existsById(field_id)){
                fieldEntities.add(fieldDao.getReferenceById(field_id));
            }
        }

        logEntity.setStaffList(staffEntities);
        logEntity.setCropList(cropEntities);
        logEntity.setFieldList(fieldEntities);

        LogEntity save = logDao.save(logEntity);
        if (save == null){
            throw new DataPersistException("log not saved");
        }
    }

    @Override
    public void updateLogs(LogDTO logDTO, String id) {

    }

    @Override
    public void deleteLogs(String id) {

    }

    @Override
    public List<LogDTO> getAllLogs() {
        return logMapping.toLogDtoList(logDao.findAll());
    }
}
