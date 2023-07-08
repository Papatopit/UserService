package org.userService.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NonNull
    @Column(name = "first_name")
    private String firstName;
    @NonNull
    @Column(name = "last_name")
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    @Column(name = "birthday")
    private String birthday;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NonNull
    private Set<Role> role;
    @NonNull
    @Column
    private String password;

}
