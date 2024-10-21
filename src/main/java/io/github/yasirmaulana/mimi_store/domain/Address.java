package io.github.yasirmaulana.mimi_store.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    private String id;
    @Column(name = "contact_id", nullable = false)
    private String contactId;
    private String street;
    private String city;
    private String province;
    @Column(nullable = false)
    private String country;
    @Column(name = "postal_code")
    private String postalCode;

//    @ManyToOne
//    @JoinColumn(name = "contact_id", referencedColumnName = "id")
//    private Contact contact;
}
