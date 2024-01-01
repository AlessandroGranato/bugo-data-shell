package com.pyrosandro.bds.resource;

import com.pyrosandro.bds.dto.UserDTO;
import com.pyrosandro.bds.dto.mapper.DtoMapperUser;
import com.pyrosandro.bds.exception.BdsErrorConstants;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

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
        if (userDTO.getId() != null) {
            throw new BdsException(BdsErrorConstants.USER_ID_PROVIDED_BY_CLIENT, null, HttpStatus.BAD_REQUEST);
        }
        UserDTO response = dtoMapperUser.toDto(userService.save(dtoMapperUser.toVo(userDTO)));
        return ResponseEntity
                .created(new URI("/api/users/" + response.getId()))
                .body(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws BdsException {

        log.debug("REST request to update user: {}, {}", id, userDTO);
        if (userDTO.getId() == null) {
            throw new BdsException(BdsErrorConstants.USER_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(id, userDTO.getId())) {
            throw new BdsException(BdsErrorConstants.USER_MISMATCHING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        UserDTO response = dtoMapperUser.toDto(userService.update(dtoMapperUser.toVo(userDTO)));
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws BdsException {
        log.debug("REST request to partial update user partially: {}, {}", id, userDTO);
        if (userDTO.getId() == null) {
            throw new BdsException(BdsErrorConstants.USER_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(id, userDTO.getId())) {
            throw new BdsException(BdsErrorConstants.USER_MISMATCHING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }

        UserDTO response = dtoMapperUser.toDto(userService.partialUpdate(dtoMapperUser.toVo(userDTO)));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        log.debug("REST request to get all Users");
        return dtoMapperUser.toDtoList(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws BdsException {
        log.debug("REST request to get User : {}", id);
        if (id == null) {
            throw new BdsException(BdsErrorConstants.USER_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        UserDTO response = dtoMapperUser.toDto(userService.findOne(id));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws BdsException {
        log.debug("REST request to delete User : {}", id);
        if (id == null) {
            throw new BdsException(BdsErrorConstants.USER_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        userService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
