package com.pyrosandro.bds.dto;

import com.pyrosandro.bds.model.Device;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {

    private Long userIdentifier;
    private DeviceDTO device;
    private LocalDateTime creationDate;
}
