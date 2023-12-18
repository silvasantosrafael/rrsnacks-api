package br.com.rrsnacks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "snacks")
public class Snack {
    @Id
    @Column(name = "snack_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snackId;
    private String name;
    private String description;
    private BigDecimal price;
}