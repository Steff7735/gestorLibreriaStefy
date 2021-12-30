
package com.GestorLibreriaStefy.demo.servicios;

/**
 *
 * @author fanny
 */
import com.GestorLibreriaStefy.demo.entidades.Editorial;
import com.GestorLibreriaStefy.demo.excepciones.ErroresDeServicio;
import com.GestorLibreriaStefy.demo.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    public EditorialServicio() {
    }

    @Transactional
    public Editorial guardarEditorial(String nombre) throws ErroresDeServicio {
        this.validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        return (Editorial)this.editorialRepositorio.save(editorial);
    }

    @Transactional
    public Editorial alta(String id) {
        Editorial editorial = (Editorial)this.editorialRepositorio.getOne(id);
        editorial.setAlta(true);
        return (Editorial)this.editorialRepositorio.save(editorial);
    }

    @Transactional
    public Editorial baja(String id) {
        Editorial editorial = (Editorial)this.editorialRepositorio.getOne(id);
        editorial.setAlta(false);
        return (Editorial)this.editorialRepositorio.save(editorial);
    }

    @Transactional(
        readOnly = true
    )
    public List<Editorial> listarActivos() {
        return this.editorialRepositorio.buscarActivos();
    }

    @Transactional(
        readOnly = true
    )
    public List<Editorial> listarTodos() {
        return this.editorialRepositorio.findAll();
    }

    public void validar(String nombre) throws ErroresDeServicio {
        if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
            throw new ErroresDeServicio("El nombre ingresado no puede estar vacio ni ser nulo");
        }
    }
    
}
