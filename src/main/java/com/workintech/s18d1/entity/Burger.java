package com.workintech.s18d1.entity;


import jakarta.persistence.*;
import lombok.*;

//POJO

@Data
@NoArgsConstructor
@Entity
@Table(name = "burger", schema = "fsweb")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "is_vegan")
    private boolean isVegan;


    @Column(name = "bread_type")
    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    @Column(name = "contents")
    private String contents;


    public boolean isVegan() {
        return this.isVegan;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Burger;
    }

    public void isVegan(boolean isVegan) {
        this.isVegan = isVegan;
    }

}
