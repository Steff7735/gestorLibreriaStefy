
package com.GestorLibreriaStefy.demo.controladores;

/**
 *
 * @author fanny
 */

import com.GestorLibreriaStefy.demo.entidades.Autor;
import com.GestorLibreriaStefy.demo.entidades.Editorial;
import com.GestorLibreriaStefy.demo.servicios.AutorServicio;
import com.GestorLibreriaStefy.demo.servicios.EditorialServicio;
import com.GestorLibreriaStefy.demo.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/"})
public class PortalControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;
    @Autowired
    private LibroServicio libroServicio;

    public PortalControlador() {
    }

    @GetMapping({"/"})
    public String index(ModelMap modelo) {
        List<Autor> autoresActivos = this.autorServicio.listarActivos();
        List<Editorial> editorialActivos = this.editorialServicio.listarActivos();
        modelo.addAttribute("autores", autoresActivos);
        modelo.addAttribute("editoriales", editorialActivos);
        return "index";
    }
}

    

