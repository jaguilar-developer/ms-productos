package com.cibertec.ms_productos.controller;

import com.cibertec.ms_productos.model.api.request.CategoriaRequest;
import com.cibertec.ms_productos.model.api.response.CategoriaResponse;
import com.cibertec.ms_productos.service.impl.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping("/guardarCategoria")
    public ResponseEntity<CategoriaResponse> guardarCategoria(@RequestBody CategoriaRequest request) {
        CategoriaResponse categoriaResponse = categoriaService.guardarCategoria(request);
        return ResponseEntity.ok(categoriaResponse);
    }

    @PostMapping("/guardarCategorias")
    public ResponseEntity<List<CategoriaResponse>> guardarCategorias(@RequestBody List<CategoriaRequest> requests) {
        List<CategoriaResponse> categoriaResponseList = categoriaService.guardarCategorias(requests);
        return ResponseEntity.ok(categoriaResponseList);
    }

    @GetMapping("/obtenerCategorias")
    public ResponseEntity<List<CategoriaResponse>> obtenerCategorias() {
        List<CategoriaResponse> categoriaResponseList = categoriaService.obtenerCategorias();
        return ResponseEntity.ok(categoriaResponseList);
    }

    @PatchMapping("/actualizarCategoria/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaRequest request) {
        CategoriaResponse categoriaResponse = categoriaService.actualizarCategoria(id, request);
        return ResponseEntity.ok(categoriaResponse);
    }

    @GetMapping("/buscarCategoria")
    public ResponseEntity<CategoriaResponse> buscarCategoria(
            @RequestParam("tipoBusqueda") String tipoBusqueda,
            @RequestParam("valorBusqueda") String valorBusqueda) {
        CategoriaResponse categoriaResponse = categoriaService.buscarCategoria(tipoBusqueda, valorBusqueda);
        return ResponseEntity.ok(categoriaResponse);
    }

}
