package lk.ijse.gdse67.greenShadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse67.greenShadow.dao.LogDao;
import lk.ijse.gdse67.greenShadow.dto.impl.LogDTO;
import lk.ijse.gdse67.greenShadow.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public void saveLogs(LogDTO logDTO) {

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
