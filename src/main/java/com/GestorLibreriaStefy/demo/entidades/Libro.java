
package com.GestorLibreriaStefy.demo.entidades;

/**
 *
 * @author fanny
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {
    
    @Id
    @GeneratedValue(
        generator = "uuid"
    )
    @GenericGenerator(
        name = "uuid",
        strategy = "uuid2"
    )
    private String id;
    private String isbn;
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Boolean alta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;

    public Libro() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return this.ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Boolean getAlta() {
        return this.alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return this.editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}


