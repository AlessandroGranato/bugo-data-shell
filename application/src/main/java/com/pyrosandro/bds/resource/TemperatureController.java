package com.pyrosandro.bds.resource;

import com.pyrosandro.bds.dto.TemperatureDTO;
import com.pyrosandro.bds.dto.mapper.DtoMapperTemperature;
import com.pyrosandro.bds.exception.BdsErrorConstants;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.service.TemperatureService;
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
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final DtoMapperTemperature dtoMapperTemperature;

    @PostMapping("/temperatures")
    public ResponseEntity<TemperatureDTO> createTemperature(@RequestBody TemperatureDTO temperatureDTO) throws BdsException, URISyntaxException {
        log.debug("REST request to save temperature : {}", temperatureDTO);
        if (temperatureDTO.getId() != null) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_ID_PROVIDED_BY_CLIENT, null, HttpStatus.BAD_REQUEST);
        }
        TemperatureDTO response = dtoMapperTemperature.toDto(temperatureService.save(dtoMapperTemperature.toVo(temperatureDTO)));
        return ResponseEntity
                .created(new URI("/api/temperatures/" + response.getId()))
                .body(response);
    }

    @PutMapping("/temperatures/{id}")
    public ResponseEntity<TemperatureDTO> updateTemperature(@PathVariable Long id, @RequestBody TemperatureDTO temperatureDTO) throws BdsException {

        log.debug("REST request to update temperature : {}, {}", id, temperatureDTO);
        if (temperatureDTO.getId() == null) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(id, temperatureDTO.getId())) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_MISMATCHING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        TemperatureDTO response = dtoMapperTemperature.toDto(temperatureService.update(dtoMapperTemperature.toVo(temperatureDTO)));
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/temperatures/{id}")
    public ResponseEntity<TemperatureDTO> partialUpdateTemperature(@PathVariable Long id, @RequestBody TemperatureDTO temperatureDTO) throws BdsException {
        log.debug("REST request to partial update temperature partially: {}, {}", id, temperatureDTO);
        if (temperatureDTO.getId() == null) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(id, temperatureDTO.getId())) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_MISMATCHING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }

        TemperatureDTO response = dtoMapperTemperature.toDto(temperatureService.partialUpdate(dtoMapperTemperature.toVo(temperatureDTO)));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/temperatures")
    public List<TemperatureDTO> getAllTemperatures() {
        log.debug("REST request to get all Temperatures");
        return dtoMapperTemperature.toDtoList(temperatureService.findAll());
    }

    @GetMapping("/temperatures/{id}")
    public ResponseEntity<TemperatureDTO> getTemperature(@PathVariable Long id) throws BdsException {
        log.debug("REST request to get Temperature : {}", id);
        if (id == null) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        TemperatureDTO response = dtoMapperTemperature.toDto(temperatureService.findOne(id));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/temperatures/{id}")
    public ResponseEntity<Void> deleteTemperature(@PathVariable Long id) throws BdsException {
        log.debug("REST request to delete Temperature : {}", id);
        if (id == null) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_MISSING_INPUT_ID, null, HttpStatus.BAD_REQUEST);
        }
        temperatureService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
