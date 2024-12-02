package lk.ijse.gdse67.greenShadow.controller;
import lk.ijse.gdse67.greenShadow.dto.impl.LogDTO;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.service.LogService;
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
@RequestMapping("api/v1/logs")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveLogs(
            @RequestPart("date") String date,
            @RequestPart("logDetails") String logDetails,
            @RequestPart("observedImage") MultipartFile observedImage,
            @RequestPart(value = "staffList",required = false) String staffList,
            @RequestPart(value = "cropList",required = false) String cropList,
            @RequestPart(value = "fieldList",required = false) String fieldList

    )  {
        try {
            String base64LogImage ="";
            byte[] bytesLogImage = observedImage.getBytes();
            base64LogImage = AppUtil.imageConvert(bytesLogImage);

            List<String> staffCodes = new ArrayList<>();
            List<String> cropCodes = new ArrayList<>();
            List<String> fieldCode = new ArrayList<>();

            if (staffList != null){
                staffCodes = SplitString.spiltLists(staffList);
            }

            if (cropList != null){
                cropCodes = SplitString.spiltLists(cropList);
            }
            if (fieldList != null){
                fieldCode = SplitString.spiltLists(fieldList);
            }
            LogDTO logDTO = new LogDTO();
            logDTO.setDate(date);
            logDTO.setLogDetails(logDetails);
            logDTO.setObservedImage(base64LogImage);
            logDTO.setStaffList(staffCodes);
            logDTO.setCropList(cropCodes);
            logDTO.setFieldList(fieldCode);
            logService.saveLogs(logDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> getAllLogs(){
        return logService.getAllLogs();
    }


}
