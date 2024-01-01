package com.pyrosandro.bds.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVO {

    private Long id;
    private String deviceIdentifier;
    private LocalDateTime creationDate;
}
