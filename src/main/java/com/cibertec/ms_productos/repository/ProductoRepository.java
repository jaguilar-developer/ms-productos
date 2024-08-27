package com.cibertec.ms_productos.repository;

import com.cibertec.ms_productos.model.dto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre%")
    Optional<List<Producto>> findByNombre(@Param("nombre") String nombre);
}
