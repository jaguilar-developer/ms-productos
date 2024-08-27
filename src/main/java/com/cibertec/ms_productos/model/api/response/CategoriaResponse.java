package com.cibertec.ms_productos.model.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CategoriaResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer categoriaId;
    private String nombre;
    private String descripcion;
    private List<ProductoResponse> productos;

}
