package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.shopping_Spring.ShoppingSpringApplication;
import com.example.shopping_Spring.entity.Products;
import com.example.shopping_Spring.repository.ProductsRepository;
import com.example.shopping_Spring.service.ProductsService;

@SpringBootTest(classes=ShoppingSpringApplication.class)
public class ProductsServiceTest {
	@Autowired
	ProductsService service;
	
	@MockBean
	ProductsRepository repository;
	
	@Test
	public void updateByProductsTest() {
		//データの準備
		Integer quantity=61;
		Integer id=5;
        String date="2024-05-31T16:32:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
	      
		Products products =new Products(id,"筆箱",20,quantity,timestamp);
		
		when(repository.save(products)).thenReturn(products);
		
		Products actualProducts=service.updateByProduct(products);
		assertEquals(products.getClass(),actualProducts.getClass());

	}

}
