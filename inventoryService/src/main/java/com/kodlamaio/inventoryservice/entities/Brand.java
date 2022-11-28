package com.kodlamaio.inventoryservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="brands")
public class Brand {
    //GUID integer yerine string!!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "brand")
    private List<Model> models;
}
