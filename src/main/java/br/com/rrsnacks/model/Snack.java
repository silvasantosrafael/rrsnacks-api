package br.com.rrsnacks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "snack")
public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_id")
    private Long snackId;
    private String name;
    private String description;
    private BigDecimal price;
}