package lk.ijse.gdse67.greenShadow.controller;

import lk.ijse.gdse67.greenShadow.dto.impl.EquipmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {
    @PostMapping
    public ResponseEntity<Void> saveEquipment(){
        return null;
    }

    @GetMapping
    public List<EquipmentDTO> getAllEquipment(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEquipment(){
        return null;
    }

    @PatchMapping
    public ResponseEntity<Void> updateEquipment(){
        return null;
    }
}
