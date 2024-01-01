package com.pyrosandro.bds.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bds_users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bds_user_seq_generator")
    @SequenceGenerator(name = "bds_user_seq_generator", sequenceName = "bds_users_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_identifier")
    private String userIdentifier;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userIdentifier, user.userIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIdentifier);
    }
}
