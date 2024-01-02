package com.distributed.systems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private Integer AFM;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name="user_roles",
            joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "AFM")},
            inverseJoinColumns = { @JoinColumn(name = "role_id",referencedColumnName = "id")}

    )
    private List<Role> roles = new ArrayList<>();

}
