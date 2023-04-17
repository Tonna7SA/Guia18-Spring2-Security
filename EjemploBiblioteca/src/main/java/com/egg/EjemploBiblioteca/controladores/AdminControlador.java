package com.egg.EjemploBiblioteca.controladores;

import com.egg.EjemploBiblioteca.entidades.Libro;
import com.egg.EjemploBiblioteca.entidades.Usuario;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin")
public class AdminControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "panel.html";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.put("usuarios", usuarios);

        return "listar_usuario.html";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        modelo.put("usuario", usuarioServicio.getone(id));

        return "usuario_eliminar.html";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id,  RedirectAttributes toto, ModelMap modelo) {
        try {
            usuarioServicio.eliminarUsuario(id);
            
            toto.addFlashAttribute("exito", "Muy bien");

           return "redirect:../listar";
        } catch (MiException ex) {
            toto.addFlashAttribute("error", "Merd");
            return "redirect:../listar";
        }

    }
}
