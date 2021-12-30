
package com.GestorLibreriaStefy.demo.controladores;

/**
 *
 * @author fanny
 */
import com.GestorLibreriaStefy.demo.entidades.Autor;
import com.GestorLibreriaStefy.demo.servicios.AutorServicio;
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
@RequestMapping({"/autor"})
public class AutorControlador {
    
     @Autowired
    private AutorServicio autorServicio;

    public AutorControlador() {
    }

    @GetMapping({"/lista"})
    public String lista(ModelMap modelo) {
        List<Autor> todos = this.autorServicio.listarTodos();
        modelo.addAttribute("autores", todos);
        return "list-autor";
    }

    @GetMapping({"/registro"})
    public String guardarAutor() {
        return "form-autor";
    }

    @PostMapping({"/registro"})
    public String guardarAutor(ModelMap modelo, @RequestParam String nombre) throws Exception {
        try {
            this.autorServicio.guardarAutor(nombre);
            modelo.put("Exito", "Registro Exitoso");
            return "form-autor";
        } catch (Exception var4) {
            modelo.put("error", "Falto algun dato");
            return "form-autor";
        }
    }

    @GetMapping({"/baja/{id}"})
    public String baja(@PathVariable String id) {
        try {
            this.autorServicio.baja(id);
            return "redirect:/autor/lista";
        } catch (Exception var3) {
            return "redirect:/";
        }
    }

    @GetMapping({"/alta/{id}"})
    public String alta(@PathVariable String id) {
        try {
            this.autorServicio.alta(id);
            return "redirect:/autor/lista";
        } catch (Exception var3) {
            return "redirect:/";
        }
    }
}
