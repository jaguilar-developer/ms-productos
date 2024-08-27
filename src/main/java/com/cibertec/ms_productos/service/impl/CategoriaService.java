package com.cibertec.ms_productos.service.impl;

import com.cibertec.ms_productos.model.api.request.CategoriaRequest;
import com.cibertec.ms_productos.model.api.response.CategoriaResponse;
import com.cibertec.ms_productos.model.dto.Categoria;
import com.cibertec.ms_productos.repository.CategoriaRepository;
import com.cibertec.ms_productos.service.transform.CategoriaRequestTransform;
import com.cibertec.ms_productos.service.transform.CategoriaResponseTransform;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaRequestTransform requestTransform;
    private final CategoriaResponseTransform responseTransform;

    @Transactional
    public List<CategoriaResponse> guardarCategorias(List<CategoriaRequest> requestList) {
        List<Categoria> categorias = requestList.stream()
                .map(requestTransform::toCategoria)
                .sorted(Comparator.comparing(Categoria::getNombre)).toList();

        List<Categoria> categoriasGuardadas = categorias.stream()
                .map(categoriaRepository::save).toList();

        return categoriasGuardadas.stream()
                .map(responseTransform::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoriaResponse guardarCategoria(CategoriaRequest request) {
        Categoria categoria = requestTransform.toCategoria(request);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return responseTransform.toResponse(categoriaGuardada);
    }

    @Transactional
    public CategoriaResponse actualizarCategoria(Integer id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el valor buscado: " + id));

        if (request.getNombre() != null) {
            categoria.setNombre(request.getNombre());
        }

        if (request.getDescripcion() != null) {
            categoria.setDescripcion(request.getDescripcion());
        }

        Categoria categoriaActualizada = categoriaRepository.save(categoria);

        return responseTransform.toResponse(categoriaActualizada);
    }

    public CategoriaResponse buscarCategoria(String tipoBusqueda, String valorBuscado) {
        Categoria categoriaEncontrada;
        switch (tipoBusqueda) {
            case "id" -> categoriaEncontrada =
                    categoriaRepository.findById(Integer.valueOf(valorBuscado))
                            .orElseThrow(() -> new RuntimeException("No se encontro la categoria con ID: " + valorBuscado));
            case "nombre" -> categoriaEncontrada =
                    categoriaRepository.findByNombre(valorBuscado)
                            .orElseThrow(() -> new RuntimeException("No se encontro la categoria con nombre: " + valorBuscado));
            default -> throw new RuntimeException("Tipo de busqueda no soportado");
        }

        return responseTransform.toResponse(categoriaEncontrada);
    }

    public List<CategoriaResponse> obtenerCategorias() {
        return categoriaRepository.findAll().stream()
                .map(responseTransform::toResponse)
                .collect(Collectors.toList());
    }


}
