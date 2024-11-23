package lk.ijse.gdse67.greenShadow.service.impl;

import lk.ijse.gdse67.greenShadow.dto.impl.FieldDTO;
import lk.ijse.gdse67.greenShadow.service.FieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class FieldServiceImpl implements FieldService {
    @Override
    public void saveField(FieldDTO fieldDTO) {

    }

    @Override
    public void updateField(FieldDTO fieldDTO, String id) {

    }

    @Override
    public void deleteField(String id) {

    }

    @Override
    public List<FieldDTO> getAllField() {
        return List.of();
    }
}
