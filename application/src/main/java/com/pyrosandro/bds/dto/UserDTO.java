package com.pyrosandro.bds.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    private String userIdentifier;
    private DeviceDTO device;
    private LocalDateTime creationDate;
}
