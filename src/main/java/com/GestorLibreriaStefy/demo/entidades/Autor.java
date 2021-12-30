
package com.GestorLibreriaStefy.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author fanny
 */
@Entity
public class Autor {
    
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

    public Autor() {
    }

    public Autor(String id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }
     public String getId() {
        return this.id;
    }

    public void setId(String id) {
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


    

