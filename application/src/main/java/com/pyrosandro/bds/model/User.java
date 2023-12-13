package com.pyrosandro.bds.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bds_users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    private Long userIdentifier;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @NonNull
    private LocalDateTime creationDate;


}
