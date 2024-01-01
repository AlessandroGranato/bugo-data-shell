package com.pyrosandro.bds.vo;

import com.pyrosandro.bds.model.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private Long id;
    private String userIdentifier;
    private DeviceVO device;
    private LocalDateTime creationDate;

}
