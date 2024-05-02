package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import java.util.Optional;


@Repository
public class BurgerDaoImpl implements BurgerDao{
    private EntityManager entityManager;
    @Autowired
    public BurgerDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Optional<Burger> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Burger.class,id));
    }

    @Override
    public List<Burger> findAll() {
        //Sql // jpql
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return query.getResultList();
    }

    @Override
    public Set<Burger> findByPrice(Double price) {
       TypedQuery<Burger> query=entityManager.createQuery("SELECT b FROM Burger b WHERE b.price>:price ORDER BY price DESC" ,Burger.class);
       query.setParameter("price",price);//price parametresini sorguya ekkledi
       List<Burger> resultList=query.getResultList();
       return new Set<>(resultList);
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query=entityManager.createQuery("Select b From Burger b Where b.breadType=:breadtype",Burger.class);
        query.setParameter("breadType",breadType);
        return query.getResultList();

    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> query=entityManager.createQuery("Select b From Burger b Where LOWER(b.content) LIKE(:content) ",Burger.class);
        query.setParameter("content","%" +content+"%");
        return query.getResultList();
    }



    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(Integer id) {
        Optional<Burger> burger=findById(id);
        if(burger.isPresent()){
            entityManager.remove(burger.get());
            return  burger.get();
        }
        //henüz hazır değil
        throw new BurgerException("Burger with given id is not exist: "+id, HttpStatus.NOT_FOUND);
    }
}
