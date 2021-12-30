
package com.GestorLibreriaStefy.demo.servicios;

/**
 *
 * @author fanny
 */

import com.GestorLibreriaStefy.demo.entidades.Autor;
import com.GestorLibreriaStefy.demo.excepciones.ErroresDeServicio;
import com.GestorLibreriaStefy.demo.repositorios.AutorRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;

    public AutorServicio() {
    }

    @Transactional
    public Autor guardarAutor(String nombre) throws ErroresDeServicio {
        this.validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        return (Autor)this.autorRepositorio.save(autor);
    }

    @Transactional
    public Autor alta(String id) {
        Autor autor = (Autor)this.autorRepositorio.getOne(id);
        autor.setAlta(true);
        return (Autor)this.autorRepositorio.save(autor);
    }

    @Transactional
    public Autor baja(String id) {
        Autor autor = (Autor)this.autorRepositorio.getOne(id);
        autor.setAlta(false);
        return (Autor)this.autorRepositorio.save(autor);
    }

    @Transactional(
        readOnly = true
    )
    public List<Autor> listarActivos() {
        return this.autorRepositorio.buscarActivos();
    }

    @Transactional(
        readOnly = true
    )
    public List<Autor> listarTodos() {
        return this.autorRepositorio.findAll();
    }

    public void validar(String nombre) throws ErroresDeServicio {
        if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
            throw new ErroresDeServicio("El nombre ingresado no puede estar vacio ni ser nulo");
        }
    }
    
}
