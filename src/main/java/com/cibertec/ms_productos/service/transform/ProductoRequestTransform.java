package com.cibertec.ms_productos.service.transform;

import com.cibertec.ms_productos.model.api.request.ProductoRequest;
import com.cibertec.ms_productos.model.dto.Categoria;
import com.cibertec.ms_productos.model.dto.Producto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductoRequestTransform {

    public Producto toProducto(ProductoRequest request) {
        Producto objProducto = new Producto();

        if (request != null) {
            objProducto.setNombre(request.getNombre());
            objProducto.setDescripcion(request.getDescripcion());
            objProducto.setPrecio(request.getPrecio());
            objProducto.setCantidad(request.getCantidad());
            objProducto.setImagen(request.getImagen());
            objProducto.setUsuarioId(request.getUsuarioId());
            objProducto.setFechaRegistro(LocalDateTime.now());

            Categoria objCategoria = new Categoria();
            objCategoria.setCategoriaId(request.getCategoria());
            objProducto.setCategoria(objCategoria);
        }

        return objProducto;
    }
}
