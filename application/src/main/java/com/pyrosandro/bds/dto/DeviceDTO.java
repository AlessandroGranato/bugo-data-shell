package com.pyrosandro.bds.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceDTO {

    private Long id;
    private String deviceIdentifier;
    private LocalDateTime creationDate;
}
