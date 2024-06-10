package service;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.shopping_Spring.ShoppingSpringApplication;
import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.repository.RegisterRepository;
import com.example.shopping_Spring.service.RegisterService;

@SpringBootTest(classes=ShoppingSpringApplication.class)
public class RegisterServiceTest {
	
	@Autowired
	 RegisterService registerService;
	
	@MockBean
	RegisterRepository registerRepository;
	
	@Test
	public void findRegisterFieldTest() {
    //テストデータの準備
	 String id = "dfafafad";
	 String password = "password";
	 String mail = "taaaaa@gmail.com";
    //期待値
	 Register expectedRegister = new Register("takashi", "1234", mail, null, "nagai");
	//モックの設定
	 when(registerRepository.findById(id)).thenReturn(Optional.of(expectedRegister));
     when(registerRepository.findByMail(mail)).thenReturn(Optional.of(expectedRegister));
	
     Optional<Register> actualRegister = registerService.findRegisterField(id, password, mail);
     assertTrue(actualRegister.isPresent()); // Optionalが値を含むか検証
     Register actualValue = actualRegister.get();
     assertEquals(expectedRegister.getMail(), actualValue.getMail());

		
	}


}
