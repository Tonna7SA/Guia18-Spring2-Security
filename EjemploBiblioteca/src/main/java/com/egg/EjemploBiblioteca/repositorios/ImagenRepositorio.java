package com.egg.EjemploBiblioteca.repositorios;

import com.egg.EjemploBiblioteca.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {

}
