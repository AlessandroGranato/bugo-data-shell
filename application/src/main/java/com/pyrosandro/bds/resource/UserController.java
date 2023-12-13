package com.pyrosandro.bds.resource;

import com.pyrosandro.bds.dto.DeviceDTO;
import com.pyrosandro.bds.dto.UserDTO;
import com.pyrosandro.bds.dto.mapper.DtoMapperUser;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final DtoMapperUser dtoMapperUser;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws BdsException, URISyntaxException {
        log.debug("REST request to save user : {}", userDTO);
        UserDTO response = dtoMapperUser.toDto(userService.save(dtoMapperUser.toVo(userDTO)));
        return ResponseEntity
                .created(new URI("/api/users/" + response.getUserIdentifier()))
                .body(response);
    }

//    @PutMapping("/devices/{id}")
//    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) throws BdsException {
//
//        log.debug("REST request to update device : {}, {}", id, deviceDTO);
//        if (deviceDTO.getId() == null) {
//            throw new BdsException(BdsErrorConstants.DEVICE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
//        }
//        if (!Objects.equals(id, deviceDTO.getId())) {
//            throw new BdsException(BdsErrorConstants.DEVICE_MISMATCHING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
//        }
//
//        DeviceDTO response = dtoMapperDevice.toDto(deviceService.update(dtoMapperDevice.toVo(deviceDTO)));
//        return ResponseEntity.ok().body(response);
//    }
//
//    @PatchMapping("/devices/{id}")
//    public ResponseEntity<DeviceDTO> partialUpdateDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) throws BdsException {
//        log.debug("REST request to partial update device partially : {}, {}", id, deviceDTO);
//        if (deviceDTO.getId() == null) {
//            throw new BdsException(BdsErrorConstants.DEVICE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
//        }
//        if (!Objects.equals(id, deviceDTO.getId())) {
//            throw new BdsException(BdsErrorConstants.DEVICE_MISMATCHING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
//        }
//
//        DeviceDTO response = dtoMapperDevice.toDto(deviceService.partialUpdate(dtoMapperDevice.toVo(deviceDTO)));
//        return ResponseEntity.ok().body(response);
//    }
//
//    @GetMapping("/devices")
//    public List<DeviceDTO> getAllDevices() {
//        log.debug("REST request to get all Devices");
//        return dtoMapperDevice.toDtoList(deviceService.findAll());
//    }
//
//    @GetMapping("/devices/{id}")
//    public ResponseEntity<DeviceDTO> getDevice(@PathVariable Long id) throws BdsException {
//        log.debug("REST request to get Device : {}", id);
//        if (id == null) {
//            throw new BdsException(BdsErrorConstants.DEVICE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
//        }
//        DeviceDTO response = dtoMapperDevice.toDto(deviceService.findOne(id));
//        return ResponseEntity.ok().body(response);
//    }
//
//    @DeleteMapping("/devices/{id}")
//    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) throws BdsException {
//        log.debug("REST request to delete Device : {}", id);
//        if (id == null) {
//            throw new BdsException(BdsErrorConstants.DEVICE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
//        }
//        deviceService.delete(id);
//        return ResponseEntity
//                .noContent()
//                .build();
//    }
}
