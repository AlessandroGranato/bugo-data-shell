package com.pyrosandro.bds.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "bds_devices")
@Getter
@Setter
@ToString //Remember. If you add oneToMany relationship, pay attention to the ToString (search online "Lombok @ToString oneToMany relationship")
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bds_device_seq_generator")
    @SequenceGenerator(name = "bds_device_seq_generator", sequenceName = "bds_devices_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "device_identifier")
    private String deviceIdentifier;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(deviceIdentifier, device.deviceIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceIdentifier);
    }
}
