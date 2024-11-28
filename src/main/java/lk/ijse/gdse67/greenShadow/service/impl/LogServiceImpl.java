package lk.ijse.gdse67.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.LogDao;
import lk.ijse.gdse67.greenShadow.dto.impl.LogDTO;
import lk.ijse.gdse67.greenShadow.entity.impl.LogEntity;
import lk.ijse.gdse67.greenShadow.entity.impl.StaffEntity;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.LogService;
import lk.ijse.gdse67.greenShadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Autowired
    Mapping logMapping;

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
        System.out.println(logDTO);
        LogEntity save = logDao.save(logMapping.toLogEntity(logDTO));
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
        return List.of();
    }
}
