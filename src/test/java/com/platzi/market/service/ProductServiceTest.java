package com.platzi.market.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito en JUnit 5
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // Simula el repositorio para no usar la BD real

    @InjectMocks
    private ProductService productService; // Se prueba el servicio con el mock inyectado

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1);
        product.setName("Lechuga");
        product.setPrice(2000.0);
        product.setStock(60);
        product.setCategoryId(1);
        product.setActive(true);
    }

    @Test
    void testGetProductById_WhenProductExists() {
        when(productRepository.getProduct(1)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProduct(1);

        assertTrue(result.isPresent());
        assertEquals("Lechuga", result.get().getName());
        assertEquals(2000.0, result.get().getPrice());
    }

    @Test
    void testGetProductById_WhenProductDoesNotExist() {
        when(productRepository.getProduct(999)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProduct(999);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct);
        assertEquals(1, savedProduct.getProductId());
        assertEquals("Lechuga", savedProduct.getName());
    }

    @Test
    void testDeleteProduct_WhenProductExists() {
        when(productRepository.getProduct(1)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(1);

        boolean isDeleted = productService.delete(1);

        assertTrue(isDeleted);
        verify(productRepository, times(1)).delete(1);
    }

    @Test
    void testDeleteProduct_WhenProductDoesNotExist() {
        when(productRepository.getProduct(999)).thenReturn(Optional.empty());

        boolean isDeleted = productService.delete(999);

        assertFalse(isDeleted);
        verify(productRepository, never()).delete(anyInt());
    }
}
