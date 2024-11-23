package lk.ijse.gdse67.greenShadow.controller;

import lk.ijse.gdse67.greenShadow.dto.impl.FieldDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vi/field")
public class FieldController {
    @PostMapping
    public ResponseEntity<Void> saveField() {
        return null;
    }

    @GetMapping
    public List<FieldDTO> getAllField() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteField() {
        return null;
    }

    @PatchMapping
    public ResponseEntity<Void> updateField() {
        return null;
    }

}
