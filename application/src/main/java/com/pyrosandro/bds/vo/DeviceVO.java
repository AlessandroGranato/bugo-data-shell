package com.pyrosandro.bds.vo;

import com.pyrosandro.bds.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVO {

    private Long id;
    private String deviceIdentifier;
    private LocalDateTime creationDate;
    private List<UserVO> users;
}
