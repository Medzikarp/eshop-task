package com.example.eshop.product.service.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for Watch entity with basic validation.
 */
@Data
public class WatchDto {

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

    private byte[] fountain;
}
