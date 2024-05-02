package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/workintech/burgers")
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
    public Burger findId(@PathVariable("id") Integer id){
        Optional<Burger> burger=burgerDao.findById();
        if(burger.isPresent()){
            return burger.get();
        }
        //Yazılacak
        throw new BurgerException("Burger with given id is not exist:"+id, HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public Burger save(@RequestBody Burger burger){
       return burgerDao.save(burger);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Burger> update(@PathVariable("id") Integer id, @RequestBody Burger burger){
        burger.setId(id);
        Burger updateBurger=burgerDao.update(burger);
        return ResponseEntity.ofNullable(updateBurger);

    }
    @DeleteMapping("/{id}")
    public Burger delete( @PathVariable Integer id){
        return burgerDao.remove(id);
    }

    @GetMapping("/findByPrice")
    public Set<Burger> findByPrice(@RequestParam Double price ){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType")
    public List<Burger> findByBreadType(@RequestParam BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }
    @GetMapping("/findByContent")
    public List<Burger> findByContent(@RequestParam String content){
        return burgerDao.findByContent(content);
    }


}
