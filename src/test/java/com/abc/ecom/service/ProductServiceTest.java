package com.abc.ecom.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.ecom.entity.Product;
import com.abc.ecom.exception.ProductNotFoundException;
import com.abc.ecom.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService = new ProductServiceImpl();
	
	@Mock
	private ProductRepository productRepository;
	
	@Test
	public void testGetProductById() {
		
		Product product = new Product();
		product.setProductName("Iphone13");
		product.setProductId(1);
		product.setProductPrice(150000);
		product.setProductCategory("Mobile");
		product.setCreateOn(LocalDate.of(2000, 10, 15));
		
		Optional<Product> optionalProduct = Optional.of(product);
		int productId = 2 ;
		
		when(productRepository.findById(2)).thenReturn(optionalProduct);
		
		Product existingProduct = productService.getProductById(productId);
		
		assertEquals(product.getProductName(),existingProduct.getProductName());
		assertEquals(product.getProductId(),existingProduct.getProductId());
		assertEquals(product.getProductCategory(),existingProduct.getProductCategory());
		assertEquals(product.getProductPrice(),existingProduct.getProductPrice());
		assertEquals(product.getCreateOn(),existingProduct.getCreateOn());
	}

	
	@Test
	public void testGetProductByIdNotFound() {
		
		int productId = 5;
		when(productRepository.findById(productId)).thenThrow(ProductNotFoundException.class);
		assertThrows(ProductNotFoundException.class,()->productService.getProductById(productId));
		
	}
}
