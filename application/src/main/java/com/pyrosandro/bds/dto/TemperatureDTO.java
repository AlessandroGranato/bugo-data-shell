package com.pyrosandro.bds.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TemperatureDTO {

    private Long id;
    private DeviceDTO device;
    private BigDecimal value;
    private LocalDateTime creationDate;
}
