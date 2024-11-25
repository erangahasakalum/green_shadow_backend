package lk.ijse.gdse67.greenShadow.controller;

import lk.ijse.gdse67.greenShadow.dto.impl.EquipmentDTO;
import lk.ijse.gdse67.greenShadow.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(EquipmentDTO equipmentDTO){
        try {
            equipmentService.saveEquipment(equipmentDTO);
        }
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
