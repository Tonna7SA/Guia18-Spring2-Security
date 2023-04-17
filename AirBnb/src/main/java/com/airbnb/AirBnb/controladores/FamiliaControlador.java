package com.airbnb.AirBnb.controladores;

import com.airbnb.AirBnb.entidades.Familia;
import com.airbnb.AirBnb.servicios.FamiliaServicio;
import com.airbnb.AirBnb.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/familia")
public class FamiliaControlador {

    @Autowired 
    private FamiliaServicio familiaServicio;
    
      @Autowired 
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/lista")
    public String listar(ModelMap model){
        List<Familia> familias = familiaServicio.listarFamilias();
        model.addAttribute("familias", familias);
        
        return "familia_list.html";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes redirectAttributes){
        try {
            familiaServicio.eliminarFamilia(id);
            redirectAttributes.addFlashAttribute("exito", "La familia ha sido eliminada correctamente.");
            return "redirect:/familia/lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/familia/lista";
        }
    }
    
    @GetMapping("/darBaja/{id}/{estado}")
    public String darBaja(@PathVariable String id, @PathVariable Boolean estado, RedirectAttributes redirectAttributes) {
        try {

            if (estado) {
                usuarioServicio.darBajaUsuario(id); 
                redirectAttributes.addFlashAttribute("exito", "La familia ha sido dado de baja correctamente.");
            } else {
                usuarioServicio.darAltaUsuario(id);
                redirectAttributes.addFlashAttribute("exito", "La familia ha sido dado de alta correctamente.");
            }

            return "redirect:/familia/lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/familia/lista";
        }
    }

}
