/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecShop.tienda.repository;

/**
 *
 * @author alber
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.TecShop.tienda.domain.Producto;

// @Repository marca esta interfaz como un repositorio de Spring
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> { // JpaRepository<Entidad, Integer> 
    // JpaRepository<Producto, Integer>
    // Producto = la entidad que maneja (tabla producto)
    // Integer = tipo de la llave primaria (id del producto)

    
    
    // Método derivado:
    // Spring analiza el nombre del método y crea la consulta automáticamente.
    // Busca todos los productos donde activo = true
    // Equivalente SQL:
    // SELECT * FROM producto WHERE activo = true;
    public List<Producto> findByActivoTrue();



    // Método derivado usando un rango
    // Busca productos cuyo precio esté entre precioInf y precioSup
    // y los ordena por precio ascendente
    // SQL equivalente:
    // SELECT * FROM producto 
    // WHERE precio BETWEEN precioInf AND precioSup
    // ORDER BY precio ASC;
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(double precioInf, double precioSup);



    // Consulta personalizada usando JPQL
    // JPQL trabaja con entidades (Producto) y atributos (precio)
    // NO con tablas directamente
    @Query(value = "SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    // Los parámetros de la consulta se reciben en el método
    // @Param conecta el parámetro de la consulta con el del método
    public List<Producto> consultaJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);



    // Consulta usando SQL nativo de la base de datos
    // nativeQuery = true indica que se usará SQL real
    @Query(nativeQuery = true, value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);

    // Metodo derivado para productos con stock
    // Busca existencias mayores a 0
    public List<Producto> findByExistenciasGreaterThan(int existencias);

    // Metodo derivado para productos sin stock
    // Busca existencias igual a 0
    public List<Producto> findByExistencias(int existencias);
}














