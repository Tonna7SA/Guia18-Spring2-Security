
package com.egg.EjemploBiblioteca.repositorios;

import com.egg.EjemploBiblioteca.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
}
