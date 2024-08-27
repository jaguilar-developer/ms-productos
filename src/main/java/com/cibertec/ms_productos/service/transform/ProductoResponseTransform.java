package com.cibertec.ms_productos.service.transform;

import com.cibertec.ms_productos.model.api.response.ProductoResponse;
import com.cibertec.ms_productos.model.dto.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoResponseTransform {

    public ProductoResponse toResponse(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoResponse response = new ProductoResponse();
        response.setProductoId(producto.getProductoId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setCantidad(producto.getCantidad());
        response.setImagen(producto.getImagen());
        response.setUsuarioId(producto.getUsuarioId());
        response.setCategoria(producto.getCategoria().getCategoriaId());

        return response;
    }


}
