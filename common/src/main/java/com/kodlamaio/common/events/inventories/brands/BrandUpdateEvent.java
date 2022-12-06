package com.kodlamaio.common.events.inventories.brands;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandUpdateEvent {
    private String id;
    private String name;
}
