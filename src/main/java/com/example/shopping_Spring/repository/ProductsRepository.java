package com.example.shopping_Spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_Spring.entity.Products;
@Repository
public interface ProductsRepository extends CrudRepository<Products,Integer>{

}
