package com.pyrosandro.bds.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bds_temperatures")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @OneToOne
    @JoinColumn(name = "device_id")
    @NonNull
    private Device device;

    @NonNull
    private BigDecimal value;

    @NonNull
    private LocalDateTime creationDate;

}