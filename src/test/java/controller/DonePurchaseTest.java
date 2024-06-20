package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.shopping_Spring.ShoppingSpringApplication;
import com.example.shopping_Spring.controller.DonePurchase;
import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.entity.Register;
import com.example.shopping_Spring.service.PurchaseService;

import jakarta.servlet.http.HttpSession;

@SpringBootTest(classes=ShoppingSpringApplication.class)
@AutoConfigureMockMvc
public class DonePurchaseTest {
	
	@Autowired
	private DonePurchase donePurchase;
	
	@MockBean
	private PurchaseService purchaseService;

	@MockBean
	Register register;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders
				.standaloneSetup(donePurchase).build();
	}
	
	@Test
	public void donePurchaseTest() throws Exception{
		mockMvc.perform(get("/donePurchase/donePurchase"))
		    .andExpect(status().isOk())
		    .andExpect(view().name("purchaseConfirm"));
	}
	@Test
	public void purhcaseConfirmTest() throws Exception{
		// セッションのモック化*sessionは複数あるためクラス内でモック化
		HttpSession session = Mockito.mock(HttpSession.class);
		
		// register情報のモックを設定
		Register mockRegister=new Register();
		mockRegister.setId("テスト");
		when(session.getAttribute("register")).thenReturn(mockRegister);
		
		// セッションから取得される情報のモックを設定
		Map<Products,Integer> purchaseMap=new HashMap<>();
		//purchaseMapに適切なデータをセットアップ
		Integer quantity=61;
		Integer id=5;
        String date="2024-05-31T16:32:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
		Products products =new Products(id,"筆箱",20,quantity,timestamp);
		Integer purchaseQuantity=10;
		purchaseMap.put(products, purchaseQuantity);
		
		when(session.getAttribute("purchaseMap")).thenReturn(purchaseMap);
		
		 // purchaseService.purchaseQuantityRegist()メソッドのモック設定
		Iterable<Products> mockPurchaseProductList=new ArrayList<>();
		when(purchaseService.purchaseQuantityRegist(anyMap())).thenReturn(mockPurchaseProductList);

		for(Products p: purchaseMap.keySet()) {
			Integer purchaseItems=purchaseMap.get(p);
			Integer purchaseProduct=p.getProductsid();
			//verify(mockObject, times(1)).doSomething();
			verify(purchaseService,times(1)).registByPurchase(purchaseProduct,mockRegister.getId(),purchaseItems);
		}
		
		mockMvc.perform(get("/donePurchase/donePurchase")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk())
				.andExpect(view().name("purchaseConfirm"));
		
		ArgumentCaptor<String> attributeNameCaptor=ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Object> attributeValueCaptor=ArgumentCaptor.forClass(Object.class);
		verify(session,times(1)).setAttribute(attributeNameCaptor.capture(),attributeValueCaptor.capture());//erifyメソッドは、mockと呼ばれるモックオブジェクトを受け取り、そのオブジェクトが特定の振る舞いを行ったかどうかを検証します。
		assertEquals("purchaseProductsList",attributeNameCaptor.getValue());
		assertEquals(mockPurchaseProductList,attributeValueCaptor.getValue());
		
	}

}
