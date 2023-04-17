package com.egg.EjemploBiblioteca.servicios;

import com.egg.EjemploBiblioteca.entidades.Autor;
import com.egg.EjemploBiblioteca.entidades.Editorial;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.repositorios.EditorialRepositorio;
import com.egg.EjemploBiblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;
    @Autowired
    LibroRepositorio libroRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws MiException{
        
         validar(nombre);
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
        
    }
    
      public List<Editorial> listarEditoriales(){
        
        List <Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
        
    }
      
      public void modificarEditorial(String id, String nombre) throws MiException{
          
           
          
          Optional<Editorial> respuesta = editorialRepositorio.findById(id);
          validar(nombre);
          if(respuesta.isPresent()){
              
              Editorial editorial = respuesta.get();
              editorial.setNombre(nombre);
              
              editorialRepositorio.save(editorial);
              
          }
      }
      
      public void eliminarEditorial(String id) throws MiException {
        try {
            Optional<Editorial> respuestaautor = editorialRepositorio.findById(id);
            if (libroRepositorio.buscarPorAutor(respuestaautor.get().getNombre()) != null) {
                if (respuestaautor.isPresent()) {
                    Editorial editorial = respuestaautor.get();
                    editorialRepositorio.delete(editorial);
                }
            }
        } catch (Exception ex) {
            throw new MiException("La Editorial tiene un libro asignado, por lo cual no se puede borrar...");
                    }
      }
      
      
      public Editorial getone(String id){
        return editorialRepositorio.getOne(id);
        
    }
      
      public void validar(String nombre) throws MiException {
        if(nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }
    }
}
