package io.github.yasirmaulana.mimi_store.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;

    private String email;

//    @ManyToOne
//    @JoinColumn(name = "username", referencedColumnName = "username")
//    private User user;

//    @OneToMany(mappedBy = "contact")
//    private List<Address> addresses;
}
