package lk.ijse.gdse67.greenShadow.service;

import lk.ijse.gdse67.greenShadow.dto.impl.LogDTO;

import java.util.List;

public interface LogService {
    void saveLogs(LogDTO logDTO);
    void updateLogs(LogDTO logDTO,String id);
    void deleteLogs(String id);
    List<LogDTO> getAllLogs();
}
