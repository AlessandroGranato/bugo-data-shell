package com.pyrosandro.bds.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bds_temperatures")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bds_temperatures_seq_generator")
    @SequenceGenerator(name = "bds_temperatures_seq_generator", sequenceName = "bds_temperatures_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(device, that.device) && Objects.equals(value, that.value) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, value, creationDate);
    }
}