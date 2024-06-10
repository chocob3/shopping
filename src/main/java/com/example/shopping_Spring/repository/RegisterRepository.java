package com.example.shopping_Spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_Spring.entity.Register;

@Repository
public interface RegisterRepository extends CrudRepository<Register,String> {
	 Optional<Register> findByMail(String mail);

}
