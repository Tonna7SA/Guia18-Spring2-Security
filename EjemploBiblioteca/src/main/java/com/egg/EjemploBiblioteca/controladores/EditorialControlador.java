package com.egg.EjemploBiblioteca.controladores;

import com.egg.EjemploBiblioteca.entidades.Autor;
import com.egg.EjemploBiblioteca.entidades.Editorial;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.servicios.AutorServicio;
import com.egg.EjemploBiblioteca.servicios.EditorialServicio;
import java.util.List;
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
@RequestMapping("/editorial") 
public class EditorialControlador {

    
    @Autowired
    private EditorialServicio editorialServicio;
    private Object editoriales;

    @GetMapping("/registrar") 
    public String registrar(){
        return "editorial_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
       
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito", "Laeditorial fue registrado correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }
      
        return "editorial_form.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List <Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editoriales);
        
        return "editorial_list.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", editorialServicio.getone(id));

        return "editorial_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, RedirectAttributes toto) {
        try {
            editorialServicio.modificarEditorial(id, nombre);
            toto.addFlashAttribute("exito", "Muy bien");

            return "redirect:../lista";
        } catch (MiException ex) {
            toto.addFlashAttribute("error", "Merd");
            return "redirect:../lista";
        }

    }
    
    
      @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", editorialServicio.getone(id));

        return "editorial_eliminar.html";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id,  RedirectAttributes toto) {
        try {
            editorialServicio.eliminarEditorial(id);
            toto.addFlashAttribute("exito", "Muy bien");

            return "redirect:../lista";
        } catch (MiException ex) {
            toto.addFlashAttribute("error", "Merd");
            return "redirect:../lista";
        }

    }
    

}

