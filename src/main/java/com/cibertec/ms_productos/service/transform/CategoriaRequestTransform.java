package com.cibertec.ms_productos.service.transform;

import com.cibertec.ms_productos.model.api.request.CategoriaRequest;
import com.cibertec.ms_productos.model.api.request.ProductoRequest;
import com.cibertec.ms_productos.model.dto.Categoria;
import com.cibertec.ms_productos.model.dto.Producto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoriaRequestTransform {

    public Categoria toCategoria(CategoriaRequest request) {
        Categoria objCategoria = new Categoria();

        if (request != null) {
            objCategoria.setNombre(request.getNombre());
            objCategoria.setDescripcion(request.getDescripcion());

            if (request.getProductos() != null && !request.getProductos().isEmpty()) {
                Set<Producto> productoSet = request.getProductos().stream()
                        .map(productoRequest -> toProducto(productoRequest, objCategoria))
                        .collect(Collectors.toSet());
                objCategoria.setProductos(productoSet);
            }
        }

        return objCategoria;
    }

    private Producto toProducto(ProductoRequest productoRequest, Categoria categoria) {
        Producto producto = new Producto();
        if (productoRequest != null) {
            producto.setNombre(productoRequest.getNombre());
            producto.setDescripcion(productoRequest.getDescripcion());
            producto.setPrecio(productoRequest.getPrecio());
            producto.setCantidad(productoRequest.getCantidad());
            producto.setImagen(productoRequest.getImagen());
            producto.setCategoria(categoria);
            producto.setUsuarioId(productoRequest.getUsuarioId());
            producto.setFechaRegistro(LocalDateTime.now());
        }
        return producto;
    }
}
