package lk.ijse.gdse67.greenShadow.utill;

import lk.ijse.gdse67.greenShadow.dto.impl.*;
import lk.ijse.gdse67.greenShadow.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    //for user mapping
    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> toUserDtoList(List<UserEntity> list) {
        return modelMapper.map(list, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    //for vehicle mapping
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }

    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

    public List<VehicleDTO> toVehicleDtoList(List<VehicleEntity> list) {
        return modelMapper.map(list, new TypeToken<List<VehicleDTO>>() {
        }.getType());
    }

    //for crop mapping
    public CropEntity toCropEntity(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, CropEntity.class);
    }

    public CropDTO toCropDto(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDTO.class);
    }

    public List<CropDTO> toCropDtoList(List<CropEntity> list) {
        return modelMapper.map(list, new TypeToken<List<CropDTO>>() {
        }.getType());
    }

    //for staff mapping
    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public List<StaffDTO> toStaffDtoList(List<StaffEntity> list) {
        return modelMapper.map(list, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    //for log mapping
    public LogDTO toLogDto(LogEntity logEntity) {
        return modelMapper.map(logEntity, LogDTO.class);
    }

    public LogEntity toLogEntity(LogDTO logDTO) {
        return modelMapper.map(logDTO, LogEntity.class);
    }

    public List<LogDTO> toLogDtoList(List<LogEntity> list) {
        return modelMapper.map(list, new TypeToken<List<LogDTO>>() {
        }.getType());
    }

    //for field mapping
    public FieldDTO toFieldDto(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }

    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }


    public List<FieldDTO> toFieldDtoList(List<FieldEntity> list) {
        return modelMapper.map(list, new TypeToken<List<FieldDTO>>() {
        }.getType());
    }

    //for equipment mapping
    public FieldDTO toEquipmentDto(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, FieldDTO.class);
    }

    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
    }


    public List<EquipmentDTO> toEquipmenttoList(List<EquipmentEntity> list) {
        return modelMapper.map(list, new TypeToken<List<EquipmentDTO>>() {
        }.getType());
    }
}
