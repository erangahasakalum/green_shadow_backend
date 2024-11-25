package lk.ijse.gdse67.greenShadow.controller;
import lk.ijse.gdse67.greenShadow.dto.impl.CropDTO;
import lk.ijse.gdse67.greenShadow.exeption.DataPersistException;
import lk.ijse.gdse67.greenShadow.exeption.NotFoundException;
import lk.ijse.gdse67.greenShadow.service.CropService;
import lk.ijse.gdse67.greenShadow.utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("cropName") String cropName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("image") MultipartFile cropImage,
            @RequestPart("category") String category,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("fieldList") List<String> fieldList,
            @RequestPart("logList") List<String> logList
    ) {

        System.out.println(cropName + scientificName + cropSeason + category);
        String base64CropPic = "";
        try {
            byte[] bytesCropImage = cropImage.getBytes();
            base64CropPic = AppUtil.cropImage(bytesCropImage);
            var cropDTO = new CropDTO();
            cropDTO.setCropName(cropName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(cropSeason);
            cropDTO.setCropImage(base64CropPic);
            cropDTO.setFieldCodeList(fieldList);
            cropDTO.setLogCodeList(logList);
            cropService.saveCrops(cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropCode")String cropCode) {
        try {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<CropDTO> getAllCrop() {
        return null;
    }

    @PatchMapping
    public ResponseEntity<Void> updateCrop() {
        return null;
    }
}
