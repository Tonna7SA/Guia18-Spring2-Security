package com.egg.EjemploBiblioteca.repositorios;

import com.egg.EjemploBiblioteca.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{

}
