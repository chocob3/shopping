package com.example.shopping_Spring.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.shopping_Spring.entity.Products;
@Repository
public interface ProductsRepository extends CrudRepository<Products,Integer>{
	@Modifying
	@Query(value="UPDATE products SET quantity= :quantity WHERE productsid= :productId")
	void changeQuantity(@Param("quantity") Integer quantity,@Param("productId")Integer productsId);
}
