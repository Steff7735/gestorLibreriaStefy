
package com.GestorLibreriaStefy.demo.entidades;

/**
 *
 * @author fanny
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Editorial {
    
     @Id
    @GeneratedValue(
        generator = "uuid"
    )
    @GenericGenerator(
        name = "uuid",
        strategy = "uuid2"
    )
    private String id;
    private String nombre;
    private Boolean alta;

    public Editorial() {
    }

    public Editorial(String id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }
    public String getId() {
        return this.id;
    }

    public void setIdEditorial(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return this.alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
}

    

