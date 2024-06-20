package service;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.shopping_Spring.ShoppingSpringApplication;
import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.entity.Purchase;
import com.example.shopping_Spring.repository.ProductsRepository;
import com.example.shopping_Spring.repository.PurcahseRepository;
import com.example.shopping_Spring.service.ProductsService;
import com.example.shopping_Spring.service.PurchaseService;

@SpringBootTest(classes=ShoppingSpringApplication.class)
public class PurchaseServiceTest {
	
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	ProductsService productsService;
	
	@MockBean
	PurcahseRepository repository;
	@MockBean
	ProductsRepository productsRepository;
	
	@Test
	public void purchaseQuantityRegistTest() {
		//データの準備
		// DateオブジェクトをTimestampオブジェクトに変換
		Integer quantity=61;
		Integer id=5;
        String date="2024-05-31T16:32:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
	      
		Products products =new Products(id,"筆箱",20,quantity,timestamp);
		Map<Products,Integer> productsMap=new HashMap<>();
		//購入した商品の数
		Integer purchaseQuantity=10; 
		
		productsMap.put(products, purchaseQuantity);
		
		//Mockの設定
		when(productsRepository.save(products)).thenReturn(products);
		
		Iterable<Products> actualMap=purchaseService.addQuantityRegist(productsMap);
		for (Products actualProducts: actualMap) {
			assertEquals(quantity+purchaseQuantity,actualProducts.getQuantity());
		}
		
	}
	@Test
	public void purcahseQuantityRegistTest() {
		//データの準備
				// DateオブジェクトをTimestampオブジェクトに変換
				Integer quantity=61;
				Integer id=5;
		        String date="2024-05-31T16:32:11";
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		        Timestamp timestamp = Timestamp.valueOf(dateTime);
			      
				Products products =new Products(id,"筆箱",20,quantity,timestamp);
				Map<Products,Integer> productsMap=new HashMap<>();
				//購入した商品の数
				Integer purchaseQuantity=10; 
				
				productsMap.put(products, purchaseQuantity);
				
				//Mockの設定
				when(productsRepository.save(products)).thenReturn(products);
				
				Iterable<Products> actualMap=purchaseService.purchaseQuantityRegist(productsMap);
				for (Products actualProducts: actualMap) {
					assertEquals(quantity-purchaseQuantity,actualProducts.getQuantity());
				}
		
	}
	@Test
	public void registByPurchaseTest() {
		Integer id=5;
		String purchaseuser="Takashi";
		Integer purchaseQuantity=10; 
		LocalDateTime now=LocalDateTime.now();
		Purchase expectedPurchase=new Purchase();
		expectedPurchase.setPurchasedate(Timestamp.valueOf(now));
		expectedPurchase.setPurchaseproduct(id);
		expectedPurchase.setPurchaseuser("Takashi");
		expectedPurchase.setPurchaseitems(purchaseQuantity);
		//Mockの準備
		when(repository.save(any(Purchase.class))).thenAnswer(invocation ->{//henAnswerは、Mockitoのメソッドの呼び出しに対する動作を定義する
			Purchase savedPurchase =invocation.getArgument(0); //メソッド呼び出しの引数を含むオブジェクトの配列を返す
			assertEquals(expectedPurchase.getPurchasedate(),savedPurchase.getPurchasedate());
			assertEquals(expectedPurchase.getPurchaseproduct(),savedPurchase.getPurchaseproduct());
			assertEquals(expectedPurchase.getPurchaseuser(), savedPurchase.getPurchaseuser());
	        assertEquals(expectedPurchase.getPurchaseitems(), savedPurchase.getPurchaseitems());
	        return savedPurchase; //引数をそのまま返す
		});
		
		Purchase actualPurchase=purchaseService.registByPurchase(id,purchaseuser,purchaseQuantity);
		assertEquals(expectedPurchase.getClass(),actualPurchase.getClass());
	}
	@Test
	public void registByProducts() {
		Integer quantity=61;
		Integer id=5;
        String date="2024-05-31T16:32:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
	      
		Products products =new Products(id,"筆箱",20,quantity,timestamp);
		
		Products pro=productsService.updateByProduct(products);
		
		assertEquals(pro.getProductsid(),id);
		assertEquals(pro.getQuantity(),quantity);
		
	}
	
	

}
