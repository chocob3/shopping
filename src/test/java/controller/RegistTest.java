package controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.shopping_Spring.ShoppingSpringApplication;
import com.example.shopping_Spring.controller.Regist;
import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.form.NewRegisterForm;
import com.example.shopping_Spring.service.RegisterService;


@SpringBootTest(classes=ShoppingSpringApplication.class)
@AutoConfigureMockMvc
public class RegistTest {
	
	@Autowired
	Regist regist;
	
	@MockBean
	RegisterService registerService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders
				.standaloneSetup(regist).build();
	}
	
	@Test
	public void registTest() throws Exception{
		mockMvc.perform(get("/regist/regist"))
		   .andExpect(status().isOk())
		   .andExpect(view().name("registForm"));
	}
	@Test
	public void registCheckTest() throws Exception{
		
		//既存のアカウント登録時
		String id="takashi";
		String password="1234";
		String password2="1234";
		String email="taaaaa@gmail.com";
		
		NewRegisterForm newRegisterForm=new NewRegisterForm(id,password,password2,email);
		Register regi=new Register(id,password,email,null,"nagai");
		Optional<Register> optionalRegister=Optional.of(regi);
		
		when(registerService.findRegisterField(id, password, email)).thenReturn(optionalRegister);
		
		
		
		 mockMvc.perform(post("/regist/check")
		  .contentType(MediaType.APPLICATION_FORM_URLENCODED)//MediaType.APPLICATION_FORM_URLENCODEDは、HTMLフォームでデータを送信する際に使用される標準的なコンテンツタイプを指定
		  .flashAttr("newRegisterForm",newRegisterForm))
		  .andExpect(status().isOk())
		  .andExpect(model().attribute("registerrmsg", "既にアカウントは存在します"));
		
		 verify(registerService,never()).createRegister(any());
	}
	@Test
	public void registOkTest() throws Exception{
		
		when(registerService.createRegister(any())).thenReturn(new Register());
		//新規アカウント登録時
	    String id = "pfadfa";
	    String password = "password";
	    String password2 = "password";
	    String email = "taagaga@gmail";
	    
	    NewRegisterForm newRegisterForm=new NewRegisterForm(id,password,password2,email);
	    
	    mockMvc.perform(post("/regist/check/")
	    		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	    		.flashAttr("newRegisterForm", newRegisterForm))
	            .andExpect(status().isOk())
		        .andExpect(view().name("registOk"));
	    
	    verify(registerService, times(1)).createRegister(any());
	    //createRegisterメソッドが1回呼び出されたことを検証。
	}

}
