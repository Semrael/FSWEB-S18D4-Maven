package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;

import com.workintech.s18d1.exceptions.BurgerErrorResponse;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
//@RequestMapping("/workintech/burgers")
@RequestMapping("/burger")
public class BurgerController {
    private BurgerDao burgerDao;
    @Autowired
    public BurgerController(BurgerDao burgerDao){
        this.burgerDao=burgerDao;
    }
    @GetMapping
    public List<Burger>  findAll(){
        return burgerDao.findAll();
    }
    @GetMapping("/{id}")
    public Burger findId(@PathVariable("id") Long id){
        return burgerDao.findById(id);
    }
    @PostMapping
    public Burger save(@RequestBody Burger burger){
        BurgerValidation.checkName(burger.getName());
       return burgerDao.save(burger);

    }

    @PutMapping
    public Burger update(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }
    @DeleteMapping("/{id}")
    public Burger delete( @PathVariable Long id){
        return burgerDao.remove(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable("price") Integer price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable("breadType") String  breadType){
       BreadType bt=BreadType.valueOf(breadType);
       return burgerDao.findByBreadType(bt);
    }
    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable("content") String content){
        return burgerDao.findByContent(content);
    }


}
