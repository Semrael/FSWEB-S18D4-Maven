package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface BurgerDao {
    Burger save(Burger burger);
    Optional<Burger> findById(Integer id);
    List<Burger> findAll();
    Set<Burger> findByPrice(Double price);
    List<Burger> findByBreadType(BreadType breadType);
    List<Burger> findByContent(String content);
    Burger update(Burger burger);
    Burger remove(Integer id);


}
