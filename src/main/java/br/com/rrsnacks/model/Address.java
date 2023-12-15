package br.com.rrsnacks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    @ManyToOne
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    private Customer customer;
    private String street;
    private String number;
    private String district;
    private String complement;
    private String city;
}
