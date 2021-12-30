package com.GestorLibreriaStefy.demo.servicios;

/**
 *
 * @author fanny
 */
import com.GestorLibreriaStefy.demo.entidades.Libro;
import com.GestorLibreriaStefy.demo.excepciones.ErroresDeServicio;
import com.GestorLibreriaStefy.demo.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @Transactional
    public void guardarLibro(Libro libro) throws ErroresDeServicio {

        if (libro.getAnio() != null && libro.getAnio().toString().length() >= 4 && (libro.getAnio() <= 1400 || libro.getAnio() >= 1)) {
        } else {
            throw new ErroresDeServicio("Vuelva a ingresar los datos indicados");
        }

        if (libro.getEjemplares() != null && libro.getEjemplares() >= 1) {
        } else {
            throw new ErroresDeServicio("Error al indicar la cantidad de ejemplares");
        }

        if (libro.getIsbn() != null && libro.getIsbn().length() <= 10 && !libro.getIsbn().isEmpty()) {
        } else {
            throw new ErroresDeServicio("Error al ingresar el ISBN");
        }

        if (libro.getTitulo() != null && !libro.getTitulo().contains("  ") && libro.getTitulo().length() >= 3) {
        } else {
            throw new ErroresDeServicio("Error al indicar el titulo");
        }

        if (libro.getAutor() == null) {

            throw new ErroresDeServicio("Debe indicar un Autor");

        } else if (libro.getEditorial() == null) {

            throw new ErroresDeServicio("Debe indicar una Editorial");

        } else {

            libro.setAlta(Boolean.TRUE);

            this.libroRepositorio.save(libro);
        }

    }

    @Transactional
    public void ModificarLibro(String id) throws ErroresDeServicio {
        Optional<Libro> respuesta = this.libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = (Libro) respuesta.get();
            this.comprobarDatos(libro.getIsbn(), libro.getTitulo(), libro.getAnio(), libro.getEjemplares());
            libro.setTitulo(id);
            libro.setIsbn(id);
            libro.setAutor(this.autorServicio.guardarAutor(id));
            libro.setEditorial(this.editorialServicio.guardarEditorial(id));
            libro.setAnio(0);
            libro.setEjemplares(0);
            this.libroRepositorio.save(libro);
        } else {
            throw new ErroresDeServicio("El id del libro ingresado no se encontro");
        }
    }

    @Transactional
    public Libro alta(String id) {
        Libro libro = (Libro) this.libroRepositorio.getOne(id);
        libro.setAlta(true);
        return (Libro) this.libroRepositorio.save(libro);
    }

    @Transactional
    public Libro baja(String id) {
        Libro libro = (Libro) this.libroRepositorio.getOne(id);
        libro.setAlta(false);
        return (Libro) this.libroRepositorio.save(libro);
    }

    @Transactional(
            readOnly = true
    )
    public Libro getOne(String id) {
        return (Libro) this.libroRepositorio.getById(id);
    }

    @Transactional(
            readOnly = true
    )
    public List<Libro> listarActivos() {
        return this.libroRepositorio.buscarActivos();
    }

    @Transactional(
            readOnly = true
    )
    public List<Libro> listarTodos() {
        return this.libroRepositorio.findAll();
    }

    public Optional<Libro> buscarId(String id) {
        return this.libroRepositorio.findById(id);
    }

    private void comprobarDatos(String isbn, String titulo, Integer anio, Integer ejemplares) throws ErroresDeServicio {
        if (isbn == null) {
            throw new ErroresDeServicio("El isbn no puede ser nulo");
        } else if (titulo != null && !titulo.isEmpty()) {
            if (anio == null) {
                throw new ErroresDeServicio("Debe colocar un año");
            } else if (ejemplares == null) {
                throw new ErroresDeServicio("Debe colocar la cantidad de ejemplares");
            }
        } else {
            throw new ErroresDeServicio("El titulo no puede estar vacio");
        }
    }

}
