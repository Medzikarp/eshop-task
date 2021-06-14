package com.example.eshop.product.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 256)
    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @PositiveOrZero
    private Integer price;

    @Size(max = 256)
    private String description;

    @Lob
    private byte[] fountain;
}
