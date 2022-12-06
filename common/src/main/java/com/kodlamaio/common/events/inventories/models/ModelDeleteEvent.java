package com.kodlamaio.common.events.inventories.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelDeleteEvent {
    private String modelId;
}
