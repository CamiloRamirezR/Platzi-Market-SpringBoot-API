package com.platzi.market.mapper;

import com.platzi.market.domain.Product;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.CategoryMapper;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class ProductMapperTest {

    @Mock
    private CategoryMapper categoryMapper; // Simulamos el CategoryMapper

    @InjectMocks
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void shouldMapProductoToProduct() {
        // GIVEN: Un objeto de la entidad de persistencia
        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Manzana");
        producto.setIdCategoria(1);
        producto.setPrecioVenta(700.0);
        producto.setCantidadStock(130);
        producto.setEstado(true);

        // WHEN: Se convierte a un objeto de dominio
        Product product = productMapper.toProduct(producto);

        // THEN: Se verifican los valores mapeados correctamente
        assertNotNull(product);
        assertEquals(1, product.getProductId());
        assertEquals("Manzana", product.getName());
        assertEquals(1, product.getCategoryId());
        assertEquals(700.0, product.getPrice());
        assertEquals(130, product.getStock());
        assertTrue(product.isActive());
    }

    @Test
    void shouldMapProductToProducto() {
        // GIVEN: Un objeto del dominio
        Product product = new Product();
        product.setProductId(2);
        product.setName("Pera");
        product.setCategoryId(1);
        product.setPrice(750.0);
        product.setStock(210);
        product.setActive(true);

        // WHEN: Se convierte a un objeto de persistencia
        Producto producto = productMapper.toProducto(product);

        // THEN: Se verifican los valores mapeados correctamente
        assertNotNull(producto);
        assertEquals(2, producto.getIdProducto());
        assertEquals("Pera", producto.getNombre());
        assertEquals(1, producto.getIdCategoria());
        assertEquals(750.0, producto.getPrecioVenta());
        assertEquals(210, producto.getCantidadStock());
        assertTrue(producto.getEstado());

        // Verificamos que el campo `codigoBarras` se haya ignorado
        assertNull(producto.getCodigoBarras());
    }
}
