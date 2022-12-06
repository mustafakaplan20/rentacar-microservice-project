package com.kodlamaio.common.events.inventories.brands;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDeleteEvent {
    private String brandId;
}
