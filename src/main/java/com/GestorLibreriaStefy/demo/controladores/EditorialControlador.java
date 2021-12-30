
package com.GestorLibreriaStefy.demo.controladores;

/**
 *
 * @author fanny
 */

import com.GestorLibreriaStefy.demo.entidades.Editorial;
import com.GestorLibreriaStefy.demo.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/editorial"})
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;

    public EditorialControlador() {
    }

    @GetMapping({"/lista"})
    public String lista(ModelMap modelo) {
        List<Editorial> todos = this.editorialServicio.listarTodos();
        modelo.addAttribute("editoriales", todos);
        return "list-editorial";
    }

    @GetMapping({"/registro"})
    public String guardarEditorial() {
        return "form-editorial";
    }

    @PostMapping({"/registro"})
    public String guardarEditorial(ModelMap modelo, @RequestParam String nombre) throws Exception {
        try {
            this.editorialServicio.guardarEditorial(nombre);
            modelo.put("Exito", "Registro Exitoso");
            return "form-editorial";
        } catch (Exception var4) {
            modelo.put("Error", "Falto Algun Dato");
            return "form-editorial";
        }
    }

    @GetMapping({"/baja/{id}"})
    public String baja(@PathVariable String id) {
        try {
            this.editorialServicio.baja(id);
            return "redirect:/editorial/lista";
        } catch (Exception var3) {
            return "redirect:/";
        }
    }

    @GetMapping({"/alta/{id}"})
    public String alta(@PathVariable String id) {
        try {
            this.editorialServicio.alta(id);
            return "redirect:/editorial/lista";
        } catch (Exception var3) {
            return "redirect:/";
        }
    }
    
}
