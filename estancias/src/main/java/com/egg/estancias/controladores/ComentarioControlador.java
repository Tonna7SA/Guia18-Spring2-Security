package com.egg.estancias.controladores;

import com.egg.estancias.entidades.Comentario;
import com.egg.estancias.servicios.ComentarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comentario")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;
    
    @GetMapping("/listar/{idCasa}")
    public String listar(@PathVariable String idCasa, ModelMap model){
        List<Comentario> comentarios = comentarioServicio.buscarComentarioPorCasa(idCasa);
        model.put("comentarios", comentarios);
        return "comentario_list.html";
    }
}
