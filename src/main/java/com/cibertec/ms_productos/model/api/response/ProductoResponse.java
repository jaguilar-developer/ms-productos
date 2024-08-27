package com.cibertec.ms_productos.model.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer productoId;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer cantidad;
    private String imagen;
    private Integer categoria;
    private Integer usuarioId;
}
