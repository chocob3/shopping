package com.example.shopping_Spring.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.form.NewRegisterForm;
import com.example.shopping_Spring.repository.RegisterRepository;

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	RegisterRepository repository;
	@Override
	public Iterable<Register> findAll(){
		return repository.findAll();
	}
	@Override
	public Optional<Register> selectOneById(String id){
		return repository.findById(id);
	}
	@Override
	public Register registByField(String id,String password,String mail){
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		Register r=new Register(id, password,mail,timestamp,"タカシ");
		return repository.save(r);
	}
	@Override
	//idかpasswordで既存のものが存在するか確認する
	public Optional<Register> findRegisterField(String id,String password,String mail){
		Optional<Register> usedRegister=repository.findById(id);
		if(usedRegister.isEmpty()) {
			usedRegister=repository.findByMail(mail);
			return usedRegister;
		}
		 return usedRegister;
	}
	
	public Optional<Register> findRegister(String id,String password){
		Optional<Register> usedRegister=repository.findById(id);
		if(usedRegister.isPresent()) {
			Register r=usedRegister.get();
		    if(password.equals(r.getPassword())) {
		    	return usedRegister;
		    }
		}
		return null;
	}
		
	     
		
	
	@Override
	public Register createRegister(NewRegisterForm registerForm) {
		Register register=new Register();
		register.setId(registerForm.getId());
		register.setMail(registerForm.getMail());
		register.setPassword(registerForm.getPassword());
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		register.setInserteddate(timestamp);
		return register;
	}
	@Override
	public Register registByRegist(Register register) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(register);
	}
	
	
}
