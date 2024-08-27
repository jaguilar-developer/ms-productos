package com.cibertec.ms_productos.model.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer cantidad;
    private String imagen;
    private Integer categoria;
    private Integer usuarioId;

}
