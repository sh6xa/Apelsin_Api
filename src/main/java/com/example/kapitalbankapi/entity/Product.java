package com.example.kapitalbankapi.entity;

import com.example.kapitalbankapi.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product extends AbsEntity {
    private String name;
    private String description;
    private double price;

    @ManyToOne
    private Category category;
    @OneToOne
    private Attachment photo;

    public Product( String name, String description, double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
