package com.pyrosandro.bds.dto;

import com.pyrosandro.bds.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeviceDTO {

    private Long id;
    private String deviceIdentifier;
    private LocalDateTime creationDate;
    private List<UserDTO> users;
}
