package com.cibertec.ms_productos.service.transform;

import com.cibertec.ms_productos.model.api.response.CategoriaResponse;
import com.cibertec.ms_productos.model.api.response.ProductoResponse;
import com.cibertec.ms_productos.model.dto.Categoria;
import com.cibertec.ms_productos.model.dto.Producto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoriaResponseTransform {

    public CategoriaResponse toResponse(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        CategoriaResponse response = new CategoriaResponse();
        response.setCategoriaId(categoria.getCategoriaId());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
        response.setProductos(convertProductos(categoria.getProductos()));

        return response;
    }

    private List<ProductoResponse> convertProductos(Set<Producto> productos) {
        if (productos == null) {
            return null;
        }
        return productos.stream()
                .map(this::toProductoResponse)
                .collect(Collectors.toList());
    }

    private ProductoResponse toProductoResponse(Producto producto) {
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

        return response;
    }
}
