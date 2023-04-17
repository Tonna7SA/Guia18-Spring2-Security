package com.egg.EjemploBiblioteca.servicios;

import com.egg.EjemploBiblioteca.entidades.Autor;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.repositorios.AutorRepositorio;
import com.egg.EjemploBiblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;
    @Autowired
    LibroRepositorio libroRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MiException{
        
        validar(nombre);
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
    public List<Autor> listarAutores(){
        
        List <Autor> autores = new ArrayList();
        
        autores = autorRepositorio.findAll();
        
        return autores;
        
    }
    
    public void modificarAutor(String nombre, String id) throws MiException{
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
         validar(nombre);
        
        if (respuesta.isPresent()){
            
            Autor autor = respuesta.get();
            
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        }
    }
    
     public void eliminarAutor(String id) throws MiException {
        try {
            Optional<Autor> respuestaautor = autorRepositorio.findById(id);
            if (libroRepositorio.buscarPorAutor(respuestaautor.get().getNombre()) != null) {
                if (respuestaautor.isPresent()) {
                    Autor autor = respuestaautor.get();
                    autorRepositorio.delete(autor);
                }
            }
        } catch (Exception ex) {
            throw new MiException("El autor tiene un libro asignado, por lo cual no se puede borrar...");
                    }
      }
    
    public Autor getone(String id){
        return autorRepositorio.getOne(id);
        
    }
    
    public void validar(String nombre) throws MiException {
        if(nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }
    }
}
