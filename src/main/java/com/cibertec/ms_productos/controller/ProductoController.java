package com.cibertec.ms_productos.controller;

import com.cibertec.ms_productos.model.api.request.ProductoRequest;
import com.cibertec.ms_productos.model.api.response.ProductoResponse;
import com.cibertec.ms_productos.service.impl.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/guardarProducto")
    public ResponseEntity<ProductoResponse> guardarProducto(@RequestBody ProductoRequest request) {
        ProductoResponse productoResponse = productoService.guardarProducto(request);
        return ResponseEntity.ok(productoResponse);
    }

    @PatchMapping("/editarProducto/{id}")
    public ResponseEntity<ProductoResponse> editarProducto(@PathVariable Integer id, @RequestBody ProductoRequest request) {
        ProductoResponse productoResponse = productoService.editarProducto(id, request);
        return ResponseEntity.ok(productoResponse);
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/obtenerProductoPorId/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable Integer id) {
        ProductoResponse productoResponse = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(productoResponse);
    }

    @GetMapping("/obtenerProductoPorNombre")
    public ResponseEntity<List<ProductoResponse>> obtenerProductoPorNombre(
            @RequestParam(name = "nombre") String nombre) {
        List<ProductoResponse> productoResponseList = productoService.obtenerProductoPorNombre(nombre);
        return ResponseEntity.ok(productoResponseList);
    }

    @GetMapping("/obtenerProductos")
    public ResponseEntity<List<ProductoResponse>> obtenerProductos(
            @RequestParam(name = "orden", required = false, defaultValue = "nombre") String orden,
            @RequestParam(name = "direccion", required = false, defaultValue = "asc") String direccion) {
        List<ProductoResponse> productoResponseList = productoService.obtenerProductos(orden, direccion);
        return ResponseEntity.ok(productoResponseList);
    }
}
