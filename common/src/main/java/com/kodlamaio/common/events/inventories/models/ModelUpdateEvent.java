package com.kodlamaio.common.events.inventories.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelUpdateEvent {
    private String id;
    private String name;
    private String brandId;
}
