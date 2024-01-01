package com.pyrosandro.bds.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureVO {

    private Long id;
    private DeviceVO device;
    private BigDecimal value;
    private LocalDateTime creationDate;

}