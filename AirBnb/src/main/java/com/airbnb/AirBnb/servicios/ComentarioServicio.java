package com.airbnb.AirBnb.servicios;

import com.airbnb.AirBnb.entidades.Casa;
import com.airbnb.AirBnb.entidades.Comentario;
import com.airbnb.AirBnb.excepciones.MiException;
import com.airbnb.AirBnb.repositorios.CasaRepositorio;
import com.airbnb.AirBnb.repositorios.ComentarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Autowired
    private CasaRepositorio casaRepositorio;
    

    @Transactional
    public void crearComentario(String descripcion, String idCasa) throws MiException {

        Casa casa = new Casa();
        casa = casaRepositorio.findById(idCasa).get();
        Comentario comentario = new Comentario();

        comentario.setCasa(casa);
        comentario.setDescripcion(descripcion);

        comentarioRepositorio.save(comentario);

    }

    public List<Comentario> listarComentarios() {
        List<Comentario> comentarios = new ArrayList();
        comentarios = comentarioRepositorio.findAll();
        return comentarios;
    }

    @Transactional
    public void modificarCliente(String id, String descripcion, String idComentario, String idCasa) throws MiException {
        validarComentario(descripcion, idComentario);
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Comentario comentario = respuesta.get();
            Casa casa = casaRepositorio.findById(idCasa).get();
            comentario.setCasa(casa);
            comentario.setDescripcion(descripcion);

            comentarioRepositorio.save(comentario);
        }
    }

    @Transactional
    public Comentario getOne(String id) {
        return comentarioRepositorio.getOne(id);
    }

    @Transactional
    public void eliminarCliente(String id) throws MiException {

        Comentario comentario = comentarioRepositorio.getById(id);

        comentarioRepositorio.delete(comentario);

    }

    private void validarComentario(String descripcion, String idComentario) throws MiException {
        if (descripcion == null || descripcion.isEmpty() || descripcion.length() <= 15) {
            throw new MiException("La Descripcion no puede ser nula  estar vacia o tener menos de 15 caracteres");

        }
        if (idComentario == null || idComentario.isEmpty()) {
            throw new MiException("La descripcion debe estar vinculada a una casa");
        }

    }
}
