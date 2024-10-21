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
@Table(name = "users")
public class User {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

//    @OneToMany(mappedBy = "user")
//    private List<Contact> contacts;
}
