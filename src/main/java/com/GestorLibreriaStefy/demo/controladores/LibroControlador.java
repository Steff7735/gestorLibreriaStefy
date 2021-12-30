
package com.GestorLibreriaStefy.demo.controladores;

/**
 *
 * @author fanny
 */
import com.GestorLibreriaStefy.demo.entidades.Autor;
import com.GestorLibreriaStefy.demo.entidades.Editorial;
import com.GestorLibreriaStefy.demo.entidades.Libro;
import com.GestorLibreriaStefy.demo.excepciones.ErroresDeServicio;
import com.GestorLibreriaStefy.demo.servicios.AutorServicio;
import com.GestorLibreriaStefy.demo.servicios.EditorialServicio;
import com.GestorLibreriaStefy.demo.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/libro"})
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    public LibroControlador() {
    }

    @GetMapping({"/lista"})
    public String lista(ModelMap modelo) {
        List<Libro> todos = this.libroServicio.listarTodos();
        modelo.addAttribute("libros", todos);
    
        return "list-libro";
    }

    @GetMapping({"/registro"})
    public String guardarLibro(ModelMap modelo, @RequestParam(required = false) String id) {
        if (id != null) {
            modelo.addAttribute("libro", this.libroServicio.buscarId(id));
            return "list-libro.html";
        } else {
            Libro aux = new Libro();
            aux.setAlta(Boolean.TRUE);
            aux.setTitulo("Ingrese el título del Libro");
            aux.setAnio(0);
            aux.setEjemplares(0);
            aux.setIsbn("Ingrese el código");
            modelo.addAttribute("libro", aux);
            List<Autor> autores = this.autorServicio.listarTodos();
            modelo.addAttribute("listaautor", autores);
            List<Editorial> editoriales = this.editorialServicio.listarTodos();
            modelo.addAttribute("listaeditorial", editoriales);
            return "form-libro.html";
        }
    }

    @PostMapping({"/registro"})
    public String guardarLibro(ModelMap modelo, RedirectAttributes redirectAtributes, @ModelAttribute Libro libro) throws ErroresDeServicio {
        this.libroServicio.guardarLibro(libro);
        modelo.put("exito", "Registro Exitoso");
        return "form-libro.html";
    }

    @GetMapping({"/modificar"})
    public String modificar(ModelMap modelo, @RequestParam(required = false) String id) {
        modelo.put("libro", this.libroServicio.getOne(id));
        return "form-libro-modif";
    }

    @PostMapping({"/modificar"})
    public String modificar(String id) {
        try {
            this.libroServicio.ModificarLibro(id);
            return "form-libro-modif";
        } catch (ErroresDeServicio var3) {
            return "form-libro-modif";
        }
    }

    @GetMapping({"/baja/{id}"})
    public String baja(@PathVariable String id) {
        try {
            this.libroServicio.baja(id);
            return "redirect:/libro/lista";
        } catch (Exception var3) {
            return "redirect:/";
        }
    }

    @GetMapping({"/alta/{id}"})
    public String alta(@PathVariable String id) {
        try {
            this.libroServicio.alta(id);
            return "redirect:/libro/lista";
        } catch (Exception var3) {
            return "redirect:/";
        }
    }
    
}
