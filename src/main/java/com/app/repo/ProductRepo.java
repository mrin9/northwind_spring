package com.app.repo;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.*;
import com.app.model.product.*;


public interface ProductRepo extends JpaRepository<Product, Integer> {
    public List<Product> findAll();
    public Page<Product> findAll(Pageable p);
    Product save(Product p);
    void delete(Product p) ;
    void delete(Integer id);
    //Product deleteById(Integer id);
    boolean exists( Integer id);
}

