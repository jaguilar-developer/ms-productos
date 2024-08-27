package com.cibertec.ms_productos.service.impl;

import com.cibertec.ms_productos.model.api.request.ProductoRequest;
import com.cibertec.ms_productos.model.api.response.ProductoResponse;
import com.cibertec.ms_productos.model.dto.Producto;
import com.cibertec.ms_productos.repository.ProductoRepository;
import com.cibertec.ms_productos.service.transform.ProductoRequestTransform;
import com.cibertec.ms_productos.service.transform.ProductoResponseTransform;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoRequestTransform requestTransform;
    private final ProductoResponseTransform responseTransform;

    @Transactional
    public ProductoResponse guardarProducto(ProductoRequest request) {
        Producto producto = requestTransform.toProducto(request);
        Producto productoGuardado = productoRepository.save(producto);
        return responseTransform.toResponse(productoGuardado);
    }

    @Transactional
    public ProductoResponse editarProducto(Integer id, ProductoRequest request) {
        if (productoRepository.existsById(id)) {
            Producto producto = requestTransform.toProducto(request);
            producto.setProductoId(id);
            Producto productoGuardado = productoRepository.save(producto);
            return responseTransform.toResponse(productoGuardado);
        } else {
            throw new RuntimeException("El producto no existe con ID:" + id);
        }
    }

    @Transactional
    public void eliminarProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe con ID: " + id));

        productoRepository.delete(producto);
    }

    public ProductoResponse obtenerProductoPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe con ID: " + id));

        return responseTransform.toResponse(producto);
    }

    public List<ProductoResponse> obtenerProductoPorNombre(String nombre) {
        List<Producto> productos = productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("El producto no existe con nombre: " + nombre));

        return productos.stream()
                .map(responseTransform::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProductoResponse> obtenerProductos(String orden, String direccion) {
        return productoRepository.findAll().stream()
                .sorted(getComparator(orden, direccion))
                .map(responseTransform::toResponse)
                .collect(Collectors.toList());
    }

    private Comparator<Producto> getComparator(String orden, String direccion) {
        Comparator<Producto> comparator;

        switch (orden.toLowerCase()) {
            case "nombre":
                comparator = Comparator.comparing(Producto::getNombre);
                break;
            case "precio":
                comparator = Comparator.comparing(Producto::getPrecio);
                break;
            default:
                throw new IllegalArgumentException("Orden no soportada: " + orden);
        }

        if ("desc".equalsIgnoreCase(direccion)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

}
