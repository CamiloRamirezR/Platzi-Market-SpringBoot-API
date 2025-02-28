package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// CrudRepository recibe la tabla (entidad) y el tipo de la clave primaria
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    // Recuperar la lista de productos que pertenezcan a una categoría en específico
    // @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true) // Tambien se podría utilizar la anotación @Query para hacerlo con un Query nativo
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria); // El parametro de entrada debe respetar el nombre de como se llama el atributo en la clase

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    Optional<List<Producto>> findByPrecioVentaLessThanAndIdCategoriaOrderByNombreAsc(double precioVenta, int idCategoria);
}
