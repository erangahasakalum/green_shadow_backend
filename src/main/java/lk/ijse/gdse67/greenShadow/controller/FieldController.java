package lk.ijse.gdse67.greenShadow.controller;
import lk.ijse.gdse67.greenShadow.dto.impl.FieldDTO;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.exeption.NotFoundException;
import lk.ijse.gdse67.greenShadow.service.FieldService;
import lk.ijse.gdse67.greenShadow.utill.AppUtil;
import lk.ijse.gdse67.greenShadow.utill.SplitString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/vi/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("name") String fieldName,
            @RequestPart("location") String location,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2,
            @RequestPart(value = "staffList",required = false) String staffList,
            @RequestPart(value = "cropList",required = false) String cropList
    ) {

        List<String> staff_codes = new ArrayList<>();
        List<String> crop_codes = new ArrayList<>();
        if (staffList != null) {
            staff_codes = SplitString.spiltLists(staffList);
        }
        if (cropList != null) {
            crop_codes = SplitString.spiltLists(cropList);
        }

        String base64FieldImage1 = "";
        String base64FieldImage2 = "";
        try {
            byte[] fieldImage1Bytes = fieldImage1.getBytes();
            base64FieldImage1 = AppUtil.imageConvert(fieldImage1Bytes);

            byte[] fieldImage2Bytes1 = fieldImage2.getBytes();
            base64FieldImage2 = AppUtil.imageConvert(fieldImage2Bytes1);

            var fieldDTO = new FieldDTO();
            fieldDTO.setName(fieldName);
            fieldDTO.setLocation(location);
            fieldDTO.setExtentSize(Double.parseDouble(extentSize));
            fieldDTO.setFieldImage1(base64FieldImage1);
            fieldDTO.setFieldImage2(base64FieldImage2);
            fieldDTO.setMemberCodeList(staff_codes);
            fieldDTO.setCropCodeList(crop_codes);
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllField() {
        return fieldService.getAllField();
    }

    @PatchMapping(value = "/{fieldCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateField(
            @PathVariable("fieldCode") String fieldCode,
            @RequestPart("name") String fieldName,
            @RequestPart("location") String location,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2,
            @RequestPart("staffList") List<String> staffList,
            @RequestPart("cropList") List<String> cropList
    ) {
        String base64FieldImage1 = "";
        String base64FieldImage2 = "";
        try {
            byte[] fieldImage1Bytes = fieldImage1.getBytes();
            base64FieldImage1 = AppUtil.imageConvert(fieldImage1Bytes);

            byte[] fieldImage2Bytes1 = fieldImage2.getBytes();
            base64FieldImage2 = AppUtil.imageConvert(fieldImage2Bytes1);

            var fieldDTO = new FieldDTO();
            fieldDTO.setName(fieldName);
            fieldDTO.setLocation(location);
            fieldDTO.setExtentSize(Double.parseDouble(extentSize));
            fieldDTO.setFieldImage1(base64FieldImage1);
            fieldDTO.setFieldImage1(base64FieldImage2);
            fieldDTO.setMemberCodeList(staffList);
            fieldDTO.setCropCodeList(cropList);
            fieldService.updateField(fieldDTO, fieldCode);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
