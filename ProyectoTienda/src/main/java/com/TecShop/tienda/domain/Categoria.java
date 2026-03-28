/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecShop.tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author alber
 */

@Data // Genera los getter setter toString... automaticamente.
@Entity // Se usa si reprecenta una tabla
@Table(name = "categoria") // Se usa cuando el nombre de la tabla no coincide con el nombre de la clase.
public class Categoria implements Serializable { //El nombre de la clase es usado en el repository      JpaRepository<Categoria, Integer = idCategoria>

    private static final long serialVersionUID = 1L;

    @Id // Indica llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se usa si la base de datos genera el ID automáticamente.
    @Column(name = "id_categoria") // Define el nombde de la columna si es diferente al atributo de java
    private Integer idCategoria;

    @Column(unique = true, nullable = false, length = 50) // Define propiedades de la columna
    @NotNull // Valida que los datos no sean nulos antes de guardar.
    @Size(max = 50) // Define tamaño maximo del texto
    private String descripcion;

    @Column(length = 1024)
    @Size(max = 1024)
    private String rutaImagen;

    @Column(name = "activo")
    private Boolean activo;
    
    @OneToMany(mappedBy = "categoria") // Anotacion de relacion de tabla N:M
    private List<Producto> productos;
    
}
