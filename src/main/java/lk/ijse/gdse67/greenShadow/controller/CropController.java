package lk.ijse.gdse67.greenShadow.controller;

import lk.ijse.gdse67.greenShadow.dto.impl.CropDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {
    @PostMapping
    public ResponseEntity<Void> saveCrop(){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCrop(){
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<CropDTO> getAllCrop(){
        return null;
    }

    @GetMapping
    public ResponseEntity<CropDTO> getCropById(@RequestParam int id){
        return null;
    }

    @PatchMapping
    public ResponseEntity<Void> updateCrop(@RequestBody CropDTO cropDTO){
        return null;
    }
}
