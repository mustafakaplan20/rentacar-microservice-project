package com.kodlamaio.inventoryservice.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandRequest {
    @NotBlank
    @NotNull
    @Size(min = 2,max = 20)
    private String name;
}
