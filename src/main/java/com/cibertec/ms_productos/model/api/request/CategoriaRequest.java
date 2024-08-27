package com.cibertec.ms_productos.model.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CategoriaRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;
    private List<ProductoRequest> productos;

}
