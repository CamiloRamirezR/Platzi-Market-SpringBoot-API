package com.platzi.market.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable // This class will be embedded in the ComprasProductoClass
public class ComprasProductoPK implements Serializable {

    @Column(name = "id_compra")
    private Integer IdCompra;

    @Column(name = "id_producto")
    private Integer IdProducto;

    public Integer getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(Integer idCompra) {
        IdCompra = idCompra;
    }

    public Integer getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Integer idProducto) {
        IdProducto = idProducto;
    }
}
