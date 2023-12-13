package com.pyrosandro.bds.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "bds_devices")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String deviceIdentifier;

    @NonNull
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<User> users;
}
