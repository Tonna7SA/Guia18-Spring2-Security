package com.egg.EjemploBiblioteca.servicios;

import com.egg.EjemploBiblioteca.entidades.Autor;
import com.egg.EjemploBiblioteca.entidades.Editorial;
import com.egg.EjemploBiblioteca.entidades.Libro;
import com.egg.EjemploBiblioteca.excepciones.MiException;
import com.egg.EjemploBiblioteca.repositorios.AutorRepositorio;
import com.egg.EjemploBiblioteca.repositorios.EditorialRepositorio;
import com.egg.EjemploBiblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();

        libro.setISBN(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);

        }
    }
    
      public void eliminarLibro(Long ISBN) throws MiException{

        Optional<Libro> respuesta = libroRepositorio.findById(ISBN);
        
        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();
           
            libroRepositorio.delete(libro); 

        }
    }
      
      

      public Libro getone(Long ISBN){
        return libroRepositorio.getOne(ISBN);
        
    }
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        if (isbn == null) {
            throw new MiException("el isbn no puede ser nulo");
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el título no puede ser nulo o estar vacio");
        }
        if (ejemplares == null) {
            throw new MiException("ejemplares no pueden ser nulos");
        }
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("el autor no puede ser nulo o estar vacio");
        }
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("la editorial no puede ser nula o estar vacia");
        }
    }

}
