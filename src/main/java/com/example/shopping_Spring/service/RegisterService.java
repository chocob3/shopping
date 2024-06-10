package com.example.shopping_Spring.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.form.NewRegisterForm;

@Service
public interface RegisterService {

	Iterable<Register> findAll();
	Optional<Register> selectOneById(String id);
	Register registByField(String id,String password,String mail);
	Optional<Register> findRegisterField(String id,String password,String mail);
	Optional<Register> findRegister(String id,String password);
	Register createRegister(NewRegisterForm registerForm);
	Register registByRegist(Register register);
	

}
