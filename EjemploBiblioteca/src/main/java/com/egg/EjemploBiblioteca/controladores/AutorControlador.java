package com.egg.EjemploBiblioteca.controladores;

import com.egg.EjemploBiblioteca.entidades.Autor;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/autor") //localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {

        try {
            autorServicio.crearAutor(nombre);
            modelo.put("exito", "El autor fue registrado correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }

        return "autor_form.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores();

        modelo.addAttribute("autores", autores);

        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorServicio.getone(id));

        return "autor_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, RedirectAttributes toto) {
        try {
            autorServicio.modificarAutor(nombre, id);
 toto.addFlashAttribute("exito", "Muy bien");
            return "redirect:../lista";
        } catch (MiException ex) {
            toto.addFlashAttribute("error", "Merd");
            return "autor_modificar.html";
        }

    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorServicio.getone(id));

        return "autor_eliminar.html";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id,  RedirectAttributes toto) {
        try {
            autorServicio.eliminarAutor(id);
            toto.addFlashAttribute("exito", "Muy bien");

            return "redirect:../lista";
        } catch (MiException ex) {
            toto.addFlashAttribute("error", "Merd");
            return "redirect:../lista";
        }

    }

}
